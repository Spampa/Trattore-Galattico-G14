package flightBoard;

public class Board {
	public static final int NUM_OF_SPACES=18;  					//flight board has 18 spaces
	private Space[] spaces=new Space[NUM_OF_SPACES];
	
	public Board() {
		for(int i=0;i<NUM_OF_SPACES;i++) {
			this.spaces[i]=new Space(i);   					//creates a board with 18 spaces
		}
	}
	
	public void stampaBoard() {
		for(int i=0;i<18;i++) {
			if(this.spaces[i].getPlayer()!=null)
				System.out.println("Spazio "+this.spaces[i].getSpace()+" :"+this.spaces[i].getPlayer()+" Pos: "+this.spaces[i].getPlayer().getPosition());
			else
				System.out.println("Spazio "+this.spaces[i].getSpace()+" :"+this.spaces[i].getPlayer());
		}
	}
	
	public int findSpaceWithPlayer(Player player) {
		for(int i=0;i<NUM_OF_SPACES;i++) {					//given a player, finds in which of the 18 spaces
			if(spaces[i].getPlayer()==player)				//he is at. 
				return i;									//if the player is not found, returns -1 (player not on board)
		}
		return -1;
	}
	
	public Space getSpace(int i) {
		return this.spaces[i];
	}
	
	public void moveForward(int movements, Player player) {
		int currentSpace=findSpaceWithPlayer(player);		//finds the current space of the player
		if(movements>0)
			spaces[currentSpace].putPlayer(null);			//sets the current space to null (the player will move)
		while(movements>0) {
			currentSpace++;									//increases the space in which the player is in (as the function implies)
			player.increaseGeneralPosition();				//increases the general position by 1
			if (currentSpace>17) {							//checks if the player moves forward from last space (the 18th) 
				currentSpace-=18;							//if true the player goes back to 1st space (basically a loop)
			}
			if(spaces[currentSpace].getPlayer()==null)		//check if the space in which the player is moving is free
				movements--;								//if true the single movement "counts" so it is decreased from movements
		}
		spaces[currentSpace].putPlayer(player);				//at the end of the loop, the player is put inside of the corresponding space		
	}
	
	public void moveBack(int movements, Player player) {
		int currentSpace=findSpaceWithPlayer(player);		//same as moveForward, but going backwards
		if(movements>0)
			spaces[currentSpace].putPlayer(null);
		while(movements>0) {
			currentSpace--;
			player.decreaseGeneralPosition();
			if (currentSpace<0) {
				currentSpace+=18;
			}
			if(spaces[currentSpace].getPlayer()==null)
				movements--;
		}
		spaces[currentSpace].putPlayer(player);				
	}
	
	public Player getPlayerFromPosition(int pos, Player[] players) {
		for(int i=0; i<players.length; i++) {
			if(players[i].getPosition()==pos)
				return players[i];
		}
		return null;
	}
	
	public void updatePosition(Player[] players) {
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
}
