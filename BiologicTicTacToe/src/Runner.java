/**
 * Runner: sets up a new game and loops until there's a winner
 * @author A.Beckwith
 * @version 3.2 6/13/14
 */
import java.util.Scanner;
import javax.swing.JOptionPane;
public class Runner {

    public static void main(String[] args)
    {
        JOptionPane.showMessageDialog(null,
            "3x3: must get 3 in a row to win\n" +
            "5x5: must get 4 in a row to win\n" + 
            "7x7: must get 5 in a row to win\n",   
            "TIC-TAC-TOE nxn",				       
            JOptionPane.INFORMATION_MESSAGE);	 
            
            
        GUI go = new GUI();
    }
}