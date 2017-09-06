
/**
 * Move randomly
 * 
 * @author A.Beckwith
 * @version 3.2
 */
public class PureRandomAI implements AI
{
    private int myPiece;
    public PureRandomAI(){
    }

    public int move(int[][] board){
        //chose at random 
        int xMove, yMove, code;
        do{
            xMove = (int)(Math.random() * board.length);
            yMove = (int)(Math.random() * board.length);
        }while((board[xMove][yMove] != Util.EMPTY));;
        code = xMove * 10 + yMove;
        return code;
    }

	public int getMyPiece() {
		return myPiece;
	}

	public void setMyPiece(int myPiece) {
		this.myPiece = myPiece;
	}
}

