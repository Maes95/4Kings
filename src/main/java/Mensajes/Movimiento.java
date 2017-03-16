/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mensajes;

import java.io.Serializable;

/**
 *
 * @author Michel
 */
public class Movimiento implements Serializable {

    public final Posicion ORIG;
    public final Posicion DEST;
    public final int id;

    public Movimiento(Posicion o,Posicion n, int i){
        ORIG = o;
        DEST = n;
        id = i;
    }
    
    @Override
    public String toString(){
        String fOrig = (ORIG.fila()+1)+"";
        String cOrig = letra(ORIG.columna());
        String fDest = (DEST.fila()+1)+"";
        String cDest = letra(DEST.columna());
        
        return cOrig+fOrig+" -> "+cDest+fDest;
    }
        
    private String letra(int i){
        switch(i){
            case 0: return "a";
            case 1: return "b";
            case 2: return "c";
            case 3: return "d";
            case 4: return "e";
            case 5: return "f";
            case 6: return "g";
            case 7: return "h";
            default: return "";
        }
    }
        
}
