package Piezas;
public class Peon extends Pieza {
    
    @Override
    public boolean validadMovimiento(int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix, int color) {
        
        boolean legalMove = true;
        int color2 = playerMatrix[desRow][desColumn] / 10;
        switch(color){
            case 1:
                if(desRow >= startRow){
                    strErrorMsg = "No se puede mover de esa manera";
                    legalMove = false;
                } else if (desColumn != startColumn) { //SI SE MUEVE EN DIAGONAL
                    if ((desColumn > startColumn && desColumn == (startColumn + 1)) || (desColumn < startColumn && desColumn == (startColumn - 1))){//DI o DD
                       if(desRow == (startRow - 1)){
                            
                            if (playerMatrix[desRow][desColumn] == 0 || color2==color) //0 REPRESENTA QUE NO HAY PIEZA AHÍ, CAMBIAR POR LO QUE SEA
                                {
                                    strErrorMsg = "Solo se puede mover en diagonal cuando se va a capturar una pieza enemiga";
                                    legalMove = false;
                                }
                        } else {//SI SE MUEVE EN DIAGONAL MAS DE UNA POS
                            strErrorMsg = "No se puede mover de esa manera";
                            legalMove = false;
                        }
                    } else {
                        strErrorMsg =  "No se puede mover de esa manera";
                        legalMove = false; 
                    }
                }else if (color2!= 0){
                     strErrorMsg =  "No se puede mover de esa manera";
                     legalMove = false;
                }
               
            break;
                
            case 2:
                if(desColumn <= startColumn){
                    strErrorMsg = "No se puede mover de esa manera";
                    legalMove = false;
                }else if (desRow != startRow) {
                    if ((desRow > startRow && desRow == (startRow + 1)) || (desRow < startRow && desRow == (startRow - 1))){//DI o DD
                       if(desColumn == (startColumn + 1)){
                            
                            if (playerMatrix[desRow][desColumn] == 0 || color2==color) //0 REPRESENTA QUE NO HAY PIEZA AHÍ, CAMBIAR POR LO QUE SEA
                                {
                                    strErrorMsg = "Solo se puede mover en diagonal cuando se va a capturar una pieza enemiga";
                                    legalMove = false;
                                }
                        } else {//SI SE MUEVE EN DIAGONAL MAS DE UNA POS
                            strErrorMsg = "No se puede mover de esa manera";
                            legalMove = false;
                        }
                    } else {
                        strErrorMsg =  "No se puede mover de esa manera";
                        legalMove = false; 
                    }
                }else if (color2!= 0){
                     strErrorMsg =  "No se puede mover de esa manera";
                     legalMove = false;
                }
              
            break;
                
            case 3:
                if(desRow <= startRow){
                    strErrorMsg = "No se puede mover de esa manera";
                    legalMove = false;
                } else if (desColumn != startColumn) { //SI SE MUEVE EN DIAGONAL
                    if ((desColumn > startColumn && desColumn == (startColumn + 1)) || (desColumn < startColumn && desColumn == (startColumn - 1))){//DI o DD
                       if(desRow == (startRow + 1)){
                            if (playerMatrix[desRow][desColumn] == 0 || color2==color) //0 REPRESENTA QUE NO HAY PIEZA AHÍ, CAMBIAR POR LO QUE SEA
                                {
                                    strErrorMsg = "Solo se puede mover en diagonal cuando se va a capturar una pieza enemiga";
                                    legalMove = false;
                                }
                        } else {//SI SE MUEVE EN DIAGONAL MAS DE UNA POS
                            strErrorMsg = "No se puede mover de esa manera";
                            legalMove = false;
                        }
                    } else {
                        strErrorMsg =  "No se puede mover de esa manera";
                        legalMove = false; 
                    }
                }else if (color2!= 0){
                     strErrorMsg =  "No se puede mover de esa manera";
                     legalMove = false;
                }
               
            break;
                
            case 4:
                if(desColumn >= startColumn){
                    strErrorMsg = "No se puede mover de esa manera";
                    legalMove = false;
                }else if (desRow != startRow) {
                    if ((desRow > startRow && desRow == (startRow + 1)) || (desRow < startRow && desRow == (startRow - 1))){//DI o DD
                       if(desColumn == (startColumn - 1)){
                            
                            if (playerMatrix[desRow][desColumn] == 0 || color2==color) //0 REPRESENTA QUE NO HAY PIEZA AHÍ, CAMBIAR POR LO QUE SEA
                                {
                                    strErrorMsg = "Solo se puede mover en diagonal cuando se va a capturar una pieza enemiga";
                                    legalMove = false;
                                }
                        } else {//SI SE MUEVE EN DIAGONAL MAS DE UNA POS
                            strErrorMsg = "No se puede mover de esa manera";
                            legalMove = false;
                        }
                    } else {
                        strErrorMsg =  "No se puede mover de esa manera";
                        legalMove = false; 
                    }
                }else if (color2!= 0){
                     strErrorMsg =  "No se puede mover de esa manera";
                     legalMove = false;
                }
            break;
        }
        
        return legalMove;   
    } 
}