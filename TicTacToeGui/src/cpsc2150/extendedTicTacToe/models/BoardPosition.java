package cpsc2150.extendedTicTacToe.models;

/**
 * This class is used to store the row and column selected for the board position
 *
 * @author Georgia Gelasco CPSC 2150
 * @version Java JDK 11
 *
 * @invariant (ROW >= 0 AND ROW <= 7) AND (COLUMN >= 0 AND COLUMN <= 4)
 */

public class BoardPosition {

    private final int ROW;
    private final int COLUMN;

    /**
     * This constructor creates a new board position with specific row and column coordinates
     *
     * @param row is am number selected for a row between 0-7
     * @param column is am number selected for a column between 0-4
     *
     * @pre none
     * @post ROW = row AND COLUMN = column
     */
    public BoardPosition(int row, int column){
        ROW = row;
        COLUMN = column;
    }

    /**
     * This method provides an int representation of the contents of ROW
     *
     * @return the value stored in the variable ROW
     *
     * @pre (ROW >= 0 AND ROW <= 7)
     * @post *getRow = ROW AND COLUMN = #COlUMN AND ROW = #ROW
     */
    public int getRow(){
        return ROW;
    }

    /**
     * This method provides an int representation of the contents of COLUMN
     *
     * @return the value stored in the variable COLUMN
     *
     * @pre (COLUMN >= 0 AND COLUMN <= 4)
     * @post *getColumn = COLUMN AND COLUMN = #COlUMN AND ROW = #ROW
     */
    public int getColumn(){
        return COLUMN;
    }

    /**
     * This overridden method compares an object the contents of BoardPosition to another object of BoardPosition
     *
     * @param obj object of type BoardPosition
     * @return true if the two objects contain the same values and false if they are different or not on the board
     *
     * @pre (bp.getRow() >= 0 AND bp.getRow()<= 7) AND (bp.getColumn() >= 0 AND bp.getColumn() <= 4)
     *      AND (this.ROW() >= 0 AND this.ROW()<= 7) AND (this.COLUMN() >= 0 AND this.COLUMN() <= 4)
     * @post if this.ROW = bP.ROW AND this.COLUMN = bP.COLUMN then true
     *       else false if this.ROW != bP.ROW OR this.COLUMN != bP.COLUMN
     *       AND COLUMN = #COlUMN AND ROW = #ROW
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        BoardPosition bp = (BoardPosition)obj;
        if((bp.getColumn() == COLUMN) && (bp.getRow() == ROW)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This overridden method converts the int's stored in ROW and COlUMN int a string separating them by a comma
     *
     * @return a string with the row number then a comma then the column number
     *
     * @pre (ROW >= 0 AND ROW <= 7) AND (COLUMN >= 0 AND COLUMN <= 4)
     * @post toString = [ a string representation of <ROW> "," <COLUMN>]
     *      AND [COLUMN and ROW values do not change]
     */
    @Override
    public String toString(){
        String rc = ROW+"," + COLUMN;
        return rc;
    }

}