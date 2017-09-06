
/**
 * Util
 * Holds the constants for use with all TicTacToe classes
 * 
 * @author A.Beckwith
 * @version 3.2
 */
import java.util.Scanner;
public class Util
{
    //win status:
    final static int NO_WIN_YET = 0;
    final static int X_WON = 1;
    final static int O_WON = 2;
    final static int TIE = 3;

    //the board contents:
    final static int EMPTY = 4;
    final static int X_IN_LOC = 5;
    final static int O_IN_LOC = 6;

    //the players:
    final static int PLAYER_X = 5;
    final static int PLAYER_O = 6;

    public static int directions(){
        System.out.println("Welcome to tictactoe.\nGames are won by getting 3 O's or X's in a row.\n" +
            "But a secondary score is also calculated based on winning in the fewest moves.\n\n");
        System.out.println("To see n full games, enter # betw. 1 and 10\n To just see the results of n games, enter # > 10");
        Scanner kbd = new Scanner(System.in);
        return(kbd.nextInt());
    }

}
