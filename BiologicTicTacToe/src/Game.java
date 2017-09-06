/**
 * Game
 * Sets up the game and provides methods for allowing AI's to move
 * @author A.Beckwith
 * @version 3.2
 */
public class Game{
    Board currentBoard;  
    //the AI's (NOTE: for now, they are both the same AI which chooses randomly)
    AI player1AI;
    AI player2AI;

    //the move being made by the AI:
    int xMove, yMove;
    int size;   //board size
    //holds the X or O:
    int player1Piece, player2Piece;

    //array of all of the AIs
    Object[] AI = new Object[8];

    boolean gameOver;
    boolean player1Turn;
    boolean showGames;

    /**
     * CONSTRUCTOR
     * @param AI1Num: index of AI array for player1
     * @param AI2Num: index of AI array for player2
     * @param size: size of nxn board
     */
    public Game(AI AI1 , AI AI2, int size, boolean player1First, boolean showGames)
    {
        this.showGames = showGames;
        this.size = size;
        if(player1First){
            player1Turn = true;
            player1Piece = Util.PLAYER_X;
            player2Piece = Util.PLAYER_O;
        }
        else{
            player1Turn = false;
            player1Piece = Util.PLAYER_O;
            player2Piece = Util.PLAYER_X;
        }
        
        player1AI = AI1;
        player2AI = AI2;  
        player1AI.setMyPiece(player1Piece);
        currentBoard = new Board(size);

        gameOver = false;
        currentBoard.numOfMovesMade = 0;  //reset number of moves made - keeps track of when game is over
    }

    /**
     * makeMove
     * Each time makeMove is called, it switches whose turn it is and calls the appropriate 
     * player's  .move() method
     *
     */
    public void makeMove()
    {
        int moveCode;  //digit1 = x, digit2 = y

        //player takes their turn and then switches whose turn it is
        if(player1Turn)
        {
            moveCode = player1AI.move(currentBoard.board);
            //if bad move, the AI will just make it again, so move for them:
            if(!validMove(moveCode)) randomMove();
            //put the X or O into the move spot:
            currentBoard.board[xMove][yMove] = player1Piece;
            player1Turn = false;
        }
        else //player2's turn
        {
            moveCode = player2AI.move(currentBoard.board);
            //if bad move, the AI will just make it again, so move for them:
            if(!validMove(moveCode)) randomMove();
            //put the X or O into the move spot:
            currentBoard.board[xMove][yMove] = player2Piece;
            player1Turn = true;
        }
        currentBoard.numOfMovesMade++;

        if(showGames) currentBoard.displayBoard();
    }

    /**
     * Determines if an AI's move is valid or not
     * @return true if chosen square is empty, otherwise false
     */
    public boolean validMove(int code){
        //coded integer digits: xy
        if(code < 0 || code > (size - 1) * 10 + (size - 1)) return false;
        xMove = code / 10;
        yMove = code - 10 * (code / 10);
        //return validity of move:
        if (currentBoard.board[xMove][yMove] == Util.EMPTY) return true;
        else return false;
    }

    public void randomMove(){
        //choose at random 
        do{
            xMove = (int)(Math.random() * currentBoard.board.length);
            yMove = (int)(Math.random() * currentBoard.board.length);
        }while((currentBoard.board[xMove][yMove] != Util.EMPTY));;
    }
}
