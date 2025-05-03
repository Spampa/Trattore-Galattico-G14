package logics.phases;

import entities.GameLevel;
import entities.Player;
import entities.Ship;
import logics.GameLogic;

import java.util.Scanner;

public class InitPhase extends  Phase {
    private Player[] players;
	private GameLevel level;

    public InitPhase(GameLogic game){
        super(game);
    }

	@Override
    public void start() {	
		System.out.println("Benvenuto! \n");
    }

    @Override
    public void update(){
    	level = cli.setGameLevel();
    	
    	int playerCount = cli.setPlayerCount();
    	
		players = cli.setPlayers(playerCount, level);

		game.switchPhase();
    }

    @Override
    public void end() {
		game.setPlayers(players);
		game.setLevel(level);

		cli.printMessage("Fine fase di inizializazione\n" + "Players:" + players.length + " " + "livello partita:" + level);
    }

}
