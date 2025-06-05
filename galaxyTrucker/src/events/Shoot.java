package events;

import components.enums.Side;
import entities.Dices;
import entities.GameLevel;
import entities.Player;
import entities.Position;
import entities.ship.Ship;
import gameEvents.enums.ProjectileType;
import ui.Graphic;

public class Shoot extends Event {

    private final Side direction;
    private final ProjectileType type;
    private Player player;
    
    private final Dices dices;

    public Shoot(Graphic graphic, Side direction, ProjectileType type ){
    	super(graphic);
    	dices = new Dices();
    
        this.direction = direction;
        this.type = type;
        
    }
    

    public void setPlayer(Player p) {
    	this.player = p;
    }
    
    @Override
    public void start(){
    	int comingTile = dices.roll();
        
        graphic.printMessage("risultato dei dadi: " + comingTile);
    	Ship s = player.getShip();
    	GameLevel level = s.getGameLevel();
    	
        switch (direction) {
            case Side.UP -> {
            	if(comingTile >= level.getBoardX()) {
            		graphic.printAlert(player.getName() +  " l' asteroide non ha colpito nulla");
            		break;
            	}
            	
                for(int i = 0; i < s.getGameLevel().getBoardY(); i++){     
                    if(s.getShipComponets()[i][comingTile].getComponent() != null){
                        if(s.breakComponent(new Position(comingTile, i), type, direction) != null) {
                        	 graphic.printAlert("L'asteroide ha colpito!");
                        }
                       
                        break;
                    }
                }
                break;
                
            }

            case Side.DOWN -> {
            	if(comingTile >= level.getBoardX()){
            		graphic.printAlert(player.getName() +  " l' asteroide non ha colpito nulla");
            		break;
            	}
            	
                for(int i = (s.getGameLevel().getBoardY()-1); i >= 0; i--){
                    if(s.getShipComponets()[i][comingTile].getComponent() != null){   
                        s.breakComponent(new Position(comingTile, i), type, direction);
                        break;
                    }
                }
                break;
            }

            case Side.LEFT -> {
            	if(comingTile >= level.getBoardY()){
            		graphic.printAlert(player.getName() +  " l' asteroide non ha colpito nulla");
            		break;
            	}
            	
                for(int i = 0; i < s.getGameLevel().getBoardX(); i++){  
                    if(s.getShipComponets()[comingTile][i].getComponent() != null){
                        s.breakComponent(new Position(i , comingTile), type, direction);
                        break;
                    }
                }
                break;
            }

            case Side.RIGHT -> {
            	if(comingTile >= level.getBoardY()){
            		graphic.printAlert(player.getName() +  " l' asteroide non ha colpito nulla");
            		break;
            	}
            	
                for(int i = (s.getGameLevel().getBoardX()-1); i >= 0 ; i--){  
                    if(s.getShipComponets()[comingTile][i].getComponent() != null){
                        s.breakComponent(new Position(i, comingTile), type, direction);
                        break;
                    }
                }
                break;
            }

        }
    }

}
