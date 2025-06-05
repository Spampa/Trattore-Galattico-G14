package saveload;

import entities.GameLevel;
import entities.Player;
import entities.Position;
import entities.board.Board;
import entities.ship.Ship;
import entities.ship.ShipTile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import components.Component;
import components.Connector;
import components.enums.ContainerSize;
import components.enums.MountType;
import components.enums.Side;
import components.models.*;
import components.models.containers.*;
import logics.GameLogic;

public class SaveLoadManager {

	private final Path path;
	private GameLogic game;
	private final static String PHASE_TAG = "PHASE";
	private final static String GAME_LEVEL_TAG = "LEVEL";
	private final static String PLAYERS_TAG = "PLAYERS";
	private final static String PLAYER_TAG = "PLAYER";
	private final static String SHIP_TAG = "SHIP";
	
	public SaveLoadManager(GameLogic game) throws IOException {
		this.game = game;
		path = Paths.get("./data/save.txt");
		
		Files.createDirectories(path.getParent());
	}
	
	public void loadGame() {
		ArrayList<Player> players = new ArrayList<Player>();
		String lastTag = "";
		
		String playerName = "";
		int playerMoves = 0;
		
		ShipTile[][] shipTile = null;
		Board board = null;
		
        try {
            List<String> rows = Files.readAllLines(path);
            
            for(String row : rows) {
            	if(TextFormatter.isCloseTag(row)) {
            		switch(TextFormatter.getTag(row)) {
	            		case PLAYERS_TAG: {
	            			game.setPlayers(players);
	            			board = new Board(players, game.getLevel());
	            			game.setBoard(board);
	            			continue;
	            		}
	            		case PLAYER_TAG: {
	            			Player player = new Player(playerName, new Ship(game.getLevel(), game.getGraphic(), shipTile));
	            			shipTile = Ship.getShipLevelBoard(game.getLevel());
	            			player.setMoves(playerMoves);
	            			players.add(player);
	            			continue;
	            		}
	            		default: continue;
            		}
            	}
            	else if(TextFormatter.isTag(row)){
            		lastTag = TextFormatter.getTag(row);
            		continue;
            	}
            	
            	switch(lastTag) {
            		case PHASE_TAG: {
            			game.setPhaseIndex(Integer.parseInt(row));
            			break;
            		}
            		case GAME_LEVEL_TAG: {
            			GameLevel level = GameLevel.stringToLevel(row);
            			game.setLevel(level);
            			shipTile = Ship.getShipLevelBoard(level);

            			break;
            		}
            		case PLAYER_TAG: {
            			String[] fields = row.split(";");
            			playerName = fields[0];
            			playerMoves = Integer.parseInt(fields[1]);
            			break;
            		}
            		case SHIP_TAG: {
            			String[] fields = row.split(";");
            			Position p = new Position(Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
            			
            			if(shipTile == null) {
            				throw new NullPointerException("Level not saved!");
            			}
            			
            			shipTile[p.getY()][p.getX()].setComponent(this.getComponent(row));
            			break;
            		}
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void saveGame() throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(TextFormatter.createOpenTag(PHASE_TAG) + "\n");
		    writer.write(game.getPhaseIndex() + "\n");
		    writer.write(TextFormatter.createCloseTag(PHASE_TAG) + "\n");
		    
		    writer.write(TextFormatter.createOpenTag(GAME_LEVEL_TAG) + "\n");
		    writer.write(game.getLevel().toString() + "\n");
		    writer.write(TextFormatter.createCloseTag(GAME_LEVEL_TAG) + "\n");
		    
		    writer.write(TextFormatter.createOpenTag(PLAYERS_TAG) + "\n");
		    for(Player player : game.getPlayers()) {
		    	writer.write(TextFormatter.createOpenTag(PLAYER_TAG) + "\n");
		    	writer.write(player.getName() + ";" + player.getMoves() + "\n");
		    	
		    	ShipTile[][] ship = player.getShip().getShipComponets();
		    	writer.write(TextFormatter.createOpenTag(SHIP_TAG) + "\n");
		    	for(int y = 0; y < ship.length; y++) {
		    		for(int x = 0; x < ship[0].length; x++) {
		    			Component c = ship[y][x].getComponent();
		    			if(c != null) {
			    			writer.write(
			    				this.stringComponent(c, x, y)
				    		);
		    			}
		    		}
		    	}
		    	writer.write(TextFormatter.createCloseTag(SHIP_TAG) + "\n");
		    	writer.write(TextFormatter.createCloseTag(PLAYER_TAG) + "\n");
		    }
		    writer.write(TextFormatter.createCloseTag(PLAYERS_TAG) + "\n");
		}
	}
	
	private String stringComponent(Component component, int x, int y) {
		String s = (component.getClass().getSimpleName() + ";" +
    			x + ";" + y + ";" +
    			component.getConnector(Side.UP) + ";" +
    			component.getConnector(Side.RIGHT) + ";" +
    			component.getConnector(Side.DOWN) + ";" +
    			component.getConnector(Side.LEFT) + ";" + 
    			component.getOrientation() + ";");
		
		if(component instanceof Cannon) {
			s += ((Cannon) component).getType();
		}
		else if(component instanceof Engine) {
			s += ((Engine) component).getType();
		}
		else if(component instanceof Container) {
			switch(component) {
				case BatteryStorage container -> {
					s += container.getContainerSize() + ";";
					break;
				}
				case NormalWareStorage container -> {
					s += container.getContainerSize() + ";";
					break;
				}
				case SpecialWareStorage container -> {
					s += container.getContainerSize() + ";";
					break;
				}
				default -> {
					s += ";";
					break;
				}
			}
			s += ((Container) component).getCurrentCapacity();
		}
		
		s += "\n";
		return s;
	}
	
	private Component getComponent(String s) {
		String[] fields = s.split(";");
		Connector[] connectors = new Connector[4];
		
		connectors[0] = Connector.valueOf(fields[3]);
		connectors[1] = Connector.valueOf(fields[4]);
		connectors[2] = Connector.valueOf(fields[5]);
		connectors[3] = Connector.valueOf(fields[6]);
		
		
		Side orientation = Side.valueOf(fields[7]);
		
		String componentName = fields[0];
		
		Component component;
		switch(componentName) {
			case "Pipe": {
				component = new Pipe(connectors);
				break;
			}
			case "Shield": {
				component = new Shield(connectors);
				break;
			}
			case "Cannon": {
				component = new Cannon(MountType.valueOf(fields[8]),connectors);
				break;
			}
			case "Engine": {
				component = new Engine(MountType.valueOf(fields[8]),connectors);
				break;
			}
			case "BatteryStorage": {
				component = new BatteryStorage(ContainerSize.valueOf(fields[8]), connectors, Integer.parseInt(fields[9]));
				break;
			}
			case "SpacemanUnit": {
				component = new SpacemanUnit(connectors, true, Integer.parseInt(fields[9]));
				break;
			}
			case "NormalWareStorage": {
				component = new NormalWareStorage(ContainerSize.valueOf(fields[8]), connectors);
				break;
			}
			case "SpecialWareStorage": {
				component = new SpecialWareStorage(ContainerSize.valueOf(fields[8]), connectors);
				break;
			}
			default: {
				component = null;
				break;
			}
		}
		
		return component;
	}
	
	public boolean isVoid() {
        try {
        	Files.readAllLines(path);
            return false;
        } catch (IOException e) {
            return true;
        }
	}

}
