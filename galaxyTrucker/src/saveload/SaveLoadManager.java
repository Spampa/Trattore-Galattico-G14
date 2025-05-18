package saveload;

import entities.GameLevel;
import entities.Player;
import entities.ship.Ship;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import logics.GameLogic;

public class SaveLoadManager {

	private final Path path;
	private GameLogic game;
	private final static String SEPARATOR = "***";
	
	public SaveLoadManager(GameLogic game) throws IOException {
		this.game = game;
		path = Paths.get("./data/save.txt");
		
		Files.createDirectories(path.getParent());
	}
	
	public void loadGame() {
		int separatorIndex = 0;
		ArrayList<Player> players = new ArrayList<Player>();
        try {
            List<String> rows = Files.readAllLines(path);
            
            for(String row : rows) {
            	if(!row.equals(SEPARATOR)) {
            		
            		switch(separatorIndex) {
	            		case 0: {
	            			game.setPhaseIndex(Integer.parseInt(row));
	            			break;
	            		}
	            		case 1: {
	            			game.setLevel(GameLevel.stringToLevel(row));
	            			break;
	            		}
	            		case 2: {
	            			String[] fields = row.split(";");
	            			players.add(new Player(fields[0], new Ship(game.getLevel(), game.getGraphic())));
	            			break;
	            		}
	            		default: break;
            		}
            		
            	}
            	else {
                	if(separatorIndex == 2) {
                		game.setPlayers(players);
                	}
                	separatorIndex++;
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void saveGame() throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
		    writer.write(game.getPhaseIndex() + "\n");
		    writer.write(SEPARATOR + "\n");
		    
		    writer.write(game.getLevel().toString() + "\n");
		    
		    writer.write(SEPARATOR + "\n");
		    for(Player player : game.getPlayers()) {
		    	writer.write(player.getName() + ";" + player.getMoves() + "\n");
		    }
		    writer.write(SEPARATOR + "\n");
		}
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
