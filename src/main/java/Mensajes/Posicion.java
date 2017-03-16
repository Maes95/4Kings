/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mensajes;

/**
 *
 * @author Michel
 */
public class Posicion {
    private final int fila;
    private final int columna;

    public Posicion(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }
    
    public boolean sonIguales(Posicion other){
        return (this.fila == other.fila && this.columna == other.columna);
    }

    public int fila(){ return this.fila;}
    public int columna(){ return this.columna;}
}
