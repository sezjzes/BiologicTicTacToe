import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


public class TicTacToeBiom extends Biom {
	/**
	 * learns to play tic tac toe
	 * @param filePath where to store the saved data
	 * @param isNew add this boolean to custruct a new learning envrionment
	 */
	public TicTacToeBiom(String filePath, boolean isNew) {
		super(filePath, 9, 4, 2);
	}
	/**
	 * learns to play tic tac toe
	 * @param filePath  where to store the saved data and where it is stored
	 * @throws IOException  filepath doesn't exist already no data is saved
	 */
	public TicTacToeBiom(String filePath) throws IOException {
		super(filePath);
	}
	/**
	 * get a move from a strand
	 * @param strandInUse the strand
	 * @param playerPiece the piece that the stand is playing
	 * @param board the board its playing to
	 * @return move as defined in in abstract AI 
	 */
	public int move(Strand strandInUse, int playerPiece, int[][] board)
	{
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int[] locations  = new int[9];
		int index = 0;
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				int piece = board[i][j];
				{
					if (piece == 4)
					{
						locations[index] = 2;
						moves.add(i*10 + j);
					}
					else if(piece == playerPiece)
					{
						locations[index] = 3;
					}
					else
					{
						locations[index] = 4;
					}
				}
				index++;
			}
		}
		int moveNum = strandInUse.move(locations, moves.size());
		return moves.get(moveNum);
	}
	/**
	 * pits to strands against eachother against itself
	 */
	public String compete(Strand p1, Strand p2) {
		return playGame(new BioAI(this, p1), new BioAI(this, p2));
	}
	/**
	 * plays against a random AI tries to win
	 */
	public int score(Strand p1) {
		int score = 0;
		for(int i = 0; i < 100; i++)
		{
		 if (playGame(new BioAI(this, p1), new PureRandomAI()) == "win") score ++;
		 if (playGame(new BioAI(this, p1), new PureRandomAI()) == "loss") System.out.print("loss");
		}
		return score;
	}
	/**
	 * 
	 * @param AI1 first competitor
	 * @param AI2 second competitor
	 * @return win loss or tie based AI1
	 */
	public String playGame(AI AI1,AI AI2)
	{
		boolean goFirst = false;
		if (Math.random() < .5)
		{
			goFirst = true;
		}
		Game ticTacToe = new Game(AI1, AI2, 3, goFirst, false);
		boolean done = false;
		while(!done)
        {
            ticTacToe.makeMove();    //makes the move and sets the board of the game
            if(ticTacToe.currentBoard.checkForWin() == Util.X_WON || ticTacToe.currentBoard.checkForWin() == Util.O_WON 
            || ticTacToe.currentBoard.checkForWin() == Util.TIE) done = true;
            
        }
		if(goFirst)
		{
			if(ticTacToe.currentBoard.checkForWin() == Util.X_WON) return "won";
			if(ticTacToe.currentBoard.checkForWin() == Util.O_WON) return "lost";
		}
		else
		{
			if(ticTacToe.currentBoard.checkForWin() == Util.X_WON) return "lost";
			if(ticTacToe.currentBoard.checkForWin() == Util.O_WON) return "won";
		}
	    if(ticTacToe.currentBoard.checkForWin() == Util.TIE) return "tied";
	    return "tied";
	}
}
