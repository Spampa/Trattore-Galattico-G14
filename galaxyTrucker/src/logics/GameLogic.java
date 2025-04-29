package logics;

import components.*;
import entities.*;
import eventCards.*;

public class GameLogic {
	private Player[] players;
	private GameLevel level;
	private Deck deck;
	private ComponentPool pool;
	
	public GameLogic(Player[] players, GameLevel level) {
		this.players = players;
		this.level = level;
		
		pool = new ComponentPool();
		deck = new Deck();
	}
	
	public void  play() {
		//TODO creare fase di generazione navi per i player
	}

}
