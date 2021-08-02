import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;

// Make new project with 2d mario kart instaed of super mario

public class Game extends Canvas implements Runnable {

    // Define a integer width and height so that there values of constant and available to use across classes
    public static final int WIDTH = 640, HEIGHT = WIDTH/ 12 * 9;

    //define a thread
    private Thread thread;
    private boolean running = false;


    private Handeler handeler;

    // Make a Game
    public Game(){
        new Window(WIDTH,HEIGHT,"Game",this);
        handeler = new Handeler();
    }

    // Start the game, creates a new thread, start it and setting running to true
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    // try to stop the thread from running
    public synchronized void stop(){
       try {
           thread.join();
           running = false;
       }
       catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){

        handeler.tick();

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs==null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handeler.render(g);

        g.dispose();
        bs.show();

    }

    public static void main(String[] args) {

        new Game();

    }

}


// Make new project on mario kart