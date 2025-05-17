package events;

import components.enums.Side;
import entities.Dices;
import entities.Player;
import entities.Position;
import entities.ship.Ship;
import gameEvents.enums.ProjectileType;
import ui.Graphic;

public class Shoot extends Event {

    private final Side direction;
    private final ProjectileType type;
    private final Player player;
    
    private final Dices dices;

    public Shoot(Graphic graphic, Player player, Side direction, ProjectileType type ){
    	super(graphic);
    	dices = new Dices();
    	
    	this.player = player;
        this.direction = direction;
        this.type = type;
        
    }

    public Side getDirection() {
        return direction;
    }

    public ProjectileType getType() {
        return type;
    }
    
    @Override
    public void start(){
    	Ship s = player.getShip();
    	int comingTile = dices.roll();
        switch (direction) {
            case Side.UP -> {
                for(int i = 0; i < s.getGameLevel().getBoardY(); i++){     
                    if(s.getShipComponets()[i][comingTile].getComponent() != null){
                        s.breakComponent(new Position(comingTile, i), type, direction);
                        break;
                    }
                }
                
            }

            case Side.DOWN -> {
                for(int i = (s.getGameLevel().getBoardY()-1); i >= 0; i--){
                    if(s.getShipComponets()[i][comingTile].getComponent() != null){   
                        s.breakComponent(new Position(comingTile, i), type, direction);
                        break;
                    }
                }
            }

            case Side.LEFT -> {
                for(int i = 0; i < s.getGameLevel().getBoardX(); i++){  
                    if(s.getShipComponets()[comingTile][i].getComponent() != null){
                        s.breakComponent(new Position(i , comingTile), type, direction);
                        break;
                    }
                }
            }

            case Side.RIGHT -> {
                for(int i = (s.getGameLevel().getBoardX()-1); i >= 0 ; i--){  
                    if(s.getShipComponets()[comingTile][i].getComponent() != null){
                        s.breakComponent(new Position(i, comingTile), type, direction);
                        break;
                    }
                }
            }

        }
    }

}
