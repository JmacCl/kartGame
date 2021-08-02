import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    //Fields

    // Width of Frame
    private int width;
    //Height of Frame
    private int height;
    //Title of the platformer
    private String title;
    //Game
    private Game game;

    //Constructor


   public Window(int width, int height, String title, Game game){

       // Make new frame with its own title, then define the dimension of the frame
       JFrame frame = new JFrame(title);
       Dimension dim = new Dimension(width, height);

       // Set size specification of frame
        frame.setPreferredSize(dim);
        frame.setMaximumSize(dim);
        frame.setMinimumSize(dim);

        //Allows frame to exit when closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Cannot resize screen
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        // Add the game to frame, set visible and do the start method for game
        frame.add(game);
        frame.setVisible(true);
        game.start();


    }


}
