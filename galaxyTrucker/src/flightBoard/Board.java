package flightBoard;

public class Board {
	public static final int NUM_OF_SPACES=18;  					//flight board has 18 spaces
	private Space[] spaces=new Space[NUM_OF_SPACES];
	
	public Board() {
		for(int i=0;i<NUM_OF_SPACES;i++) {
			this.spaces[i]=new Space(i);   					//creates a board with 18 spaces
		}
	}
	
	public void printBoard() {
		for(int i=0;i<18;i++) {
			if(this.spaces[i].getPawn()!=null)
				System.out.println("Spazio "+this.spaces[i].getSpace()+" :"+this.spaces[i].getPawn()+" Pos: "+this.spaces[i].getPawn().getPosition());
			else
				System.out.println("Spazio "+this.spaces[i].getSpace()+" :"+this.spaces[i].getPawn());
		}
	}
	
	public int findSpaceWithPawn(Pawn pawn) {
		for(int i=0;i<NUM_OF_SPACES;i++) {					//given a pawn, finds in which of the 18 spaces
			if(spaces[i].getPawn()==pawn)				//he is at. 
				return i;									//if the pawn is not found, returns -1 (pawn not on board)
		}
		return -1;
	}
	
	public Space getSpace(int i) {
		return this.spaces[i];
	}
	
	public void moveForward(int movements, Pawn pawn) {
		int currentSpace=findSpaceWithPawn(pawn);		//finds the current space of the pawn
		if(movements>0)
			spaces[currentSpace].putPawn(null);			//sets the current space to null (the pawn will move)
		while(movements>0) {
			currentSpace++;									//increases the space in which the pawn is in (as the function implies)
			pawn.increaseGeneralPosition();				//increases the general position by 1
			if (currentSpace>17) {							//checks if the pawn moves forward from last space (the 18th) 
				currentSpace-=18;							//if true the pawn goes back to 1st space (basically a loop)
			}
			if(spaces[currentSpace].getPawn()==null)		//check if the space in which the pawn is moving is free
				movements--;								//if true the single movement "counts" so it is decreased from movements
		}
		spaces[currentSpace].putPawn(pawn);				//at the end of the loop, the pawn is put inside of the corresponding space		
	}
	
	public void moveBack(int movements, Pawn pawn) {
		int currentSpace=findSpaceWithPawn(pawn);		//same as moveForward, but going backwards
		if(movements>0)
			spaces[currentSpace].putPawn(null);
		while(movements>0) {
			currentSpace--;
			pawn.decreaseGeneralPosition();
			if (currentSpace<0) {
				currentSpace+=18;
			}
			if(spaces[currentSpace].getPawn()==null)
				movements--;
		}
		spaces[currentSpace].putPawn(pawn);				
	}
	
	public Pawn getPawnFromPosition(int pos, Pawn[] pawns) {
		for(int i=0; i<pawns.length; i++) {
			if(pawns[i].getPosition()==pos)
				return pawns[i];
		}
		return null;
	}
	
	public void updatePosition(Pawn[] pawns) {
		int[] temp=new int[pawns.length];					//static method which updates the position attribute of each pawn
		for(int i=0;i<pawns.length-1;i++) {
			for(int j=i+1;j<pawns.length;j++) {
				if(pawns[i].getGeneralPosition()<pawns[j].getGeneralPosition())
					temp[i]++;
				else
					temp[j]++;
			}
		}
		
		for(int t=0;t<pawns.length;t++) {
			pawns[t].setPosition(temp[t]+1);
		}			
	}
}
