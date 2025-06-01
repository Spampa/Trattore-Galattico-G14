package eventCards;

import entities.*;
import entities.board.Board;
import entities.ship.Ship;
import ui.Graphic;

import java.util.ArrayList;

public class OpenSpaceCard extends Card {

    public OpenSpaceCard(GameLevel level, Graphic graphic) {
        super(graphic, "Spazio Aperto", 
              "Ogni giocatore può guadagnare giorni di volo in base alla potenza dei suoi motori.", 
              level, 0);
    }

    @Override
    public void execute(Board board) {
        ArrayList<Player> players = board.getPlayers(); 

        
        graphic.printMessage("Ogni giocatore avanza in ordine di rotta in base alla propria potenza motrice.");

        for (Player player : players) {
            Ship ship = player.getShip();
            graphic.printMessage("🚀 " + player.getName() + ", tocca a te.");

            int power = ship.getMotorPower(); 
            if (power == 0) {
                graphic.printAlert(player.getName() + " non ha potenza motrice disponibile.");
                continue;
            }

            board.moveForward(power, player);  
            graphic.printMessage(player.getName() + " avanza di " + power + " spazi.");
        }
    }
}