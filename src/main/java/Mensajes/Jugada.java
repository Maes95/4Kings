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
public class Jugada {

    public enum Promocion{CABALLO,ELEFANTE, NO_PROMO};
    
    
    private final Movimiento mov;
    private boolean reenviada;
    private String color;
    private int[] score;
    
    // PARA LA PROMOCIÓN DEPEONES
    private Promocion tipoPromocion;
    
    
    public Jugada(Movimiento m){
        reenviada = false;
        mov = m;
        tipoPromocion = Promocion.NO_PROMO;
        color = "";
    }

    public void setReSended(){
        reenviada = true;
    }
    
    public boolean wasReSended(){
        return reenviada;
    }
    
    public Movimiento getMov(){
        return mov;
    }

    public Promocion getTipoPromocion() {
        return tipoPromocion;
    }

    public void setTipoPromocion(Promocion tipoPromocion, int id) {
        this.tipoPromocion = tipoPromocion;
        switch(id){
            case 1: color = "amarillo";
            break;
            case 2: color = "azul";
            break;
            case 3: color = "verde";
            break;
            case 4: color = "rojo";
            break;
        }
    }
    
    public String getColor(){return color;}
    
    public void setScore(int[] s){
        final int[] b  = s;
        if (b.equals(s)) score = s;
    }
    
    public int[] getScore(){
        return score;
    }
    
    
}
