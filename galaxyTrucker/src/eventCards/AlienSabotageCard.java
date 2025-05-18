package eventCards;

import entities.*;
import entities.board.Board;
import ui.Graphic;

public class AlienSabotageCard extends Card {
    public AlienSabotageCard(GameLevel level, Graphic graphic) {
        super(graphic, "Sabotaggio Alieno", 
              "Gli alieni a bordo sabotano un componente casuale!", 
              level);
    }

    @Override
    public void execute(Board b) {

        // if (ship.getAliensCounter() > 0) {
        //     graphic.printAlert("Gli alieni stanno sabotando la nave!");
        // }
            
        /* è un tentativo, non penso funzioni quindi è da ignorare per ora
            boolean bribeAttempt = graphic.askUser("Vuoi corrompere gli alieni con 2 crediti stra mafiosi?");
            
            if (bribeAttempt && player.getCredits() >= 2) {
                player.addCredits(-2);
                graphic.printAlert("Gli alieni se ne vanno");
                return;
            }
            
            ci sarebbero un paio di robe in player da aggiungere relative ai crediti, ma le lascio in stand by per ora.
           
            List<Position> sabotageTargets = new ArrayList<>();
            for (int y = 0; y < ship.getGameLevel().getBoardY(); y++) {
                for (int x = 0; x < ship.getGameLevel().getBoardX(); x++) {
                    if (!ship.getShipComponents()[y][x].isProtectedTile() 
                        && ship.getShipComponents()[y][x].getComponent() != null) {
                        sabotageTargets.add(new Position(x, y));
                    }
                }
            }
            
            if (!sabotageTargets.isEmpty()) {
                Position target = sabotageTargets.get(new Random().nextInt(sabotageTargets.size()));
                ship.getShipComponents()[target.getY()][target.getX()].setComponent(null);
                graphic.printAlert("Componente sabotato alla posizione (" + target.getX() + "," + target.getY() + ")!");
            } else {
                graphic.printAlert("Nessun componente vulnerabile trovato!");
            }
        } else {
            graphic.printAlert("Nessun alieno a bordo - la nave è al sicuro!");
        }*/
    }
}