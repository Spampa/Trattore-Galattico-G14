package eventCards;

import entities.*;
import entities.board.Board;
import java.util.ArrayList;
import java.util.Collections;
import ui.Graphic;

public class StarDustCard extends Card {

    public StarDustCard(Graphic graphic) {
        super(graphic,
              "Polvere Stellare",
              "Ogni giocatore perde " + 1 + " giorno di volo per ogni connettore esposto!");
    }

    @Override
    public void execute(Board board) {
    	Collections.reverse(board.getPlayers());
    	Player[] players = board.getPlayers().toArray(new Player[0]);

        graphic.printMessage("Evento speciale: Polvere Stellare!");

        for (Player player : players) {
            int daysToLose = player.getShip().getVoidConnectors();

            if (daysToLose > 0) {
                graphic.printMessage(player.getName() + " ha " + daysToLose +
                                     " connettori esposti e perde " + daysToLose + " giorni di volo.");
                super.lostFlyDays(board, player);
            } else {
                graphic.printMessage(player.getName() + " non ha connettori esposti. Nessuna perdita di volo.");
            }
        }
    }
}
