package cpsc2150.extendedTicTacToe.models;

/**
 * game board has 8 rows 5 columns and will be filled with null OR 'x' OR 'O'
 * initialization ensures: grid is empty
 * defines: rowPick: z
 *          columnPick: Z
 * constraints: MIN <= rowPic < MAX_IN_ROW
 *              MIN <= columnPic < MAX_IN_COLUMN
 */

public interface IGameBoard {

    /**gets the number of rows in the gameboard
     *
     * @return the number of rows in the gameBoard
     *
     * @pre none
     * @post *getNumRows = MAX_IN_ROW
     */
    public int getNumRows();

    /** gets the number of columns in the gameboard
     *
     * @return the number of columns in the gameBoard
     *
     * @pre none
     * @post *getNumColumns = MAX_IN_COLUMN
     */
    public int getNumColumns();

    /** gets how many more pieces are needed in a row for a win
     *
     * @return the number of pieces in a row needed to win the game
     *
     * @pre none
     * @post *getNumToWin = TOTAL_WIN
     */
    public int getNumToWin();

    //defurmine if secondary
    /**
     * This method checks a specific location to see if there is something already there or if it is open or if it out of bounds
     *
     * @param pos is a row and column destination at which is being checked to see if there is a players peice there
     * @return true if that specific location is available or false if there is already something there or is out of bounds
     *
     * @pre (pos != null) AND (board != null)
     * @post true if((pos.getRow() >= MIN AND pos.getRow() <= MAX_IN_ROW - 1) AND (pos.getColumn() >= MIN AND pos.getColumn() <= MAX_IN_COLUMN -1)
     *               AND (board[pos.getRow()][pos.getColumn()] = '\0')) [only place in board that changes is at pos]
     */
    public default boolean checkSpace(BoardPosition pos){
        BoardPosition bp = new BoardPosition(pos.getRow(), pos.getColumn());
        if(whatsAtPos(bp) != ' '){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * This method adds the players character to the board at the specific row and column
     *
     * @param marker the location in row and column on the board
     * @param player the character of either X or O that that player is playing as
     *
     * @pre (checkSpace() = true) AND (player = 'X' Or player = 'O') AND (board != null)
     *       AND (pos.getRow() >= MIN AND pos.getRow() <= MAX_IN_ROW - 1) AND (pos.getColumn() >= MIN AND pos.getColumn() <= MAX_IN_COLUMN -1)
     * @post (board != null) AND (board[marker.getRow()][marker.getColumn()] = player)
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * This method checks to see if the last position placed results in a win
     *
     * @param lastPos the row and columns at which the last position has been placed
     * @return true if last position place results in a win or false if the last position placed did not result in a winner
     *
     * @pre  [lastPos is the game board position on the latest play]
     *       (lastPos.getRow() >= MIN AND lastPos.getRow() <= MAX_IN_ROW - 1)
     *       AND (lastPos.getColumn() >= MIN AND lastPos.getColumn() <= MAX_IN_COLUMN -1) AND (board != null)
     * @post true if((checkForVerticalWin(lastPos) = true) OR (checkForHorizontalWin(lastPos) = true) OR
     *                (checkForDiagonalWin(lastPos) = true))
     */
    public default boolean checkForWinner(BoardPosition lastPos){
        char player = whatsAtPos(lastPos);
        if((checkDiagonalWin(lastPos, player) == true) || (checkHorizontalWin(lastPos, player) == true) || (checkVerticalWin(lastPos, player) == true)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method checks to see if the game has resulted in a tie and no one won
     *
     * @return true if there are no wins and no free spaces available or false if there is a win or still free spaces
     *         available
     *
     * @pre (board != null) AND (checkForWinner() != true)
     * @post true if [loop through board and no positions equal '\0']
     */
    public default boolean checkForDraw(){
        //loop through and see if there is a player in each space
        boolean space = true;
        //row loop
        for(int i = 0; i < getNumRows(); ++i) {
            //column loop
            for (int m = 0; m < getNumColumns(); ++m) {
                BoardPosition temp = new BoardPosition(i,m);
                if((whatsAtPos(temp) != ' ')){
                    //then the draw is still true
                }
                else{
                    space = false;
                }
            }
        }
        return space;
    }

    /**
     * This method checks to see if there are 5 in a row horizontally resulting in a horizontal win
     *
     * @param lastPos the row and columns at which the last position has been placed
     * @param player the character of either X or O that that player is playing as
     * @return true if there are 5 pieces of the player in a row horizontally else false if there are not
     *         5 of the player in a row horizontally
     *
     * @pre (lastPos != null) AND (board != null) And (lastPos.getRow() >= MIN AND lastPos.getRow() <= MAX_IN_ROW - 1)
     *      AND (lastPos.getColumn() >= MIN AND lastPos.getColumn() <= MAX_IN_COLUMN -1) AND (player = 'X' OR player = 'O')
     * @post true = if[loop through columns to see is 5 of the character of the player are in a next to each other like
     *                 board[MIN][lastPos.getColumn()]--> board[MIN + 1][lastPos.getColumn()]... add one each time they are next to
     *                 each other and clear if they ar not next to each other till board[MAX_IN_ROW - 1][lastPos.getColumn()]]
     */
    public default boolean checkHorizontalWin(BoardPosition lastPos, char player){
        int rowNum = lastPos.getRow();
        boolean winner = false;
        int i = 0;
        int inRow = 0;
        while(i < getNumColumns()){
            BoardPosition bp = new BoardPosition(rowNum, i);
            if(whatsAtPos(bp) == player){
                //winner = true;
                inRow += 1;
            }
            else{
                if((inRow < getNumToWin()) && (inRow < getNumRows())){
                    inRow = 0;
                }
                //winner = false;
            }
            ++i;
        }
        if(inRow >= getNumToWin()){
            winner = true;
        }
        return winner;
    }

    /**
     * This method checks to see if there are 5 in a row vertically resulting in a vertical win
     *
     * @param lastPos the row and columns at which the last position has been placed
     * @param player the character of either X or O that that player is playing as
     * @return true if there are 5 pieces of the player in a row vertically else false if there are not 5
     *         of the player in a row vertically
     *
     * @pre (lastPos ! = null) AND (board != null) And (lastPos.getRow() >= MIN AND lastPos.getRow() <= MAX_IN_ROW - 1)
     *     AND (lastPos.getColumn() >= MIN AND lastPos.getColumn() <= MAX_IN_COLUMN -1) AND (player = 'X' OR player = 'O')
     * @post true = if[loop through rows to see is 5 of the character of the player are in a next to each other like
     *               board[lastPos.getRow()][MIN]--> board[lastPos.getRow()][MIN + 1]... add one each time they are next to
     *               each other and clear if they ar not next to each other till board[lastPos.getRow()][MAX_IN_COLUMN -1]]
     */
    public default boolean checkVerticalWin(BoardPosition lastPos, char player){int colNum = lastPos.getColumn();
        int inRow = 0;
        boolean winner = true;
        int i = 0;
        while(i < getNumRows()){
            BoardPosition bp = new BoardPosition(i, colNum);
            if(whatsAtPos(bp) == player){
                inRow += 1;
            }
            else{
                if(inRow < getNumToWin()) {
                    inRow = 0;
                }
            }
            ++i;
        }
        if(inRow == getNumToWin()){
            winner = true;
        }
        else{
            winner = false;
        }
        return winner;

    }

    /**
     *This method checks to see if there are 5 in a row diagonally resulting in a diagonal win
     *
     * @param lastPos the row and columns at which the last position has been placed
     * @param player the character of either X or O that that player is playing as
     * @return true if there are 5 pieces of the player in a row diagonally else false if there are not 5
     *         of the player in a row diagonally
     *
     * @pre  @pre (lastPos ! = null) AND (board != null) And (lastPos.getRow() >= MIN AND lastPos.getRow() <= MAX_IN_ROW - 1)
     *      AND (lastPos.getColumn() >= MIN AND lastPos.getColumn() <= MAX_IN_COLUMN -1) AND (player = 'X' OR player = 'O')
     * @post true = if[board[marker.getRow()][marker.getColumn()] to the right diagonally or to the left diagonally
     *                  has 5 consecutivly diagonal from each other]
     */
    public default boolean checkDiagonalWin(BoardPosition lastPos, char player){
        int rowNum = lastPos.getRow();
        int colNum = lastPos.getColumn();
        int posInRow = 0;
        int negInRow = 0;
        int i;
        //check positive /
        while(colNum != 0 && rowNum != getNumRows() -1){
            rowNum = rowNum + 1;
            colNum = colNum - 1;
        }
        i = 0;
        int tempCN = colNum;
        if((rowNum - getNumToWin()) >= 0 && (colNum + getNumToWin()) < getNumColumns()){
            while (((colNum + i) < getNumColumns()) && ((rowNum - i) >= 0)) {
                BoardPosition bp = new BoardPosition(rowNum - i, colNum + i);
                if (whatsAtPos(bp) == player) {
                    posInRow += 1;
                } else {
                    if (posInRow < getNumToWin()) {
                        posInRow = 0;
                    }
                }
                ++i;
            }
        }
        if(posInRow >= getNumToWin()){
            return true;
        }
        rowNum = lastPos.getRow();
        colNum = lastPos.getColumn();

        //check negatice \
        while(colNum != 0 && rowNum != 0){
            rowNum = rowNum - 1;
            colNum = colNum - 1;
        }
        i = 0;
        if((rowNum + getNumToWin()) <= getNumRows() && (colNum + getNumToWin()) <= getNumColumns()){
            while (((colNum + i) < getNumColumns()) && ((rowNum + i) < getNumRows() )) {
                BoardPosition bp = new BoardPosition(rowNum + i, colNum + i);
                if (whatsAtPos(bp) == player) {
                    negInRow += 1;
                } else {
                    if (negInRow < getNumToWin()) {
                        negInRow = 0;
                    }
                }
                ++i;
            }
        }
        else{
            negInRow = 0;
        }
        if (negInRow >= getNumToWin()) {
            return true;
        }

        return false;

    }

    /**
     * This method finds what is located at the row and column associated with pos
     *
     * @param pos the row and column on the gameboard
     * @return X or O if one of them are in that location otherwise a blank space ' '
     *
     * @pre  (pos ! = null) AND (pos.getRow() >= MIN AND pos.getRow() <= MAX_IN_ROW - 1)
     *       AND (pos.getColumn() >= MIN AND pos.getColumn() <= MAX_IN_COLUMN -1) AND (board != null)
     * @post if (board[pos.getRow()][pos.COLUMN] = null then atPos = ' ')
     *       else (atPos = board[pos.getRow()][pos.COLUMN])
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * This method checks to see if there is a player at the row and column of the position
     *
     * @param pos the row and column on the gameboard
     * @param player the character of either X or O that that player is playing as
     * @return true if there is a X or O at that location else return false if that position in board is empty
     *
     * @pre  (pos.getRow() >= MIN AND pos.getRow() <= MAX_IN_ROW - 1) AND (pos.getColumn() >= MIN AND pos.getColumn() <= MAX_IN_COLUMN -1)
     *        AND (player = 'X' OR player = 'O') AND (board != null) AND (pos ! = null)
     * @post true if((board[pos.getRow()][pos.getColumn[] != null) AND (player = board[pos.getRow()][pos.getColumn()]))
     */
    //cals whatsAtPos if it is ' ' or null obviously doesn't work then check return with val
    public default boolean isPlayerAtPos(BoardPosition pos, char player){
        char atP = whatsAtPos(pos);
        //do i need .equals here?
        if(atP == player){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method prints out the entire game board
     *
     * @return a string representation of board[] with | to separate each place in the boars
     *         and MIN-(MAX_IN_ROW - 1) representing the column on top and MIN-(MAX_IN_COLUMN -1) representing the rows along the left side
     *
     * @pre (board != null)
     * @post toString = [a string representation of ' ' + '0' + ' ' until 7 \n
     *                  '0' + '|' + board[MIN][MIN] + "|" until board[MIN][MAX_IN_ROW - 1] \n
     *                  repeate for each line and if there is not an X or O in that place of the array print ' ']
     */
    @Override
    public String toString();

}
