package entities;

import java.util.Arrays;

import flightBoard.Board;

public class Player implements Comparable<Player> {

    private static int playerCount = 0;
    private final int playerID;
    private final Ship playerShip;
    private final String playerName;
	private int generalPosition;

    public Player(String playerName, Ship playerShip) {
        if (playerCount >= 4) {
        	System.out.println("Massimo 4 giocatori consentiti.");
        }
        this.playerID = ++playerCount;
        this.playerShip = playerShip;
        this.playerName = playerName;
        this.generalPosition=0;

    }

    public int getPlayerID() {
        return playerID;
    }

    public Ship getPlayerShip() {
        return playerShip;
    }


    public boolean checkPlayer() {
        return playerShip.isPlayable();
    }

    public boolean isInGame(Player[] allPlayers) {
    	if (!checkPlayer()) {
            return false; 
        }
    	 for (Player other : allPlayers) {
             if (other != this && other.isLappedBy(this)) {
                 return false;  
             }
         }
    	 return true;  
    }

    public void increaseGeneralPosition() {
		this.generalPosition++;						//used in board when moving forward	
	}
	
	public void decreaseGeneralPosition() {
		this.generalPosition--;						//used in board when moving backwards
	}
	
	public int getGeneralPosition() {
		return this.generalPosition;
	}

	public void setStartingPosition(int numberOfArrival, Board board) {	//invoked only after ship-building-phase
		generalPosition=4-numberOfArrival;								//sets the position on the starting 
		board.getSpace(generalPosition-1).putPlayer(this);				//fills out the space with corresponding player
	}

    public boolean isLappedBy(Player other) {
        return other.getGeneralPosition() - this.getGeneralPosition() >= Board.NUM_OF_SPACES;
    }

    public String getPlayerName() {
        return playerName;
    }

	@Override
	public int compareTo(Player p) {
		return p.generalPosition-this.generalPosition;
	}
	
	public static void updateOrder(Player[] players) {
		Arrays.sort(players);
	}
}


