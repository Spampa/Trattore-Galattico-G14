package titles.cards;

import components.Component;
import components.models.*;
import entities.Player;
import entities.ship.Ship;
import titles.Title;
import titles.TitleType;

public class TitlePowerTrucker extends Title {

	public TitlePowerTrucker() {
		super(TitleType.POWER_TRUCKER);
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
						Component c=s.getShipComponets()[j][k].getComponent();
						if(c instanceof Engine || c instanceof Cannon || c instanceof Shield) {	
							count++;
						}	
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

