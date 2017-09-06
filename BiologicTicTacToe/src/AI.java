
/**
 * AI The superclass of all other AIs
 * 
 * @author A.Beckwith
 * @version 2.0
 */
public interface AI
{
    /**
     * param: 2D nxn board with (n=3, 5, or 7)  
     *   contents of board:
     *   EMPTY = 4;
     *   X_IN_LOC = 5;
     *   O_IN_LOC = 6;
     *   
     * return: movecode, which is row, column location, using 10 * row + column
     *    e.g.  21 = 2nd row, 1st column
     */
    public int move(int[][] board);
    public void setMyPiece(int myPiece);
}
