package titles.cards;

import entities.Player;
import entities.Ship;
import titles.Title;
import titles.TitleTypes;

public class TitleMasterEngineer extends Title {

	public TitleMasterEngineer() {
		super(TitleTypes.MASTER_ENGINEER.getDescription(), TitleTypes.MASTER_ENGINEER);
	}

	@Override
	public int findElibiglePlayer(Player[] players) {
		int[] counters=new int[players.length];
		int countNotInGame=0;
		for(int i=0; i<players.length; i++) {
			int count=0;
			//
			if(players[i].isInGame(players)) {			//TODO da rivedere in Player
				Ship s=players[i].getPlayerShip();
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

