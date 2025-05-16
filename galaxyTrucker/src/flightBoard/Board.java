package flightBoard;

import java.util.ArrayList;
import java.util.Collections;

import entities.GameLevel;
import entities.Player;

public class Board {
	public final int numberOfSpaces;  					//flight board has 18 spaces
	private Space[] spaces;
	private ArrayList<Player> players;
	
	public Board(ArrayList<Player> players, GameLevel level) {
		this.numberOfSpaces = level.getBoardSpaces();
		this.players = players;
		
		spaces = new Space[numberOfSpaces];
		
		for(int i=0; i<numberOfSpaces; i++) {
			this.spaces[i] = new Space(i);   					//creates a board with 18 spaces
		}
		
		switch(level) { //TODO: other position for other levels
			case GameLevel.I -> {
				this.movePlayer(0, players.get(0));
				this.movePlayer(-2, players.get(1));
				if(players.size() >= 3) this.movePlayer(-3, players.get(2));
				if(players.size() == 4) this.movePlayer(-4, players.get(3));
			}
			case GameLevel.II -> {
				this.movePlayer(0, players.get(0));
				this.movePlayer(-2, players.get(1));
				if(players.size() >= 3) this.movePlayer(-3, players.get(2));
				if(players.size() == 4) this.movePlayer(-4, players.get(3));
			}
			case GameLevel.III -> {
				this.movePlayer(0, players.get(0));
				this.movePlayer(-2, players.get(1));
				if(players.size() >= 3) this.movePlayer(-3, players.get(2));
				if(players.size() == 4) this.movePlayer(-4, players.get(3));
			}
			default -> throw new IllegalArgumentException("Unexpected value: " + level);
		}
	}
	
	public Space getSpace(int i) {
		return this.spaces[i];
	}
	
	public void moveForward(int moves, Player player) {
		if(!players.contains(player)) return; //thorw error
		spaces[this.calculateIndex(player)].removePlayer();
		this.movePlayer(moves, player);
		Collections.sort(players);
	}
	
	public void moveBack(int moves, Player player) {
		if(!players.contains(player)) return; //thorw error
		spaces[this.calculateIndex(player)].removePlayer();
		this.movePlayer(- moves, player);
		Collections.sort(players);
	}
	
	private void movePlayer(int moves, Player player) {
		player.incrementMoves(moves);
		
		while(spaces[this.calculateIndex(player)].getPlayer() != null) {
			if(moves >= 0) player.incrementMoves(1);
			else player.incrementMoves(-1);
		}
		
		spaces[this.calculateIndex(player)].setPlayer(player);
	}
	
	private int calculateIndex(Player player) {
		int index = player.getMoves() % numberOfSpaces;
		if(index < 0 ) {
			index = numberOfSpaces + index;
		}
		return index;
	}
	
	public Player getFirstPlayer() {
		return players.getFirst();
	}
	
	public Player getLastPlayer() {
		return players.getLast();
	}
	
	public Player getPlayerByRank(int rank) {
		if(rank < 0 || rank >= players.size()) return null; //throw error
		return players.get(rank);
	}
	
	public int getNumberOfSpaces() {
		return numberOfSpaces;
	}
}
