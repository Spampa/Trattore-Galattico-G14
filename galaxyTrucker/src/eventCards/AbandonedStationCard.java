package eventCards;

import entities.*;
import flightBoard.Board;
import ui.Graphic;
import java.util.List;

public class AbandonedStationCard extends EventCard {
    private static final int REQUIRED_HUMANS = 5;
    private static final int REQUIRED_ALIENS = 5;
    private static final int DAYS_LOST = 3;
    private static final int MAX_LOOT = 10; 

    public AbandonedStationCard(Board board, Graphic graphic) {
        super("Stazione Abbandonata", 
              "Bottino disponibile! Richiesti: " + REQUIRED_HUMANS + " umani e " + REQUIRED_ALIENS + " alieni\n" +
              "Merci ottenibili: fino a " + MAX_LOOT + "\n" +
              "Penalità: " + DAYS_LOST + " giorni di volo", 
              board, graphic);
    }

    @Override
    public void executeEvent(Ship currentShip, Player currentPlayer) {
        List<Player> playersInOrder = board.getPlayerByRank();
        
        //  Trova il primo giocatore idoneo/che acetti di usare la carta 
        Player looter = findLooter(playersInOrder);
        
        if (looter == null) {
            graphic.printAlert("Nessun equipaggio qualificato ha voluto saccheggiare!");
            return;
        }

        //  saccheggio
        lootStation(looter, looter.getPlayerShip());
    }

    private Player findLooter(List<Player> players) {
        for (Player player : players) {
            Ship ship = player.getPlayerShip();
            
            if (canLoot(ship)) {
                boolean wantsToLoot = graphic.askUser(player.getPlayerName() + 
                    " vuole saccheggiare? (Umani: " + ship.getHumansCounter() + 
                    "/" + REQUIRED_HUMANS + " Alieni: " + ship.getAliensCounter() + 
                    "/" + REQUIRED_ALIENS + ")");
                
                if (wantsToLoot) return player;
            }
        }
        return null;
    }

    private boolean canLoot(Ship ship) {
        return ship.getHumansCounter() >= REQUIRED_HUMANS && 
               ship.getAliensCounter() >= REQUIRED_ALIENS;
    }

    private void lootStation(Player player, Ship ship) {
        // Calcola quante merci può trasportare 
        int lootAmount = MAX_LOOT; 
        
      
        // Aggiungi merci
        ship.setWaresValue(ship.getWaresValue() + lootAmount);
        
        
        graphic.printAlert(player.getPlayerName() + " ha raccolto " + lootAmount + " merci!");
        graphic.printAlert("Nuovo valore merci: " + ship.getWaresValue());
        
     
        board.moveBack(DAYS_LOST, player);
        graphic.printAlert("Penalità: -" + DAYS_LOST + " giorni di volo");
    }
}