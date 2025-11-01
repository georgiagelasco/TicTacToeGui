
package cpsc2150.extendedTicTacToe.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;
/**
 * this class checks sets the constuctor provides the numRows, numColumns, and numToWin funcions,
 * puts a player in a spot and identifies what is at a boardposition
 *
 * @author Georgia Gelasco cpsc 2150
 * @version java JDK 11
 *
 * @invariant Map board contains >= 0 characters and positions
 * @correspondence getNumRows(), getNumColumns(), & getNumToWin() usied in interface are here along with others
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    Map<Character, List<BoardPosition>> board;
    //List<BoardPosition> bpList = new ArrayList<>();
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
    public GameBoardMem(int numR, int numC, int marker){
        r = numR;
        c = numC;
        numToWin = marker;
        board = new HashMap<>();
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
    // if player exists in map then add marker to the associated players existing list
    // if player does not exist in map then add marker into list add both player and list into the map
    public void placeMarker(BoardPosition marker, char player){
        //board.put(player, marker);
        //1 place marker into list
        //2 check to see if player is already in map
        if(board.containsKey(player) == true) {
            //2.5 just add marker list into map somehow conected to player?
            List<BoardPosition> bpList = board.get(player);
            bpList.add(marker);
        }
        // 3 else add player and marker list into map
        else{
            List<BoardPosition> bpList = new ArrayList<>();
            bpList.add(marker);
            board.put(player, bpList);
        }
    }

    // This method finds what is located at the row and column associated with pos
    //ok so i was feelink ok here but do my array list have to be eaual to other lists?
    public char whatsAtPos(BoardPosition pos){
        //0 put pos into a list
        //Character arrayKey[] = new Character[10];
        //int index = 0;
        for(Map.Entry<Character, List<BoardPosition>> m : board.entrySet()){
            char player = m.getKey();
            List<BoardPosition> bpList = m.getValue();
            boolean hasVal = bpList.contains(pos);
            if(hasVal == true){
                return player;
            }
        }
        //1 check to see if contains value at board position
        //2 if false return ' '
        return ' ';
        ///return '\0';
        //3 else get char at pos
        //3.5 return char
        //char atPos = board.;
    }
}
