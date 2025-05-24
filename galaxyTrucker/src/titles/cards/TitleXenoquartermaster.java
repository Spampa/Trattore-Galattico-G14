package titles.cards;

import java.util.ArrayList;				//serve per 2a versione di findNearestHousingUnit
import java.util.Arrays;

import components.Component;
import components.enums.Side;
import components.models.containers.HousingUnit;
import entities.Player;
import entities.Position;
import entities.ship.Ship;
import entities.ship.ShipTile;
import titles.Title;
import titles.TitleType;
import entities.GameLevel;

public class TitleXenoquartermaster extends Title{

	public TitleXenoquartermaster() {
		super(TitleType.XENOQUARTERMASTER);
	}

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
						if(c instanceof Component /* TODO replace Component with LifeSupportSystem when defined*/) {   //ci possono essere al massimo due LifeSupportSystem su una nave
							count+= findNearestHousingUnit(/*c.getPosition()*/new Position(k,j), s.getShipComponets(), s.getGameLevel());							
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
		int max=findMax(counters, players);
		return max;
	}
	
	private static int findNearestHousingUnit(Position p, ShipTile[][] shipComponents, GameLevel level) {//per come è ora trova un path fino ad una housingunit, ma non è detto che sia il più vicino
		 int [] r=new int[4];																				//TODO aggiungere un attributo countVisited in ShipTile + due metodi (un get e un get)
		 																									//scopo: realizzazione dell'alg di Dijkstra
	        if(shipComponents[p.getY()][p.getX()].getComponent() != null){
	            if(shipComponents[p.getY()][p.getX()].getComponent() instanceof HousingUnit){
	                return 1;
	            }
	            else shipComponents[p.getY()][p.getX()].setScanned(true);
	        }
	        else return 0;

	        if(p.getY()+1 < level.getBoardY() 
	        && !shipComponents[p.getY()+1][p.getX()].isScanned() 
	        && shipComponents[p.getY()+1][p.getX()].getComponent() != null 
	        && checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.DOWN), shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.UP))){
	            r[0] += findNearestHousingUnit(new Position(p.getX(), p.getY()+1), shipComponents, level); 
	        }
	        
	        if(p.getY()-1 >= 0 
	        && !shipComponents[p.getY()-1][p.getX()].isScanned() 
	        && shipComponents[p.getY()-1][p.getX()].getComponent() != null 
	        && checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.UP), shipComponents[p.getY()-1][p.getX()].getComponent().getConnector(Side.DOWN))){
	            r[1] += findNearestHousingUnit(new Position(p.getX(), p.getY()-1),shipComponents, level);
	        }

	        if(p.getX()+1 < level.getBoardX() 
	        && !shipComponents[p.getY()][p.getX()+1].isScanned() 
	        && shipComponents[p.getY()][p.getX()+1].getComponent() != null 
	        && checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.RIGHT), shipComponents[p.getY()][p.getX()+1].getComponent().getConnector(Side.LEFT))){
	            r[2] += findNearestHousingUnit(new Position(p.getX()+1, p.getY()),shipComponents, level);
	        }

	        if(p.getX()-1 > 0 && !shipComponents[p.getY()][p.getX()-1].isScanned() 
	        && shipComponents[p.getY()][p.getX()-1].getComponent() != null 
	        && checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.LEFT), shipComponents[p.getY()][p.getX()-1].getComponent().getConnector(Side.RIGHT))){
	            r[3] += findNearestHousingUnit( new Position(p.getX()-1, p.getY()),shipComponents, level);
	        }
	        Arrays.sort(r);			//ordina vettore r in maniera crescente
	        return r[0]; 			//restituisce l'elemento in cima(il più piccolo)
	}
	
	
	//TODO versione 2 di findNearestHousingUnit
/*	
	private static ArrayList<ShipTile> getNeighbours(Position p,ShipTile[][] shipComponents, GameLevel level){
		ArrayList<ShipTile> temp=new ArrayList<ShipTile>(); 
		if(p.getY()+1 < level.getBoardY() 
	        && shipComponents[p.getY()+1][p.getX()].getComponent() != null 
	        && checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.DOWN), shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.UP))) {
			temp.add(shipComponents[p.getY()+1][p.getX()]);
		}
		
		if(p.getX()+1 < level.getBoardX() 
		        && shipComponents[p.getY()][p.getX()+1].getComponent() != null 
		        && checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.RIGHT), shipComponents[p.getY()][p.getX()+1].getComponent().getConnector(Side.LEFT))) {
				temp.add(shipComponents[p.getY()][p.getX()+1]);
			}
		
		if(p.getY()-1 > 0
		        && shipComponents[p.getY()-1][p.getX()].getComponent() != null 
		        && checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.UP), shipComponents[p.getY()-1][p.getX()].getComponent().getConnector(Side.DOWN))) {
				temp.add(shipComponents[p.getY()-1][p.getX()]);
			}
		
		if(p.getX()-1 >0
		        && shipComponents[p.getY()][p.getX()-1].getComponent() != null 
		        && checkConnectors(shipComponents[p.getY()][p.getX()].getComponent().getConnector(Side.LEFT), shipComponents[p.getY()][p.getX()-1].getComponent().getConnector(Side.RIGHT))) {
				temp.add(shipComponents[p.getY()][p.getX()-1]);
			}
		
		return temp;
		
	}
	
	private static int findNearestHousingUnit(Position source, ShipTile[][] shipComponents, GameLevel level) {
		boolean trovato=false;
		int count;
		ArrayList<ShipTile> aggiunti=new ArrayList<ShipTile>(); 
		aggiunti.add(shipComponents[source.getY()][source.getX()]);
		Position p=source;
		while(!trovato) {
			ArrayList<ShipTile> vicini=getNeighbours(p, shipComponents, level);
			for(int i=0; i<vicini.size();i++) {
				if(vicini.get(i).getComponent()instanceof HousingUnit) {
					count=shipComponents[p.getY()][p.getX()].getCountVisited()+1;
					trovato=true;
				}
				else {
					if(!aggiunti.contains(vicini.get(i))) {
						aggiunti.add(vicini.get(i));
						vicini.get(i).setCountVisited(shipComponents[p.getY()][p.getX()].getCountVisited()+1);
					}
					else {
						if(vicini.get(i).getCountVisited()>(shipComponents[p.getY()][p.getX()].getCountVisited()+1)) {
							vicini.get(i).setCountVisited(shipComponents[p.getY()][p.getX()].getCountVisited()+1);
						}
					}
				}
				ShipTile st=aggiunti.get(aggiunti.indexOf(shipComponents[p.getY()][p.getX()])+1);
				for(int j=0;j<level.getBoardY();j++) {				//potentially removable if TODO implemented (look in Corridorist)
				for(int k=0;k<level.getBoardX();k++) {
					if(shipComponents[j][k]==st){
						p=new Position(k,j);
						break;
						}
				}
			}
				//p=aggiunti.get(aggiunti.indexOf(shipComponents[p.getY()][p.getX()])+1).getPosition();	//TODO getPosition in ShipTile
			}
		}	
		return count;
	}
    */
}

