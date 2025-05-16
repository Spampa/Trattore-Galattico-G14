package entities;

public class Player implements Comparable<Player> {

    private final Ship playerShip;
    private final String playerName;
	private int moves;

    public Player(String playerName, Ship playerShip) {
        this.playerShip = playerShip;
        this.playerName = playerName;
        this.moves = 0;
    }

    public Ship getPlayerShip() {
        return playerShip;
    }

    public boolean checkPlayer() {
        return playerShip.isPlayable();
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

    public String getPlayerName() {
        return playerName;
    }

	@Override
	public int compareTo(Player p) {
		if(this.moves > p.moves) return 1;
		if(this.moves < p.moves) return -1;
		return 0;
	}
	
	@Override
	public String toString() {
		return playerName + ", moves: " + moves;
	}
}


