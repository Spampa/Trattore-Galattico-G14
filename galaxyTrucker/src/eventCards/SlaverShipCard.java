package eventCards;

import ui.Graphic;
import entities.*;
import components.enums.Side;
import gameEvents.Actions.ProjectileType;
import gameEvents.Actions.Shoot;
import java.util.Random;
import flightBoard.Board;

public class SlaverShipCard extends EventCard {
    public SlaverShipCard(Board board, Graphic graphic) {
        super(
            "Nave Schiavista", 
            "Perdi 1 equipaggio, puoi contrattattaccare.", 
            board, graphic
        );
    }

     @Override
     public void executeEvent(Ship ship, Player player) {
         graphic.printAlert("Nave attacca! perso 1 equipaggio!");
         //setter ship(ship.getHumansCounter() - 1);

         if (ship.getFirePower() > 0 /*&& graphic.askUser("Vuoi sparare?") non ho idea del perchè mi dia errore */) {
             boolean success = new Random().nextBoolean();
             if (success) {
                 new Shoot(Side.RIGHT, ProjectileType.BIG_CANNON, 
                          new Random().nextInt(ship.getGameLevel().getBoardY()))
                     .getHit(ship);
                 graphic.printAlert("Colpito! La nave fugge.");
             } else {
                //  setter ship(ship.getHumansCounter() - 1);
                //  metodo per far tornare indietro il player(player, 1);
             }
         }
     }
}