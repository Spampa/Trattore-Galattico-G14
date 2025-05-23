package saveload;

import entities.GameLevel;
import entities.Player;
import entities.Position;
import entities.ship.Ship;
import entities.ship.ShipTile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import components.Component;
import components.Connector;
import components.enums.Side;
import logics.GameLogic;

public class SaveLoadManager {

	private final Path path;
	private GameLogic game;
	private final static String PHASE_TAG = "PHASE";
	private final static String GAME_LEVEL_TAG = "LEVEL";
	private final static String PLAYERS_TAG = "PLAYERS";
	private final static String PLAYER_TAG = "PLAYER";
	private final static String SHIP_TAG = "SHIP";
	private final static String ITEM_TAG = "ITEM";
	
	public SaveLoadManager(GameLogic game) throws IOException {
		this.game = game;
		path = Paths.get("./data/save.txt");
		
		Files.createDirectories(path.getParent());
	}
	
	public void loadGame() {
		ArrayList<Player> players = new ArrayList<Player>();
		String lastTag = "";
		ShipTile[][] shipTile = null;
        try {
            List<String> rows = Files.readAllLines(path);
            
            for(String row : rows) {
            	if(TextFormatter.isCloseTag(row)) {
            		switch(TextFormatter.getTag(row)) {
	            		case PLAYERS_TAG: {
	            			game.setPlayers(players);
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
            			shipTile = new ShipTile[level.getBoardY()][level.getBoardX()];
            			break;
            		}
            		case PLAYER_TAG: {
            			String[] fields = row.split(";");
            			players.add(new Player(fields[0], new Ship(game.getLevel(), game.getGraphic())));
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
				    			c.getClass().getSimpleName() + ";" +
				    			x + ";" + y + ";" +
			    				c.getConnector(Side.UP) + ";" +
			    				c.getConnector(Side.RIGHT) + ";" +
			    				c.getConnector(Side.DOWN) + ";" +
			    				c.getConnector(Side.LEFT) + "\n" 
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
	
	private Component getComponent(String s) {
		String[] fields = s.split(";");
		Connector[] c = new Connector[4];
		
		return null;
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
