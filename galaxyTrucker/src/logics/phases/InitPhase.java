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
    	cli.clear();
    	
    	int playerCount = cli.setPlayerCount();
    	cli.clear();
    	
		players = cli.setPlayers(playerCount, level);
		cli.clear();

		game.switchPhase();
    }

    @Override
    public void end() {
		game.setPlayers(players);
		game.setLevel(level);

		cli.printMessage("Fine fase di inizializazione");
		cli.printMessage("players:" + players.length + " " + "livello partita:" + level);
		cli.clear();
    }

}
