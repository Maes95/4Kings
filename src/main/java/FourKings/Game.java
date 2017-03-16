package FourKings;

import Mensajes.NewGame;
import Mensajes.PlayGame;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michel
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Game().startGame();
    }
    
    final List<ActorRef> players = new ArrayList<>();
    
    void startGame() {
        
        ActorSystem system = ActorSystem.create("Game");
        final ActorRef listener = system.actorOf(new Props(Listener.class), "listener");
        
        // LOCAL
        for (int i = 0; i < 4; i++){
            ActorRef player = system.actorOf(new Props(() -> {
                return new Player(listener);
            }), "Player_"+i);
            
            players.add(i, player);
            
            GraphicBoard panel = new GraphicBoard(player, i);   
        }
        // FIN DISTRIBUIDO

        // DISTRIBUIDO
        
        
        // FIN DISTRIBUIDO
        
        ActorRef master = system.actorOf(new Props(() -> {
            Master mast = new Master(players);
            return mast;
        }), "master");
        
        
        
        master.tell(new PlayGame());
        listener.tell(new NewGame());
    }
    
}
