package titles.cards;

import java.util.ArrayList;

import components.Component;
import components.Connector;
import components.enums.Side;
import entities.GameLevel;
import entities.Player;
import entities.Position;
import entities.ship.Ship;
import entities.ship.ShipTile;
import titles.Title;
import titles.TitleType;

public class Corridorist extends Title {

	public Corridorist() {
		super(TitleType.CORRIDORIST);
	}

	@Override
	public int findElibiglePlayer(Player[] players) {
		int[] counters=new int[players.length];
		int countNotInGame=0;
		for(int i=0; i<players.length; i++) {
			int count=0;
			ArrayList<ShipTile> wantedTiles=new ArrayList<ShipTile>();
			//
			if(players[i].checkPlayer()/*isInGame(players)*/) {			//TODO da rivedere in Player
				Ship s=players[i].getShip();
				ShipTile[][] sts=s.getShipComponets();
				for(int j = 0; j < s.getGameLevel().getBoardY(); j++) {
					for(int k = 0; k < s.getGameLevel().getBoardX(); k++) {
						ShipTile st=sts[j][k];
						Component c=st.getComponent();
						int temp=0;
						for(Connector cn :c.getConnectors()) {
							if(cn.getNumber()!=0)
								temp++;
						}
						if(temp<3)
							wantedTiles.add(st);	
				}
			}
				count=findLongestCorridor(wantedTiles, sts, s.getGameLevel());
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
	
	private static ArrayList<ShipTile> findExtremities(ArrayList<ShipTile> wantedTiles, ShipTile[][] shipComponents, GameLevel level){
		ArrayList<ShipTile> extremities=new ArrayList<ShipTile>();
		for(int i=0;i<wantedTiles.size();i++) {
			int temp=0;
			Position p=new Position();
			ShipTile st=(wantedTiles.get(i));
			for(int j=0;j<level.getBoardY();j++) {				//potentially removable if TODO below
				for(int k=0;k<level.getBoardX();k++) {
					if(shipComponents[j][k]==st) {
						p=new Position(k,j);
						break;
					}
				}
			}
			//Position p=(wantedTiles.get(i)).getPosition();		//getPosition not in ShipTile
	        if(p.getY()+1 < level.getBoardY() 
	        		&& shipComponents[p.getY()+1][p.getX()].getComponent() != null 
	        		&& checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.DOWN), shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.UP))) {
	        		if(wantedTiles.contains(shipComponents[p.getY()+1][p.getX()]))
	        			temp++;
	        }
	        
	        if(p.getX()+1 < level.getBoardX() 
	        		&& shipComponents[p.getY()][p.getX()+1].getComponent() != null 
	        		&& checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.RIGHT), shipComponents[p.getY()][p.getX()+1].getComponent().getConnector(Side.LEFT))) {
	        		if(wantedTiles.contains(shipComponents[p.getY()][p.getX()+1]))
	        			temp++;
	        }
	        
	        if(p.getY()-1 > 0
	        		&& shipComponents[p.getY()-1][p.getX()].getComponent() != null 
	        		&& checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.UP), shipComponents[p.getY()-1][p.getX()].getComponent().getConnector(Side.DOWN))) {
	        		if(wantedTiles.contains(shipComponents[p.getY()-1][p.getX()]))
	        			temp++;
	        }
	        
	        if(p.getX()-1 > 0
	        		&& shipComponents[p.getY()][p.getX()-1].getComponent() != null 
	        		&& checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.LEFT), shipComponents[p.getY()][p.getX()-1].getComponent().getConnector(Side.RIGHT))) {
	        		if(wantedTiles.contains(shipComponents[p.getY()][p.getX()-1]))
	        			temp++;
	        }
	        
	        if(temp<2)
	        	extremities.add(wantedTiles.get(i));
		}
		return extremities;
	}
	
	private static int findLongestCorridor(ArrayList<ShipTile> wantedTiles, ShipTile[][] shipComponents, GameLevel level ) {
		int maxLength=1;
		ArrayList<ShipTile> extremities=findExtremities(wantedTiles, shipComponents, level);
		for(int i=0;i<extremities.size();i++) {
			ShipTile firstPiece=extremities.get(i);
			Position p=new Position();
			for(int j=0;j<level.getBoardY();j++) {				//potentially removable if TODO below
				for(int k=0;k<level.getBoardX();k++) {
					if(shipComponents[j][k]==firstPiece) {
						p=new Position(k,j);
						break;
					}
				}
			}
			//Position p=firstPiece.getPosition();       TODO when and if getPosition in ShipTile
			ArrayList<ShipTile> temp=wantedTiles;
			temp.remove(extremities.get(i));
			int count=0;
			int tempLength=0;
			while(count<wantedTiles.size()) {
				if(p.getY()+1 < level.getBoardY() 
		        		&& shipComponents[p.getY()+1][p.getX()].getComponent() != null 
		        		&& checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.DOWN), shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.UP))) {
		        		if(temp.contains(shipComponents[p.getY()+1][p.getX()])) {
		        				tempLength++;
		        				p.setY(p.getY()+1);
		        				//p=shipComponents[p.getY()+1][p.getX()].getPosition();
		        				temp.remove(shipComponents[p.getY()][p.getX()]);
		        		}
		        			
		        }
		        
		        if(p.getX()+1 < level.getBoardX() 
		        		&& shipComponents[p.getY()][p.getX()+1].getComponent() != null 
		        		&& checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.RIGHT), shipComponents[p.getY()][p.getX()+1].getComponent().getConnector(Side.LEFT))) {
		        			if(temp.contains(shipComponents[p.getY()][p.getX()+1])) {
		        				tempLength++;
		        				p.setX(p.getX()+1);
		        				//p=shipComponents[p.getY()][p.getX()+1].getPosition();
		        				temp.remove(shipComponents[p.getY()][p.getX()]);
	        		}
		        }
		        
		        if(p.getY()-1 > 0
		        		&& shipComponents[p.getY()-1][p.getX()].getComponent() != null 
		        		&& checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.UP), shipComponents[p.getY()-1][p.getX()].getComponent().getConnector(Side.DOWN))) {
		        		if(temp.contains(shipComponents[p.getY()-1][p.getX()])) {
		        			tempLength++;
		        			p.setY(p.getY()-1);
		        			//p=shipComponents[p.getY()-1][p.getX()].getPosition();
		        			temp.remove(shipComponents[p.getY()][p.getX()]);
	        		}
		        }
		        
		        if(p.getX()-1 > 0
		        		&& shipComponents[p.getY()][p.getX()-1].getComponent() != null 
		        		&& checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.LEFT), shipComponents[p.getY()][p.getX()-1].getComponent().getConnector(Side.RIGHT))) {
		        	if(temp.contains(shipComponents[p.getY()][p.getX()-1])) {
	        			tempLength++;	
	        			p.setX(p.getX()-1);
	        			//p=shipComponents[p.getY()][p.getX()-1].getPosition();
	        			temp.remove(shipComponents[p.getY()][p.getX()]);
	        		}
		        }
		        count++;
			}
			
			if(tempLength>maxLength)
				tempLength=maxLength;
		}
		
		return maxLength;
	}

}

