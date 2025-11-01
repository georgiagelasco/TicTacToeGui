package cpsc2150.extendedTicTacToe.models;

/**
 * this class checks the locations throughout the game board to see if
 * the space is avaliable or what is at that space and if there is a win/draw
 *
 * @author Georgia Gelasco cpsc 2150
 * @version java JDK 11
 *
 * @invariant (board != null) AND (board = new char[getNumRows()][getNumColumns()])
 * @correspondence rowPick = board[row][]
 *                 columnPick = board[][column]
 */

public class GameBoard extends AbsGameBoard implements IGameBoard{

    //private variables
    private char board[][];
    private final int r;
    private final int c;
    private final int numToWin;

    /**
     * This constructor initializes the array board with the size of 5x8 and initializes all the private variables
     *
     * @pre none
     * @post (board = new char[8][5]) [every position in board is empty]
     */
    //constructor
    public GameBoard(int numR, int numC, int marker){
        r = numR;
        c = numC;
        numToWin = marker;
        board = new char[r][c];
    }


    //gets the number of rows in the gameboard
    public int getNumRows(){
        return r;
    }

    // gets the number of columns in the gameboard
    public int getNumColumns(){
        return c;
    }

    // gets how many more pieces are needed in a row for a win
    public int getNumToWin(){
        return numToWin;
    }

    // This method adds the players character to the board at the specific row and column
    public void placeMarker(BoardPosition marker, char player){
        if(checkSpace(marker) == true){
            board[marker.getRow()][marker.getColumn()] = player;
        }
    }

    // This method finds what is located at the row and column associated with pos
    public char whatsAtPos(BoardPosition pos) {
        char player = board[pos.getRow()][pos.getColumn()];
        if (player != '\0') {
            //System.out.println("player = " + player);
            return player;
        }
        return ' ';
        //return '\0';
    }

}