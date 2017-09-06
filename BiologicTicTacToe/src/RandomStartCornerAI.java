
/**
 * AI that moves at a corner, then moves randomly
 * 
 * @author A.Beckwith
 * @version 3.2
 */
public class RandomStartCornerAI implements AI
{
    int myPiece;
    boolean cornerMove;

    public RandomStartCornerAI(int piece)
    {
        myPiece = piece;
        cornerMove = false;
    }

    public int move(int[][] board){
        //cmove to a corner
        int edge = board.length - 1;  //index of last row and last column
        //upper left corner:
        if(board[0][0] == Util.EMPTY) { cornerMove = true; return 0; }
        //bottom left corner:
        else if(board[edge][0] == Util.EMPTY){ cornerMove = true; return edge * 10; }
        //upper right corner:
        else if(board[0][edge] == Util.EMPTY) { cornerMove = true; return edge; }
        //lower right corner
        else if(board[edge][edge] == Util.EMPTY) { cornerMove = true; return edge * 10 + edge; }
        
        else
        //otherwise, move at random:
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
