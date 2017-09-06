import java.awt.*;
import javax.swing.*;

/**
 * Displays a moving text on screen
 */
public class Display extends JPanel
{
    //location of text:
    int textxLoc = 20;
    int textyLoc = 200;
    int shift = 10;  //amount to shift text over each timer fire
    int x = 1;  //the number to display
    /*
     * Constructor
     */
    public Display() {
        setBackground(new Color(0, 0, 0));              //background will be black
        //setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));  
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);        //clears canvas
        Font courier = new Font ("Courier", Font.PLAIN, 25); 
        g.setColor(Color.BLUE);
        g.drawString("Score: (do this)", 280, 20);
        g.setColor(Color.WHITE);
        g.drawString("currently numbers are random...", 30, 45);
        g.drawString("write code to display numbers be like" , 30, 75);
        g.drawString("\"6, 67, 671, 6715, 67153,...\"", 30, 100);
        //Yellow text:
        g.setColor(Color.YELLOW);
        g.setFont(courier); //set the font
        g.drawString("memorize ", textxLoc, textyLoc);
        g.drawString("" + x, textxLoc, textyLoc + 40);
    }

}
