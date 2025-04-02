package flightBoard;

public class Space {
	private final int space;
	private Player player;
	//private final Space nextSpace;
	
	public Space(int space) {
		this.space=space;  //creates a numbered space box (casella sulla plancia)
		this.player=null;
	}
	
	public void putPlayer(Player player) {
		
		this.player=player;
	}
	
	public int getSpace() {
		return space;
	}
	
	public Player getPlayer() {
		return player;
	}
}
