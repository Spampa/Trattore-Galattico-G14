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
	
	public int getPosition() {
		return this.generalPosition;
	}
	
	public void setPosition(int numberOfArrival) {
		if(numberOfArrival==1) {
			generalPosition=4;
		}
		else
		generalPosition=4-numberOfArrival;
	}

	public void setStartingPositionOnBoard(Board board) {						//invoked only after ship-building-phase
		board.getSpace(generalPosition-1).putPlayer(this);				//fills out the space with corresponding player
	}

    public boolean isLappedBy(Player other) {
        return other.getPosition() - this.getPosition() >= Board.NUM_OF_SPACES;
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


