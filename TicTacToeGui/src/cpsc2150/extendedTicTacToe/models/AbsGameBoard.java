package cpsc2150.extendedTicTacToe.models;

public abstract class AbsGameBoard implements IGameBoard{

    // This method prints out the entire game board
    @Override
    public String toString(){
        String b = "   ";
        for(int i = 0; i < getNumColumns(); ++i){
            if(i < 10){
                b += " "+ i+"|";
            }
            else{
                b += i+"|";
            }
        }
        b += "\n";
        //rows loop
        for(int i = 0; i < getNumRows(); ++i){
            if(i<10) {
                b += " "+i + "|";
            }
            else{
                b += i + "|";
            }
            //column loop
            for(int m = 0; m < getNumColumns(); ++m){
                BoardPosition temp = new BoardPosition(i,m);
                //if x or o use other function then print else print space
                if(checkSpace(temp) == false){
                    char piece = whatsAtPos(temp);
                    b += piece + " |";
                }
                else{
                    b += "  |";
                }
            }
            b += "\n";
        }
        return b;
    }

}