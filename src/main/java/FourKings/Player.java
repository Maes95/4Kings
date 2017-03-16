package FourKings;

import Mensajes.Jugada;
import Mensajes.Movimiento;
import Mensajes.Notificacion;
import Mensajes.TeToca;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 *
 * @author Michel
 */
public class Player extends UntypedActor {
    
    private static int nextID = 1; // Lleva la cuenta de los jugadores creados
    private int ID;
    private boolean myTurn;
    private GraphicBoard myGraphicBoard;
    private final ActorRef myListener;
    private ActorRef myMaster;
    
    
    public Player(ActorRef l) {
        myListener = l;
        myMaster = null;
        ID = nextID;
        nextID++;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg instanceof TeToca){ // Mensaje del Master
            myTurn = true;
            myGraphicBoard.myTurn();
            myMaster = getSender();
        }else if (msg instanceof GraphicBoard) { // Mensaje de JFrame (Referencia a TextArea)
            myGraphicBoard = (GraphicBoard) msg;
        }else if (msg instanceof Notificacion) { // Mensaje enviado por Listener 
            Notificacion n = (Notificacion) msg;
            myGraphicBoard.appendText(n.getNot());
            myTurn=true;
            myGraphicBoard.myTurn();
        }else if (msg instanceof Jugada) { // Mensaje enviado del Listener o re-enviado por Master
            Jugada j = (Jugada) msg;
            myGraphicBoard.appendText("Jugador "+j.getMov().id+" movió "+j.getMov().toString()+"\n");
            if(!j.wasReSended()){
                myMaster.tell(j, getSelf());
                myGraphicBoard.actualizarTablero(j);
            }else{
                myGraphicBoard.actualizarTablero(j);
            }
        }else if(msg instanceof Movimiento){ // Mensaje del JFrame (Jugada)
            if (myTurn){
                myListener.tell(msg, getSelf()); // SERA MOVIMIENTO
                myTurn = false;
                myGraphicBoard.notMyTurn();
            }else{myGraphicBoard.appendText("No es tu turno campeón \n");} 
        }else {
            unhandled(msg);
        }
    }
    
    
    public void setID(int i){
        ID = i;
    }
}
