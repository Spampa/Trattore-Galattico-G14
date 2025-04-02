package flightBoard;

public class Board {
	private Space[] spaces;
	private final int NUM_OF_SPACES=18;
	
	public Board() {
		for(int i=0;i<NUM_OF_SPACES;i++) {
			this.spaces[i]=new Space(i);
		}
	}
	
	public int findSpaceWithPlayer(Player player) {
		for(int i=0;i<NUM_OF_SPACES;i++) {
			if(spaces[i].getPlayer()==player)
				return i;
		}
	}
	
	public void moveForward(int space, Player player) {
		int currentSpace=findSpaceWithPlayer(player);
		spaces[currentSpace].putPlayer(null);
		while(space>0) {
			currentSpace++;
			if (currentSpace>17) {
				currentSpace-=18;
			}
			if(spaces[currentSpace].getPlayer()==null)
				space--;
		}
		spaces[currentSpace].putPlayer(player);	
		
	}
	
	public void moveBack(int space, Player player) {
		int currentSpace=findSpaceWithPlayer(player);
		spaces[currentSpace].putPlayer(null);
		while(space>0) {
			currentSpace--;
			if (currentSpace<0) {
				currentSpace+=18;
			}
			if(spaces[currentSpace].getPlayer()==null)
				space--;
		}
		spaces[currentSpace].putPlayer(player);	
		
		
	}
	
	
	
	
}
