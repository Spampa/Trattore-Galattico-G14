package flightBoard;

public class Space {
	private final int space;
	private Player player;
	
	public Space(int space) {
		this.space=space;  					//creates a numbered space box (casella sulla plancia)
		this.player=null;					//each space initially doesn't have a player
	}
	
	public void putPlayer(Player player) {
		this.player=player;					//puts a player inside a space
	}
	
	public int getSpace() {
		return space;						
	}
	
	public Player getPlayer() {
			return player;					
	}
}
