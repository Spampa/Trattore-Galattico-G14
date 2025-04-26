package flightBoard;

public class Player {								
	private static int playerCount = 0;
	private final int  playerID;
	private final Ship playerShip;
	private int position;							
	private int generalPosition;					
							
	
	public Player(Ship ship) throw Exeception {
		if (playerCount >=4) {
			throw new Exception("Massimo 4 giocatori consentiti");
		}
		
		this.playerID = ++playerCount;
        this.playerShip = ship;
        this.position = 1;
        this.generalPosition = 0;
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
	
	public boolean isInGame() {
		return checkPlayer();
	}
	
	public int getPosition() {
		return position;
	}
	
	public int getGeneralPosition() {
		return generalPosition;
	}
	
	public void increaseGeneralPosition() {
		this.generalPosition++;
	}
	
	public void decreaseGeneralPosition() {
        this.generalPosition--;
    }
	
	public void setStartingPosition(int startingPosition) {
        this.generalPosition = startingPosition;
    }
	
	public boolean isLappedBy(Player other) {
        return other.getGeneralPosition() - this.getGeneralPosition() >= Board.NUM_OF_SPACES;
    }
		
}
