package titles.cards;

import entities.Player;
import entities.ship.Ship;
import titles.Title;
import titles.TitleType;

public class MasterEngineer extends Title {

	public MasterEngineer() {
		super(TitleType.MASTER_ENGINEER);
	}

	@Override
	public int findElibiglePlayer(Player[] players) {
		int[] counters=new int[players.length];
		int countNotInGame=0;
		for(int i=0; i<players.length; i++) {
			int count=0;
			//
			if(players[i].checkPlayer()) {			//TODO da rivedere in Player
				Ship s=players[i].getShip();
				for(int j = 0; j < s.getGameLevel().getBoardY(); j++) {
					for(int k = 0; k < s.getGameLevel().getBoardX(); k++) {
						if(s.getShipComponets()[j][k].getComponent()!=null)
							count++;
					}
				} 
			}
			else {
				countNotInGame++;
			}
			//
			counters[i]=count;		
		}
		if(countNotInGame==players.length) {
			return -100;
		}
		int max_i=findMax(counters, players);
		return max_i;		
	}

}

