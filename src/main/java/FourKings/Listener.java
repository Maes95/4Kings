package FourKings;

import Mensajes.Jugada;
import Mensajes.Jugada.Promocion;
import Mensajes.Movimiento;
import Mensajes.NewGame;
import Mensajes.Notificacion;
import Mensajes.Posicion;
import Piezas.*;
import Piezas.Pieza;
import akka.actor.UntypedActor;

/**
 *
 * @author Michel
 */
public class Listener extends UntypedActor {
    
    private int[][] tablero = new int[8][8];
    private Pieza[] piezas = new Pieza[6];
    private int[] puntuaciones = new int[4];
    
    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Movimiento) {
            Movimiento m = (Movimiento) msg;
            // COMPROBAR VALIDEZ DEL MOVIMIENTO
            Posicion origen = m.ORIG;
            int piezaOrigen = tablero[origen.fila()][origen.columna()];
            Boolean b = piezas[piezaOrigen % 10].validadMovimiento(m.ORIG.fila(), m.ORIG.columna(), m.DEST.fila(), m.DEST.columna(), tablero, m.id);       
            if (b && (piezaOrigen / 10 == m.id)){
                Jugada j = new Jugada(m);
                System.out.println("ANTES");
                this.impMat();
                realizarMovimiento(j);
                System.out.println("DESPUES");
                this.impMat();
                j.setScore(puntuaciones);
                getSender().tell(j); 
            }else{
                getSender().tell(new Notificacion("Tu jugada ha sido NO válida \n"));
            }
        }else if(msg instanceof NewGame){  
            nuevoTablero();
        }else {
            unhandled(msg);
        }
    }
    
    // SOLO SE LLAMA CUANDO SE RECIBE UNA JUGADA (MOVIMIENTO VERIFICADO)
    private void realizarMovimiento(Jugada jug){
        // BASICO -> REALIZAMOS MOVIMIENTO
        Posicion origen = jug.getMov().ORIG;
        Posicion destino = jug.getMov().DEST;
        int piezaOrigen = tablero[origen.fila()][origen.columna()];
        int piezaDestino = tablero[destino.fila()][destino.columna()];
        tablero[origen.fila()][origen.columna()] = 0;
        tablero[destino.fila()][destino.columna()] = piezaOrigen;
        if(piezaDestino!=0){//TE HAS COMIDO UNA PIEZA
            int valorGanado = piezaDestino%10;
            puntuaciones[(piezaOrigen/10)-1]+=valorGanado;
        }
        // AVANZADO -> CONTEMPLAR CASOS
        
        //PROMOCIÓN PEÓN
        if(piezaOrigen%10==1){ // ES UN PEON
            int dest = destino.fila()*10+destino.columna();
            switch(dest){
                case 72:case 20:case 05:case 57:
                    tablero[destino.fila()][destino.columna()] = piezaOrigen+3;
                    jug.setTipoPromocion(Promocion.ELEFANTE,piezaOrigen/10);
                    break;
                case 10:case 06:case 67:case 71:
                    tablero[destino.fila()][destino.columna()] = piezaOrigen+2;
                    jug.setTipoPromocion(Promocion.CABALLO,piezaOrigen/10);
                    break;
            }
        }
        
        // VICTORIA NAVAL
        
        if(piezaOrigen%10==2){ // ES UN BARCO
            
        }
        
        // TOMAR UN TRONO
        
        if(piezaOrigen%10==2){ // ES UN REY
            
        }
    }
    
    // CREA UN NUEVO TABLERO CON TODAS LAS PIEZAS COLOCADAS
    private void nuevoTablero(){
        int[] f0 = {22,21,00,00,35,34,33,32};
        int[] f1 = {23,21,00,00,31,31,31,31};
        int[] f2 = {24,21,00,00,00,00,00,00};
        int[] f3 = {25,21,00,00,00,00,00,00};
        int[] f4 = {00,00,00,00,00,00,41,45};
        int[] f5 = {00,00,00,00,00,00,41,44};
        int[] f6 = {11,11,11,11,00,00,41,43};
        int[] f7 = {12,13,14,15,00,00,41,42};
        int[][] matriz = {f0,f1,f2,f3,f4,f5,f6,f7};
        tablero = matriz;
        piezas[0] = null;
        piezas[1] = new Peon();
        piezas[2] = new Barco();
        piezas[3] = new Caballo();
        piezas[4] = new Elefante();
        piezas[5] = new Rey();
        for(int i : puntuaciones){
            puntuaciones[i]=0;
        }
        
    }
    
    public void impMat(){
        for(int[] columna :tablero){
            for(int celda :columna){
                if (celda == 0){System.out.print("[00]");}
                else{System.out.print("["+celda+"]");}
            }
            System.out.println();
        }
    }


}
