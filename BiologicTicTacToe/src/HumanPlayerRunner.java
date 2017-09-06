
/**
 *  Console version of tic-tac-toe to test out against human player
 * 
 * @author A.Beckwith
 * @version 6.8.15
 * 
 * NOTE:
 *      TO CHANGE THE AI CHANGE LINES 44 AND 66
 */

//TO-DO: change over to using JPanel and mouselistener
import java.util.Scanner;

public class HumanPlayerRunner
{
    public static void main(String[] args)
    {
        //SPLASH:
        System.out.println("Welcome to Human vs. AI Tic-Tac-Toe!\n");
        Scanner s = new Scanner(System.in);

        boolean player1Turn;            //tracks whose turn it is
        int player1Piece, player2Piece;  //X or O for each

        boolean gameOver = false;  //individual game
        boolean done = false;       //whole program

        Board board;

        int moveCode = -1;

        int xMove = 0;
        int yMove = 0;
        //GET BOARD SIZE:
        System.out.print("What size board? (3, 5, or 7)==> ");
        int bdsize = s.nextInt();
        if(bdsize != 3 && bdsize != 5 && bdsize != 7) bdsize = 3;  //invalid board size default = 3

        while(!done)
        {
            board = new Board(bdsize);

            System.out.println("\nPlaying against pureRandomAI");
            gameOver = false;

            System.out.print("\nWho do you want to move first? 1 = human, 2 = AI (3 = quit)==> ");
            int choice = s.nextInt();
            if(choice == 3) System.exit(1);

            System.out.println("======================");

            if(choice == 1){
                player1Turn = true;             //player 1 is always the human
                player1Piece = Util.PLAYER_X;
                player2Piece = Util.PLAYER_O;
            }
            else{
                player1Turn = false;            //player is always the AI
                player1Piece = Util.PLAYER_O;
                player2Piece = Util.PLAYER_X;
            }
            /*
             * CHANGE LINE BELOW FOR DIFFERENT AI
             */
            PureRandomAI AIPlayer = new PureRandomAI(player1Piece);  //which AI the human is playing against

            //show the board to start
            displayBoard(board.board, player1Turn);

            /*
             * Main Game Loop
             */
            board.numOfMovesMade = 0;
            while(!gameOver)
            {
                if(player1Turn)
                {
                    do{  
                        /*
                         * Show move codes:
                         */
                        System.out.println("Move Codes:");
                        int spot = 1;
                        String colNums = "";
                        for(int i = 0; i < board.board.length; i++){
                            for(int j = 0; j < board.board.length; j++){
                                if(board.board[i][j] == Util.EMPTY)
                                {
                                    System.out.print("_" + spot + "_");         
                                    if(spot < 10 && board.board.length > 3) System.out.print("_");
                                }
                                else if(board.board.length > 3) {
                                    System.out.print("____");
                                }
                                else System.out.print("___");
                                spot++;
                            }
                            System.out.println();
                        }
                        //get a valid move code from human
                        System.out.print("enter move code==> ");
                        moveCode = s.nextInt();
                        System.out.println("======================");
                        //find spot on board:
                        moveCode--;
                        xMove = moveCode / board.board.length;
                        yMove = moveCode % board.board.length;
                        //KEEP TRYING WHILE BAD MOVE:
                    }while(xMove > bdsize - 1 || yMove > bdsize - 1 || (board.board[xMove][yMove] != Util.EMPTY) );

                    //place the Human piece chosen:
                    board.board[xMove][yMove] = player1Piece;
                    board.numOfMovesMade++;
                    player1Turn = false;
                }
                else{
                    //get a valid move code from AI:
                    moveCode = AIPlayer.move(board.board);
                    if(!validMove(moveCode, board)) {
                        do{
                            xMove = (int)(Math.random() * board.board.length);
                            yMove = (int)(Math.random() * board.board.length);
                        }while((board.board[xMove][yMove] != Util.EMPTY));
                    }
                    else{
                        xMove = moveCode / 10;
                        yMove = moveCode % 10;
                    }
                    //place the AI piece chosen
                    board.board[xMove][yMove] = player2Piece;
                    board.numOfMovesMade++;
                    player1Turn = true;
                }
                //show board with new move:
                displayBoard(board.board, player1Turn);

                /*
                 * Check for wins or ties
                 */
                if(board.checkForWin() == Util.X_WON && player1Piece == Util.PLAYER_X
                || board.checkForWin() == Util.O_WON && player1Piece == Util.PLAYER_O){
                    System.out.println("Human Wins!");
                    gameOver = true;
                }
                else if(board.checkForWin() == Util.X_WON && player2Piece == Util.PLAYER_X
                ||  board.checkForWin() == Util.O_WON && player2Piece == Util.PLAYER_O){
                    System.out.println("AI Wins!");
                    gameOver = true;
                }
                else if(board.checkForWin() == Util.TIE){
                    System.out.println("TIE!");
                    gameOver = true;
                }
            }
        }
    }

    /**
     * Checks for valid moves
     */
    public static boolean validMove(int code, Board board){
        //check for invalid code numbers:
        if(code < 0 || code > (board.board.length - 1) * 10 + (board.board.length - 1)) return false;
        int xMove = code / 10;
        int yMove = code - 10 * (code / 10);

        //true = empty, false = occumpied:
        if (board.board[xMove][yMove] == Util.EMPTY) return true;
        else return false;
    }

    /**
     * Display board in console
     */
    public static void displayBoard(int[][] bd, boolean player1Turn){
        if(!player1Turn) System.out.println("YOUR MOVE:");
        //SHOW CURRENT BOARD:
        for(int i = 0; i < bd.length; i++){
            for(int j = 0; j < bd.length; j++){
                if(bd[i][j] == Util.EMPTY){
                    System.out.print(" _  ");          //empty
                }
                else if (bd[i][j] == Util.X_IN_LOC) System.out.print(" X  ");  //X
                else System.out.print(" O  ");       //O
            }
            System.out.println();
        }
        String instr = "";
        if(!player1Turn) instr = "\n(....AI MOVE.....):";        //don't show instructions for AI player's turn
        System.out.println("======================" + instr);
    }
}
