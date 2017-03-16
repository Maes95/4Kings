/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Piezas;

/**
 *
 * @author Sergio
 */
public class Barco extends Pieza{
    
    @Override
    public boolean validadMovimiento(int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix, int color) {
        finalDesRow = desRow;
        finalDesColumn = desColumn;
        
        if (desRow == (startRow - 2) && desColumn == (startColumn - 2)) //2N, 1E
        {
            return true;
        } else if (desRow == (startRow - 2) && desColumn == (startColumn + 2)) //2N, 1W
        {
            return true;
        } else if (desRow == (startRow + 2) && desColumn == (startColumn - 2)) //2S, 1E
        {
            return true;
        } else if (desRow == (startRow + 2) && desColumn == (startColumn + 2)) //2S, 1W
        {
            return true;
        }
        
        return false;
    }
}
