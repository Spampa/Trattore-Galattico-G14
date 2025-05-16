package logics.phases;

import java.util.ArrayList;

import entities.GameLevel;
import entities.Player;
import logics.GameLogic;
import ui.Graphic;

public class InitPhase extends  Phase {
    private ArrayList<Player> players;
	private GameLevel level;

    public InitPhase(GameLogic game, Graphic graphic){
        super(game, graphic);
    }

	@Override
    public void start() {	
		System.out.println("Benvenuto! \n");
    }

    @Override
    public void update(){
    	level = graphic.setGameLevel();
    	
    	int playerCount = graphic.setPlayerCount();
    	
		players = graphic.setPlayers(playerCount, level);

		game.switchPhase();
    }

    @Override
    public void end() {
		game.setPlayers(players);
		game.setLevel(level);

		graphic.printMessage("Fine fase di inizializazione\n" + "Players:" + players.size() + " " + "livello partita:" + level);
    }

}
