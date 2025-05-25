package eventCards;

import entities.*;
import entities.board.Board;
import ui.Graphic;

import java.util.ArrayList;
import java.util.Collections;

public class StarDustCard extends Card {

    public StarDustCard(GameLevel level, Graphic graphic) {
        super(graphic,
              "Polvere Stellare",
              "Ogni giocatore perde " + 1 + " giorno di volo per ogni connettore esposto!",
              level,
              1);
    }

    @Override
    public void execute(Board board) {
        ArrayList<Player> players = new ArrayList<>(board.getPlayers());
        Collections.reverse(players); 

        graphic.printMessage("Evento speciale: Polvere Stellare!");

        for (Player player : players) {
            int exposedConnectors = player.getShip().getVoidConnectors();
            int daysToLose = getFlyDays() * exposedConnectors;

            if (exposedConnectors > 0) {
                graphic.printMessage(player.getName() + " ha " + exposedConnectors +
                                     " connettori esposti e perde " + daysToLose + " giorni di volo.");
                board.moveBack(daysToLose, player);
            } else {
                graphic.printMessage(player.getName() + " non ha connettori esposti. Nessuna perdita di volo.");
            }
        }
    }
}
