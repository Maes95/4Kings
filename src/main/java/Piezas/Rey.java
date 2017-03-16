package Piezas;

//MOvimiento del rey
public class Rey extends Pieza {
    
    public void objKing() {
    }
    
    /**
     *
     * @param startRow
     * @param startColumn
     * @param desRow
     * @param desColumn
     * @param playerMatrix
     * @param color
     * @return
     */
    @Override
    public boolean validadMovimiento(int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix, int color) {
        
        finalDesRow = desRow;
        finalDesColumn = desColumn;
      
        
        if (desRow == (startRow + 1) && desColumn == startColumn) //S
        {
            return true;
        } else if (desRow == (startRow + 1) && desColumn == (startColumn - 1)) //SW
        {
            return true;
        } else if (desRow == startRow && desColumn == startColumn - 1) //W
        {
            return true;
        } else if (desRow == (startRow - 1) && desColumn == (startColumn - 1)) //NW
        {
            return true;
        } else if (desRow == (startRow - 1) && desColumn == startColumn) //N
        {
            return true;
        } else if (desRow == (startRow - 1) && desColumn == (startColumn + 1)) //NE
        {
            return true;
        } else if (desRow == startRow && desColumn == (startColumn + 1)) //E
        {
            return true;
        } else if (desRow == (startRow + 1) && desColumn == (startColumn + 1)) //SE
        {
            return true;
        } else {
            
            strErrorMsg = "Rey solo se puede mover un espacio en diferente direcciones";
            return false;
            
        }
        
    }
    
}