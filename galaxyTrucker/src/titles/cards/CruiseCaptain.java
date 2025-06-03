package titles.cards;

import components.Component;
import components.enums.Side;
import components.models.containers.SpacemanUnit;
import entities.Player;
import entities.ship.Ship;
import titles.Title;
import titles.TitleType;

public class CruiseCaptain extends Title {

	public CruiseCaptain() {
		super(TitleType.CRUISE_CAPTAIN);
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
						if(c instanceof SpacemanUnit) {
							if(((SpacemanUnit) c).getCurrentCapacity()>0) {
								for(int q=0; q<4; q++) {
									if(c.getConnectors()[q].getNumber()==0) {
										Side sd=Side.values()[q];
										switch(sd) {
										case UP:{
											if((j+1)<s.getGameLevel().getBoardY()) {
												if(s.getShipComponets()[j+1][k].getComponent()==null||
												s.getShipComponets()[j+1][k].isIsSpace()) {
													count++;
												}
											}
											else {
											count++;
											}
											break;
										}
											
										case RIGHT:{
											if((k+1)<s.getGameLevel().getBoardX()) {
												if(s.getShipComponets()[j][k+1].getComponent()==null||
												s.getShipComponets()[j][k+1].isIsSpace()) {
													count++;
												}
											}
											else {
											count++;
											}
											break;
										}
											
										case DOWN:{
											if((j-1)>0) {
												if(s.getShipComponets()[j-1][k].getComponent()==null||
												s.getShipComponets()[j-1][k].isIsSpace()) {
													count++;
												}
											}
											else {
											count++;
											}
											break;
										}
											
										default:{
											if((k-1)>0) {
												if(s.getShipComponets()[j][k-1].getComponent()==null||
												s.getShipComponets()[j][k-1].isIsSpace()) {
													count++;
												}
											}
											else {
											count++;
											}
											break;
										}	
											
									}
								}
							}
						}
							
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

