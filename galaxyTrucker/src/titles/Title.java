package titles;

import java.util.Random;
import java.util.random.*;

import components.Connector;
import entities.GameLevel;
import entities.Player;

public abstract class Title {
	
	private boolean taken;
	private boolean goldTier;
	private final String description;
	private final TitleTypes type;
	private Player player;
	
	public String getDescription() {
		return description;
	}

	public TitleTypes getType() {
		return type;
	}

	public Title(String description, TitleTypes type) {
		this.taken=false;
		this.goldTier=false;
		this.description=description;
		this.type=type;
		this.player=null;
	}
	
	public abstract int findElibiglePlayer(Player[] players);
	
	public void checkIfDefended(Player[] players, GameLevel gl) {
		Player previous=this.getTitleHolder();
		int new_max=this.findElibiglePlayer(players);
		if(previous==players[new_max]) {
			switch(gl.getLevel()) {
			case 2:{
				goldTier=true;
				//TODO this.player.incrementCosmicCredits(4);
			}
			
			case 3:
			{
				if(goldTier) {
					//TODO this.player.incrementCosmicCredits(12);
				}
				else {
					//TODO this.player.incrementCosmicCredits(6);
				}
			}
			
			}
		}	
	}
	
	public void assignToPlayer(Player[] players, int max_i) throws SpecialAssignmentException {
		if(max_i>=0) {
			this.taken=true;
			this.player=players[max_i];
			//TODO this.player.incrementCosmicCredits(2);
		}
		else 
		{
			if(max_i==-100) {
				this.assignToRandomPlayer(players);
			}
			else
				throw new SpecialAssignmentException(this, players);
		}
	}
	
	public Player getTitleHolder() {
		return this.player;
	}
	
	public static int findMax(int counters[], Player[] players) {
		int max_i=-1;
		int max=0;
		for(int i=0; i<counters.length; i++) {
			if(counters[i]>max||(counters[i]==max && (players[i].getPosition()>players[max_i].getPosition()))) {
				max=counters[i];
				max_i=i;				
			}
		}
		return max_i;
	}
	
	private void assignToRandomPlayer(Player[] players) {
		int num_players=players.length;
		int rand_index=new Random().nextInt()%num_players;
		this.player=players[rand_index];
		this.taken=true;
	}
	
    public static boolean checkConnectors(Connector c1, Connector c2){     
    	////TODO to be removed once checkConnectors is put elsewhere other than Ship or made public

        switch (c1) {
            case UNIVERSAL -> {
                if(c2 != Connector.EMPTY) return true;
            }
            case DOUBLE -> {
                if(c2 == Connector.DOUBLE || c2 == Connector.UNIVERSAL) return true;
            }
            case SINGLE -> {
                if(c2 == Connector.SINGLE || c2 == Connector.UNIVERSAL) return true;
            }
            case EMPTY ->{
                if(c2 == Connector.EMPTY) return true;
            }
        }

        return false;
    }
}
