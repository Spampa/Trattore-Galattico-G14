package titles;

import java.util.Random;

import entities.GameLevel;
import entities.Player;

public abstract class Title {
	

	private boolean goldTier;
	private final TitleType type;
	private Player player;
	
	public String getDescription() {
		return type.getDescription();
	}

	public TitleType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Title [goldTier=" + goldTier + ", type=" + type + ", player=" + player + "]\n"+"Description: "+this.getDescription();
	}

	public Title(TitleType type) {
		this.goldTier=false;
		this.type=type;
		this.player=null;
	}
	
	public abstract int findElibiglePlayer(Player[] players);
	
	public void checkIfDefended(Player[] players, GameLevel gl) {
		Player previous=this.getTitleHolder();
		int new_max=this.findElibiglePlayer(players);
		if(previous==players[new_max]) {
			switch(gl.toInt()) {
			case 2:{
				goldTier=true;
				this.player.addCosmicCredit(4);
			}
			
			case 3:
			{
				if(goldTier) {
					this.player.addCosmicCredit(12);
				}
				else {
					this.player.addCosmicCredit(6);
				}
			}
			
			}
		}	
	}
	
	public void assignToPlayer(Player[] players, int max_i) throws SpecialAssignmentException {
		if(max_i>=0) {							//caso classico: titolo assegnato al giocatore con counter maggiore (per quella categoria)
			this.player=players[max_i];
			this.player.addCosmicCredit(2);
		}
		else 										//casi particolari:
		{
			if(max_i==-100) {						//caso particolare 1: tutti i giocatori sono non-playable-->assegnazione random
				this.assignToRandomPlayer(players);
			}
			else									//caso particolare 2: tutti i giocatori avevano rispettivi counter a 0--> eccezione
				throw new SpecialAssignmentException(this, players);
		}
	}
	
	public Player getTitleHolder() {
		return this.player;
	}
	
	public void setTitleHolder(Player player) {
		this.player=player;
	}
	
	public static int findMax(int counters[], Player[] players) {
		int max_i=-1;
		int max=0;
		for(int i=0; i<counters.length; i++) {
			if(counters[i]>max||(counters[i]==max &&  (players[i].compareTo(players[max_i])>0)/*(players[i].getPosition()>players[max_i].getPosition())*/)) {
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
	}
	
}
