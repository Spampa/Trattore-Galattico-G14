package flightBoard;

public class Pawn {								
	private static final int MAX_NUM_PLAYERS=4;
	private static int numPlayers=0;				//initially 0, can go up to 4
	private int position;							//indicates the position (1st, 2nd, 3rd, 4th)
	private int generalPosition;					//indicates the number of spaces cleared (can be negative)
	//TODO private final Color color;						
	
	public Pawn(/*Color color*/) {
		if(numPlayers<=MAX_NUM_PLAYERS) {
			this.generalPosition=0;
			this.position=0;
			//TODO this.color=color;
			numPlayers++;
		}
		else
			System.out.println("Number of players exceeded!");
	}
	
	public void setStartingPosition(int numberOfArrival, Board board) {	//invoked only after ship-building-phase
		generalPosition=MAX_NUM_PLAYERS-numberOfArrival;				//sets the position on the starting grid
		position=numberOfArrival+1;										//sets the initial position of the player
		board.getSpace(generalPosition-1).putPawn(this);				//fills out the space with corresponding player
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
	
	public int getPosition() {
		return this.position;
	}
	
	public void setPosition(int p) {
		this.position=p;
	}
	
/*	public static void updatePosition(Player[] players) {
		int[] temp=new int[players.length];					//static method which updates the position attribute of each player
		for(int i=0;i<players.length-1;i++) {
			for(int j=i+1;j<players.length;j++) {
				if(players[i].getGeneralPosition()<players[j].getGeneralPosition())
					temp[i]++;
				else
					temp[j]++;
			}
		}
		
		for(int t=0;t<players.length;t++) {
			players[t].setPosition(temp[t]+1);
		}			
	}
*/	
	public boolean checkIfLapped(Pawn[] pawns) {		//method which checks if the player has been lapped
		for(int i=0; i<pawns.length;i++) {
			if(pawns[i].getGeneralPosition()-this.generalPosition>=Board.NUM_OF_SPACES)
				return true;
		}
		return false;		
	}	
}