/**
 * GUI: puts together selectors, buttons, and display
 * @author A.Beckwith
 * @version 3.2
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;
public class GUI implements ActionListener, ItemListener {

    String[] AINames = {"PureRandom", "RandomStartCenter", "RandomStartCorner"};    //ADD THE NAME OF YOUR AI TO THIS LIST (THE NAME THAT WILL APPEAR IN THE MENU)!!!!!!!

    JFrame window;      
    JComboBox<String> AIList1, AIList2; //lists of AI players
    JComboBox<Integer> sizesList;       //list of possible sizes of board
    JButton playButton, multPlayButton;             
    Display boardDisplay;               //displays the tic-tac-toe board
    JPanel topPanel;                    //holds the AI lists and play button 

    JLabel player1Label, player2Label;  //score labels
    JLabel numberLabel;                 //tells user that textfield is for number of games
    JTextField numGames;                //number of games desired
    JPanel scorePanel;                  //holds the score labels

    JCheckBox showGamesBox;

    Integer[] sizes = {3, 5, 7};        //different board sizes

    AI player1AI, player2AI;            //the players playing the game         
    Game ticTacToe = null;              //the Game

    int[] AINums = {0, 0};              //the starting index of the selected AINames (start with simple random players
    int boardSize = 3;                  //to send to Game constructor

    int player1QuicknessScore, player2QuicknessScore; //keep track of wins and quickness scores
    int[] playerWins = new int[2];    //array of scores to send toe display

    boolean[] player1IsFirst = new boolean[1];
    int numOfGamesPlayed;               
    boolean player1Wins;
    boolean nGames = false;
    boolean showGames;              //controls whether individual games are shown or not

    /**
     * CONSTRUCTOR
     */
    public GUI(){
        window = new JFrame("Tic-Tac-Toe ");
        showGames = true;
        numOfGamesPlayed = 0;
        player1IsFirst[0] = true;
        //set both players' win totals to zero
        playerWins[0] = 0;      
        playerWins[1] = 0;
        //set both players' quickness scores to zero
        player1QuicknessScore = 0;
        player2QuicknessScore = 0;
        //Player1 AI combobox selector:
        AIList1 = new JComboBox<String>(AINames);
        AIList1.setSelectedIndex(0);
        AIList1.addActionListener(this);
        AIList1.setActionCommand("AI1");
        AIList1.setMaximumRowCount(20); //show all when you click
        
        //Player2 AI combobox selector:
        AIList2 = new JComboBox<String>(AINames);
        AIList2.setSelectedIndex(0);
        AIList2.addActionListener(this);
        AIList2.setActionCommand("AI2");
        AIList2.setMaximumRowCount(20); //show all when you click
        
        //size of board selector
        sizesList = new JComboBox<Integer>(sizes);
        sizesList.setSelectedIndex(0);
        sizesList.addActionListener(this);
        sizesList.setActionCommand("sizes");

        //buttons and labels
        playButton = new JButton("Play 1 Game");
        playButton.setActionCommand("play");
        playButton.addActionListener(this);

        JLabel spacingLabel = new JLabel("              ");
        multPlayButton = new JButton("Play n Games");
        multPlayButton.setActionCommand("playN");
        multPlayButton.addActionListener(this);
        numberLabel = new JLabel("# of games: ");
        numGames = new JTextField("1", 5);

        showGamesBox = new JCheckBox("Show Individual Games");
        showGamesBox.addItemListener(this);
        showGamesBox.setSelected(true);

        //checkBoxPanel = new JPanel();
        //checkBoxPanel.add(showGamesBox);
        //topPanel has selectors and play button
        topPanel = new JPanel();
        topPanel.add(AIList1);
        topPanel.add(AIList2);
        topPanel.add(sizesList);
        topPanel.add(playButton);
        //topPanel.add(spacingLabel);
        topPanel.add(multPlayButton);
        topPanel.add(numberLabel);
        topPanel.add(numGames);
        topPanel.add(showGamesBox);

        //create display board with names
        boardDisplay = new Display(AINames, AINums, boardSize, playerWins, player1IsFirst);
        boardDisplay.playing = false;
        //put all components together
        window.setLayout(new BorderLayout());
        window.add(topPanel,BorderLayout.NORTH);
        window.add(boardDisplay, BorderLayout.CENTER);

        window.setPreferredSize(new Dimension(1100, 600));
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
        window.setVisible(true);
    }

    /**
     * ACTIONPERFORMED
     */
    public void actionPerformed(ActionEvent e) {
        //if either AI selection has changed, update the display
        if(e.getActionCommand().equals("AI1") || e.getActionCommand().equals("AI2"))
        {
            //get the selected index of the AI:
            AINums[0] = AIList1.getSelectedIndex();
            AINums[1] = AIList2.getSelectedIndex();

            boardDisplay.playing = false;       //don't try to display board if no board built yet
            //set scores to zero for new competition:
            boardDisplay.playerWins[0] = 0;
            boardDisplay.player1QuicknessScore = 0;
            boardDisplay.playerWins[1] = 0;
            boardDisplay.player2QuicknessScore = 0;
            boardDisplay.numOfTies = 0;

            boardDisplay.repaint();
        }
        //if size of board has changed, update the board
        else if(e.getActionCommand().equals("sizes")){
            boardDisplay.playerWins[0] = 0;
            boardDisplay.player1QuicknessScore = 0;
            boardDisplay.playerWins[1] = 0;
            boardDisplay.player2QuicknessScore = 0;
            boardDisplay.numOfTies = 0;
            boardDisplay.size = (int)sizesList.getSelectedItem();

            boardSize = boardDisplay.size;
            boardDisplay.playing = false;   //don't try to display board if no board built yet
            boardDisplay.repaint();
        }
        //play button has been clicked
        else if(e.getActionCommand().equals("play")){
            nGames = false;
            boardDisplay.playing = true;            //now we're playing for sure
            player1IsFirst[0] = !player1IsFirst[0]; //toggle who goes first

            //make a new game with the players, board size and who goes first:
            ticTacToe = new Game(AINums[0], AINums[1], boardSize, player1IsFirst[0], showGames);  
            //play the game:
            playGame();
            //update display:
            boardDisplay.repaint();
        } 
        //play multiple games has been clicked
        else if(e.getActionCommand().equals("playN")){
            nGames = true;
            boardDisplay.playing = true;            //now we're playing for sure
            int numberOfGames = Integer.parseInt(numGames.getText().toString()); //get number of games
            //play all games:
            for(int i = 0; i < numberOfGames; i++){
                player1IsFirst[0] = !player1IsFirst[0];
                ticTacToe = new Game(AINums[0], AINums[1], boardSize, player1IsFirst[0], showGames);  //AI0 vs. AI1 in a 3x3 game
                playGame();
                boardDisplay.repaint();
            }
        }
    }

    /**
     * playGame Loops through all the moves of the game, determines scoring, and updates display
     */
    public void playGame(){
        boolean done = false;   //becomes true when a player wins or there is a tie
        boardDisplay.tie = false; 

        //main loop to play a single game
        while(!done)
        {
            ticTacToe.makeMove();    //makes the move and sets the board of the game
            boardDisplay.board = ticTacToe.currentBoard.board;  //sends the game of the board to the display
            boardDisplay.repaint();
            boardDisplay.showWinner = !nGames;
            if(ticTacToe.currentBoard.checkForWin() == Util.X_WON || ticTacToe.currentBoard.checkForWin() == Util.O_WON 
            || ticTacToe.currentBoard.checkForWin() == Util.TIE) done = true;
        }
        //for display of games:
        if(showGames) System.out.println("GAME OVER");

        boardDisplay.gameOver = true;
        boardDisplay.repaint();
        //Display winner:
        int quicknessScore = (ticTacToe.currentBoard.boardSize * ticTacToe.currentBoard.boardSize) - ticTacToe.currentBoard.numOfMovesMade;
        if(ticTacToe.currentBoard.checkForWin() == Util.X_WON)
        {
            //Player 1 wins:
            if(ticTacToe.player1Piece == Util.PLAYER_X){
                player1Wins = true;
                //add to games won:
                playerWins[0]++;
                //add to quickness score
                player1QuicknessScore += quicknessScore;
            }
            //Player 2 wins:
            else 
            {
                player1Wins = false;
                playerWins[1]++;
                player2QuicknessScore += quicknessScore;
            }
        }
        else if(ticTacToe.currentBoard.checkForWin()  == Util.O_WON)
        {
            //Player 1 wins:
            if(ticTacToe.player1Piece == Util.PLAYER_O){
                player1Wins = true;
                playerWins[0]++;
                player1QuicknessScore += quicknessScore;
            }
            else
            //Player 2 wins:
            {
                player1Wins = false;
                playerWins[1]++;
                player2QuicknessScore += quicknessScore;
            }
        }
        else 
        //TIE!
        {
            boardDisplay.tie = true;
            boardDisplay.numOfTies++;
        }
        //update board display and repaint:
        boardDisplay.update(player1Wins, boardDisplay.tie, player1QuicknessScore, player2QuicknessScore);
        boardDisplay.repaint();
        numOfGamesPlayed++;
    }

    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        if (e.getStateChange() == ItemEvent.DESELECTED) showGames = false;
        else showGames = true;
    }
}