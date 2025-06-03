package eventCards;

import components.Component;
import entities.GameLevel;
import entities.Player;
import entities.Position;
import entities.board.Board;
import entities.ship.Ship;
import entities.ship.ShipTile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ui.Graphic;

public class AlienSabotageCard extends Card {
    private static final int MIN_ALIENS_TO_TRIGGER = 1;
    
    public AlienSabotageCard(Graphic graphic) {
        super(graphic, "Sabotaggio Alieno", 
             "Gli alieni sabotano componenti casuali", 
             GameLevel.III);    
    }

    @Override
    public void execute(Board board) {
        for (Player player : board.getPlayers()) {
            Ship ship = player.getShip();
            
            if (ship.getAliensCounter() >= MIN_ALIENS_TO_TRIGGER) {
                handleSabotage(ship, player);
            } else {
                graphic.printMessage(player.getName() + ": Nessun alieno a bordo");
            }
        }
    }

    private void handleSabotage(Ship ship, Player player) {
        graphic.printAlert("Alieno" + player.getName() + " sta sabotando!");
        
        List<Position> validTargets = findSabotageTargets(ship);
        if (!validTargets.isEmpty()) {
            Position target = selectRandomTarget(validTargets);
            executeSabotage(ship, target, player);
        } else {
            graphic.printMessage("Nessun componente vulnerabile trovato");
        }
    }

    private List<Position> findSabotageTargets(Ship ship) {
        List<Position> targets = new ArrayList<>();
        ShipTile[][] grid = ship.getShipComponets(); 
        
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (isValidTarget(grid[y][x])) {
                    targets.add(new Position(x, y));
                }
            }
        }
        return targets;
    }

    private boolean isValidTarget(ShipTile tile) {
        return tile.getComponent() != null;
    }

    private Position selectRandomTarget(List<Position> targets) {
        return targets.get(new Random().nextInt(targets.size()));
    }

    private void executeSabotage(Ship ship, Position target, Player player) {
        Component sabotaged = ship.getShipComponets()[target.getY()][target.getX()].getComponent();
        ship.getShipComponets()[target.getY()][target.getX()].setComponent(null);
        
        graphic.printAlert("‼ " + sabotaged.getClass().getSimpleName() + 
                         " sabotato a (" + target.getX() + "," + target.getY() + ")");
    }
}