package flightBoard;

import entities.Player;

public class Space {
	private final int number;
	private Player player;
	
	public Space(int number) {
		this.number = number;  					//creates a numbered space box (casella sulla plancia)
		this.player = null;					//each space initially doesn't have a pawn
	}
	
	public void setPlayer(Player player) {
		this.player=player;					//puts a pawn inside a space
	}
	
	public Player removePlayer() {
		Player player = this.player;
		this.player = null;
		return player;
	}
	
	public int getNumber() {
		return number;						
	}
	
	public Player getPlayer() {
			return player;					
	}
	
	@Override
	public String toString() {
		if(player != null) return number + player.toString();
		return number + "";
	}
}
