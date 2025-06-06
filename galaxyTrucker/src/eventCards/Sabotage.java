package eventCards;

import components.Component;
import entities.Dices;
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

public class Sabotage extends Card {
    
    public Sabotage(Graphic graphic) {
        super(graphic, "Sabotaggio", 
             "I sabotatori provano a distruggere un componente casuale sulla tua nave!");    
    }

    @Override
    public void execute(Board board) {
    	Player p=findTarget(board);
    	
    	handleSabotage(p);
    	
    	graphic.printAlert("Evento" + super.getName() + " terminato!...");
        graphic.waitForUser("primo per continuare...");    

        /*for (Player player : board.getPlayers()) {
            Ship ship = player.getShip();
            
            if (ship.getAliensCounter() >= MIN_ALIENS_TO_TRIGGER) {
                handleSabotage(ship, player);
            } else {
                graphic.printMessage(player.getName() + ": Nessun alieno a bordo");
            }
        }*/
    }
    
    private Player findTarget(Board board) {
    	int min=999;
    	Player target=null;
    	for (Player player : board.getPlayers()) {
            Ship ship = player.getShip();
            if(ship.getSpacemans()<min || (ship.getSpacemans()==min && player.compareTo(target)<0)) {
            	min=ship.getSpacemans();
            	target=player;
            } 
        }
    	graphic.printMessage(target.getName() + ": Sei il bersaglio dei sabotatori!");
    	return target;
    }

    private void handleSabotage(Player player) {
        graphic.printAlert("Primo tentativo di sabotaggio!");
        Ship ship=player.getShip();
        Dices dices=new Dices();
        int row,column;
        
        for(int i=1; i<=3;i++) {
        	row=dices.roll();
        	column=dices.roll();
        	graphic.printMessage("Riga: "+row+" Colonna: "+column);		//faccio -4 perchè usiamo un offeset di 4 rispetto al gioco fisico
        	if((row-4<ship.getGameLevel().getBoardY()&&column-4<ship.getGameLevel().getBoardX()) ||(row -4>=0 && column-4>=0)) {
        		if(ship.getShipComponets()[row][column].getComponent()!=null) {
        			ship.breakComponent(new Position(column,row));
        		}
        		else {
        			if(i!=3) graphic.printMessage("Tentativo numero "+i+ " fallito. Nuovo tentativo!");
        			else graphic.printMessage("Tentaivo numero 3 fallito. I sabotatori si arrendono!");
        		}
        	}
        	else {
        		graphic.printMessage("Posizione fuori dalla placia nave! Ti è andata bene...");        	}
        }
        
        /*List<Position> validTargets = findSabotageTargets(ship);
        if (!validTargets.isEmpty()) {
            Position target = selectRandomTarget(validTargets);
            executeSabotage(ship, target, player);
        } else {
            graphic.printMessage("Nessun componente vulnerabile trovato");
        }*/
        
        graphic.printAlert("Evento" + super.getName() + " terminato!...");
        graphic.waitForUser("premi per continuare...");    

    }

    /*private List<Position> findSabotageTargets(Ship ship) {
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
    }*/
}