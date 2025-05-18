package eventCards;

import entities.*;
import entities.board.Board;
import ui.Graphic;

public class SlaverShipCard extends Card {
    public SlaverShipCard(GameLevel level, Graphic graphic) {
        super(graphic,
            "Nave Schiavista", 
            "Perdi 1 equipaggio, puoi contrattattaccare.", 
            level);
    }

     @Override
     public void execute(Board b) {
         graphic.printAlert("Nave attacca! perso 1 equipaggio!");
         //setter ship(ship.getHumansCounter() - 1);

        //  if (ship.getFirePower() > 0 /*&& graphic.askUser("Vuoi sparare?") non ho idea del perchè mi dia errore */) {
        //      boolean success = new Random().nextBoolean();
        //      if (success) {
        //          new Shoot(Side.RIGHT, ProjectileType.BIG_CANNON, 
        //                   new Random().nextInt(ship.getGameLevel().getBoardY()))
        //              .getHit(ship);
        //          graphic.printAlert("Colpito! La nave fugge.");
        //      } else {
        //         //  setter ship(ship.getHumansCounter() - 1);
        //         //  metodo per far tornare indietro il player(player, 1);
        //      }
        //  }
     }
}