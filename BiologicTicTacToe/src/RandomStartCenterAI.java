
/**
 * AI that starts at center, then moves randomly
 * 
 * @author A.Beckwith
 * @version 3.2
 */
public class RandomStartCenterAI implements AI
{
    int myPiece;
    public RandomStartCenterAI(int piece){
        myPiece = piece;
    }

    public int move(int[][] board){
        //if the center is open, move there
        int center = (board.length - 1 ) / 2;
        if(board[center][center] == Util.EMPTY) return center * 10 + center;
        //otherwise choose at random
        else
        {

            int xMove, yMove, code;
            do{
                xMove = (int)(Math.random() * board.length);
                yMove = (int)(Math.random() * board.length);
            }while((board[xMove][yMove] != Util.EMPTY));;
            code = ((int)(Math.random() * board.length) * 10 +  (int)(Math.random() * board.length) );
            return code;
        }
    }
}
