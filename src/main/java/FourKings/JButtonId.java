/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourKings;

import Mensajes.Posicion;
import javax.swing.JButton;

/**
 *
 * @author Pablo
 */
public class JButtonId extends JButton{
    
    private Posicion posicion;
    private boolean ocupado;
    
    public JButtonId (int fila, int columna){
        this.posicion = new Posicion(fila, columna);
    }
    
    public Posicion getPosicion(){
        return posicion;
    }
    
    public boolean getOcupado(){
        return this.ocupado;
    }
    
    public void setOcupado(boolean b){
        this.ocupado = b;
    }
    
}
