package flightBoard;

public class Space {
	private final int space;
	private Pawn pawn;
	
	public Space(int space) {
		this.space=space;  					//creates a numbered space box (casella sulla plancia)
		this.pawn=null;					//each space initially doesn't have a pawn
	}
	
	public void putPawn(Pawn pawn) {
		this.pawn=pawn;					//puts a pawn inside a space
	}
	
	public int getSpace() {
		return space;						
	}
	
	public Pawn getPawn() {
			return pawn;					
	}
}
