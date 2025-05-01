package entities;

import flightBoard.Board;
import flightBoard.Pawn;

public class Player {

    private static int playerCount = 0;
    private final int playerID;
    private final Ship playerShip;
    private Pawn pawn;
    private final String playerName;

    public Player(String playerName, Ship playerShip) {
        if (playerCount >= 4) {
        	System.out.println("Massimo 4 giocatori consentiti.");
        }
        this.playerID = ++playerCount;
        this.playerShip = playerShip;
        this.playerName = playerName;

    }

    public int getPlayerID() {
        return playerID;
    }

    public Ship getPlayerShip() {
        return playerShip;
    }

    public Pawn getPawn() {
        return pawn;
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
    

    public int getPosition() {
        return pawn.getPosition();
    }

    public void setPosition(int position) {
        pawn.setPosition(position);
    }

    public int getGeneralPosition() {
        return pawn.getGeneralPosition();
    }

    public void increaseGeneralPosition() {
        pawn.increaseGeneralPosition();
    }

    public void decreaseGeneralPosition() {
        pawn.decreaseGeneralPosition();
    }

    public void setStartingPosition(int numberOfArrival, Board board) {
        pawn.setStartingPosition(numberOfArrival, board);
    }

    public boolean isLappedBy(Player other) {
        return other.getGeneralPosition() - this.getGeneralPosition() >= Board.NUM_OF_SPACES;
    }

    public String getPlayerName() {
        return playerName;
    }

}


