package FourKings;

import Mensajes.Jugada;
import Mensajes.PlayGame;
import Mensajes.TeToca;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import java.util.List;

/**
 *
 * @author Michel
 */
public class Master extends UntypedActor {

    protected final List<ActorRef> players;
    protected int nextPlayer;
    
    public Master(List<ActorRef> players) {
        this.players = players;
        nextPlayer = 0;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof PlayGame) {
            nextPlayer();
        }else if (msg instanceof Jugada) {
            Jugada j = (Jugada) msg;
            j.setReSended();
            for(ActorRef jug :players){if (jug != getSender()) jug.tell(j, getSelf());}
            nextPlayer();
        }else {
            unhandled(msg);
        }
    }
    
    public void nextPlayer(){
        players.get(nextPlayer).tell(new TeToca(), getSelf());
        nextPlayer = (1 + nextPlayer) % 4;
    }
    
}
