
/**
 * Class: Board - keeps track of pieces on board, checks for win, displays board
 * 
 * @author A.Beckwith
 * @version 3.2
 */
public class Board
{
    //the board:
    int[][] board;  
    int boardSize;
    int numOfMovesMade;
    AI player1AI, player2AI;  //to access the movedCorner method in RandomCorner
    /**
     * Board CONSTRUCTOR
     * @param size the side length of the board
     */
    public Board(int size){
        // create board and set to blanks:
        this.player1AI = player1AI;
        this.player2AI = player2AI;
        boardSize = size;
        board = new int[boardSize][boardSize];
        resetToBlankBoard();
        numOfMovesMade = 0;
    }

    /**
     * Checks to see if board contains a winner 
     * @return 0 = player1 winner, 1 = player2 winner, 2 = no winner yet, 3 = tie
     */
    public int checkForWin()
    {
        //FOR 3X3 BOARD:
        if(board.length == 3)
        {
            for(int r = 0; r < board.length; r++)
            {
                if(board[r][0] != Util.EMPTY && board[r][0] == board[r][1] && board[r][1] == board[r][2]){
                    if(board[r][0] == Util.X_IN_LOC) return Util.X_WON;   
                    else return Util.O_WON; 
                }
            }
            //check for three down
            for(int c = 0; c < board.length; c++)
            {
                if(board[0][c] != Util.EMPTY && board[0][c] == board[1][c] && board[1][c] == board[2][c]){
                    if(board[0][c] == Util.X_IN_LOC) return Util.X_WON;   
                    else return Util.O_WON;       
                }
            }
            //check two diagonals
            if(board[0][0] != Util.EMPTY && board[0][0] == board[1][1]  && board[1][1] == board[2][2])
            {
                if(board[0][0] == Util.X_IN_LOC) return Util.X_WON;   
                else return Util.O_WON;     
            }
            else if(board[2][0] != Util.EMPTY && board[2][0] == board[1][1]  && board[1][1] == board[0][2])
            {
                if(board[2][0] == Util.X_IN_LOC) return Util.X_WON;   
                else return Util.O_WON;      
            }
        }
        //FOR 5X5 BOARD:
        else if(board.length == 5)
        {
            //check for 4 across
            for(int r = 0; r < board.length; r++)
            {
                for (int c = 0; c <= 1; c++)
                {
                    if(board[r][c] != Util.EMPTY && board[r][c] == board[r][c + 1] && board[r][c + 1] == board[r][c + 2]
                    && board[r][c + 2] == board[r][c + 3])
                    {
                        if(board[r][c] == Util.X_IN_LOC) return Util.X_WON;   
                        else return Util.O_WON; 
                    }
                }
            }
            //check for 4 down
            for(int c = 0; c < board.length; c++)
            {
                for (int r = 0; r <= 1; r++)
                {
                    if(board[r][c] != Util.EMPTY && board[r][c] == board[r + 1][c] && board[r + 1][c] == board[r + 2][c]
                    && board[r + 2][c] == board[r + 3][c])
                    {
                        if(board[r][c] == Util.X_IN_LOC) return Util.X_WON;   
                        else return Util.O_WON; 
                    }
                }
            }
            //check main diagonal and two above going southeast
            for (int r = 0; r <= 1; r++){
                for(int c = 0; c <= 1; c++){

                    if(board[r][c] != Util.EMPTY && board[r][c] == board[r+1][c+1]  && board[r+1][c+1] == board[r+2][c+2]
                    && board[r+2][c+2] == board[r+3][c+3])
                    {
                        if(board[r][c] == Util.X_IN_LOC) return Util.X_WON;   
                        else return Util.O_WON;     
                    }
                }
            }
            //check diagonals going southwest
            for (int r = 0; r <= 1; r++){
                for(int c = 3; c <= 4; c++){

                    if(board[r][c] != Util.EMPTY && board[r][c] == board[r+1][c-1]  && board[r+1][c-1] == board[r+2][c-2]
                    && board[r+2][c-2] == board[r+3][c-3])
                    {
                        if(board[r][c] == Util.X_IN_LOC) return Util.X_WON;   
                        else return Util.O_WON;     
                    }
                }
            }
        }
        //FOR 7X7 BOARD
        else
        {
            //check 5 across:
            for(int r = 0; r < board.length; r++)
            {
                for (int c = 0; c <= 2; c++)
                {
                    if(board[r][c] != Util.EMPTY && board[r][c] == board[r][c + 1] && board[r][c + 1] == board[r][c + 2]
                    && board[r][c + 2] == board[r][c + 3] && board[r][c+3] == board[r][c+4])
                    {
                        if(board[r][c] == Util.X_IN_LOC) return Util.X_WON;   
                        else return Util.O_WON; 
                    }
                }
            }
            //check 5 down:
            for(int c = 0; c < board.length; c++)
            {
                for (int r = 0; r <= 2; r++)
                {
                    if(board[r][c] != Util.EMPTY && board[r][c] == board[r + 1][c] && board[r + 1][c] == board[r + 2][c]
                    && board[r + 2][c] == board[r + 3][c] && board[r+3][c] == board[r+4][c])
                    {
                        if(board[r][c] == Util.X_IN_LOC) return Util.X_WON;   
                        else return Util.O_WON; 
                    }
                }
            }
            //check diagonals going southeast
            for (int r = 0; r <= 2; r++){
                for(int c = 0; c <= 2; c++){

                    if(board[r][c] != Util.EMPTY && board[r][c] == board[r+1][c+1]  && board[r+1][c+1] == board[r+2][c+2]
                    && board[r+2][c+2] == board[r+3][c+3] && board[r+3][c+3] == board[r+4][c+4])
                    {
                        if(board[r][c] == Util.X_IN_LOC) return Util.X_WON;   
                        else return Util.O_WON;     
                    }
                }
            }
            //check diagonals going southwest
            for (int r = 0; r <= 2; r++){
                for(int c = 4; c <= 6; c++){

                    if(board[r][c] != Util.EMPTY && board[r][c] == board[r+1][c-1]  && board[r+1][c-1] == board[r+2][c-2]
                    && board[r+2][c-2] == board[r+3][c-3] && board[r+3][c-3] == board[r+4][c-4])
                    {
                        if(board[r][c] == Util.X_IN_LOC) return Util.X_WON;   
                        else return Util.O_WON;     
                    }
                }
            }
        }
        //no win, so keep going or its a tie
        if (numOfMovesMade < boardSize * boardSize) return Util.NO_WIN_YET;  //board not yet filled and no winner
        else return Util.TIE;                //board filled and not winner
    }

    /**
     * Resets the board to all blanks
     */
    public void resetToBlankBoard(){
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                board[r][c] = Util.EMPTY;
            }
        }
    }

    /**
     * Displays the board in the console
     */
    public void displayBoard(){
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                if(board[r][c] == Util.PLAYER_X) System.out.print("X");
                else if(board[r][c] == Util.PLAYER_O) System.out.print("O");
                else  System.out.print("_");
                //include vertical bars of board:
                if(c < board[0].length - 1) System.out.print("|");
            }
            System.out.println(); //go to next line 
        }
        System.out.println();
    }
}
