
public class BioAI implements AI{


    private int myPiece;
    private TicTacToeBiom home;
    private Strand base;
	public BioAI(TicTacToeBiom home, Strand base) {
		this.home = home;
		this.base = base;
	}

    public int move(int[][] board)
    {
    	return home.move(base, myPiece, board);
    }

	public int getMyPiece() {
		return myPiece;
	}

	public void setMyPiece(int myPiece) {
		this.myPiece = myPiece;
	}

}
