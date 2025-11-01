package cpsc2150.extendedTicTacToe.controllers;

import cpsc2150.extendedTicTacToe.models.*;
import cpsc2150.extendedTicTacToe.views.*;

/**
 * <p>
 * The {@link TicTacToeController} class will handle communication between our {@link TicTacToeView}
 * and our model ({@link IGameBoard} and {@link BoardPosition})
 * </p>
 *
 * <p>
 * This is where you will write code
 * <p>
 *
 * You will need to include your {@link BoardPosition} class, the {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class TicTacToeController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private TicTacToeView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;

    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    /**
     * <p>
     *     contains if there was a win tie
     * </p>
     */
    private boolean win = false;

    /**
     * <p>
     *     contains if there was a win tie
     * </p>
     */
    private boolean tie = false;
    /**
     * <p>
     *     holds the player letters
     * </p>
     */
    private char player[];

    /**
     * <p>
     *     holds point in array where the player is
     * </p>
     */
    private int curPlayer;
    /**
     * <p>
     * This creates a controller for running the Extended TicTacToe game
     * </p>
     *
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     *
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;
        player = new char[numPlayers];
        if(numPlayers == 2){
            player[0] = 'X';
            player[1] = 'O';
        }
        if(numPlayers == 3){
            player[0] = 'X';
            player[1] = 'O';
            player[2] = 'M';
        }
        if(numPlayers == 4){
            player[0] = 'X';
            player[1] = 'O';
            player[2] = 'M';
            player[3] = 'A';
        }
        if(numPlayers == 5){
            player[0] = 'X';
            player[1] = 'O';
            player[2] = 'M';
            player[3] = 'A';
            player[4] = 'I';
        }
        if(numPlayers == 6){
            player[0] = 'X';
            player[1] = 'O';
            player[2] = 'M';
            player[3] = 'A';
            player[4] = 'I';
            player[5] = 'S';
        }
        if(numPlayers == 7){
            player[0] = 'X';
            player[1] = 'O';
            player[2] = 'M';
            player[3] = 'A';
            player[4] = 'I';
            player[5] = 'S';
            player[6] = 'Z';
        }
        if(numPlayers == 8){
            player[0] = 'X';
            player[1] = 'O';
            player[2] = 'M';
            player[3] = 'A';
            player[4] = 'I';
            player[5] = 'S';
            player[6] = 'Z';
            player[7] = 'B';
        }
        if(numPlayers == 9){
            player[0] = 'X';
            player[1] = 'O';
            player[2] = 'M';
            player[3] = 'A';
            player[4] = 'I';
            player[5] = 'S';
            player[6] = 'Z';
            player[7] = 'B';
            player[8] = 'H';
        }
        if(numPlayers == 10){
            player[0] = 'X';
            player[1] = 'O';
            player[2] = 'M';
            player[3] = 'A';
            player[4] = 'I';
            player[5] = 'S';
            player[6] = 'Z';
            player[7] = 'B';
            player[8] = 'H';
            player[9] = 'W';
        }
        curPlayer = 0;
        // Some code is needed here.
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     *
     * @param row
     *      The row of the activated button
     * @param col
     *      The column of the activated button
     *
     * @post [ will allow the player to place a marker in the position if it is a valid space, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int row, int col) {
        BoardPosition bp = new BoardPosition(row, col);
        //last time through win or tie this time wherever they pick will restart game
        if(win == true){
            win = false;
            newGame();
        }
        if(tie == true){
            tie = false;
            newGame();
        }
        //make sure space is avalible
        if(curGame.whatsAtPos(bp) == ' '){
            //add player
            screen.setMarker(row, col, player[curPlayer]);
            curGame.placeMarker(bp, player[curPlayer]);
            if(curGame.checkForWinner(bp) == true){
                screen.setMessage("player " + String.valueOf(player[curPlayer]) + " wins!");
                win = true;
            }
            else if(curGame.checkForDraw() == true){
                screen.setMessage("There is a tie!");
                tie = true;
            }
            else {
                //player has not met end of array
                if (curPlayer < numPlayers - 1) {
                    curPlayer++;
                } else {
                    curPlayer = 0;
                }
                screen.setMessage("It is " + String.valueOf(player[curPlayer]) + "\'s turn. ");
            }
        }
        //position is occupoied
        else{
            screen.setMessage("It is " + String.valueOf(player[curPlayer]) + "\'s turn. " + "That space is not available!");
        }
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     *
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();

        //start back at the set up menu
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}