package entities;

import entities.ship.Ship;

public class Player implements Comparable<Player> {

    private final Ship ship;
    private final String name;
	private int moves;

    public Player(String name, Ship ship) {
        this.ship = ship;
        this.name = name;
        this.moves = 0;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean checkPlayer() {
        return ship.isPlayable();
    }
    
    public void setMoves(int moves) {
    	this.moves = moves;
    }

    public void incrementMoves(int moves) {
		this.moves += moves;						//used in board when moving forward	
	}
	
	public int getMoves() {
		return this.moves;
	}

    public String getName() {
        return name;
    }

	@Override
	public int compareTo(Player p) {
		if(this.moves > p.moves) return 1;
		if(this.moves < p.moves) return -1;
		return 0;
	}
	
	@Override
	public String toString() {
		return name + ", moves: " + moves;
	}
}


