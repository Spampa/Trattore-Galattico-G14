package eventCards;

import entities.*;
import entities.board.Board;
import entities.ship.Ship;
import java.util.List;
import ui.Graphic;

public class AbandonedStationCard extends Card {
    private static final int REQUIRED_HUMANS = 5;
    private static final int REQUIRED_ALIENS = 5;
    private static final int DAYS_LOST = 3;
    private static final int MAX_LOOT = 10; 

    public AbandonedStationCard(GameLevel level, Graphic graphic) {
        super(graphic, "Stazione Abbandonata", 
              ("Bottino disponibile! Richiesti: " + REQUIRED_HUMANS + " umani e " + REQUIRED_ALIENS + " alieni\n" +
              "Merci ottenibili: fino a " + MAX_LOOT + "\n" +
              "Penalità: " + DAYS_LOST + " giorni di volo"),  level);
    }

    //TODO Fixare con il metodo start()
    @Override
    public void execute(Board b) {
        List<Player> playersInOrder = b.getPlayers();
        
        //  Trova il primo giocatore idoneo/che acetti di usare la carta 
        Player looter = findLooter(playersInOrder);
        
        if (looter == null) {
            graphic.printAlert("Nessun equipaggio qualificato ha voluto saccheggiare!");
            return;
        }

        //  saccheggio
        lootStation(looter, looter.getShip());
    }

    private Player findLooter(List<Player> players) {
        for (Player player : players) {
            Ship ship = player.getShip();
            
            if (canLoot(ship)) {
                boolean wantsToLoot = graphic.askUser(player.getName() + 
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
        
      
        // TODO rifare seguendo le logiche della ship
        // ship.setWaresValue(ship.getWaresValue() + lootAmount);
        
        
        // graphic.printAlert(player.getPlayerName() + " ha raccolto " + lootAmount + " merci!");
        // graphic.printAlert("Nuovo valore merci: " + ship.getWaresValue());
        
     
        // board.moveBack(DAYS_LOST, player);
        // graphic.printAlert("Penalità: -" + DAYS_LOST + " giorni di volo");
    }
}