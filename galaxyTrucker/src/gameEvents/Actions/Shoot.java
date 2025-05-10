package gameEvents.Actions;

import components.enums.Side;
import entities.Position;
import entities.Ship;

public class Shoot extends Action {

    private final Side direction;
    private final ProjectileType type;
    private final int comingTile;

    public Shoot(Side direction, ProjectileType type, int comingTile){
        super("Shoot");
        this.direction = direction;
        this.type = type;
        this.comingTile = comingTile;
    }

    public Side getDirection() {
        return direction;
    }

    public ProjectileType getType() {
        return type;
    }

    public int getComingTile() {
        return comingTile;
    }

    public boolean getHit(Ship s){
        switch (direction) {
            case Side.UP -> {
                for(int i = 0; i < s.getGameLevel().getBoardY(); i++){     
                    if(s.getShipComponets()[i][comingTile].getComponent() != null){
                        return s.breakComponent(new Position(comingTile, i), type, direction);
                    }
                }
                return false;
            }

            case Side.DOWN -> {
                for(int i = (s.getGameLevel().getBoardY()-1); i >= 0; i--){
                    if(s.getShipComponets()[i][comingTile].getComponent() != null){   
                        return s.breakComponent(new Position(comingTile, i), type, direction);
                    }
                }
                return false;
            }

            case Side.LEFT -> {
                for(int i = 0; i < s.getGameLevel().getBoardX(); i++){  
                    if(s.getShipComponets()[comingTile][i].getComponent() != null){
                        return s.breakComponent(new Position(i , comingTile), type, direction);
                    }
                }
                return false; 
            }

            case Side.RIGHT -> {
                for(int i = (s.getGameLevel().getBoardX()-1); i >= 0 ; i--){  
                    if(s.getShipComponets()[comingTile][i].getComponent() != null){
                        return s.breakComponent(new Position(i, comingTile), type, direction);
                    }
                }
                return false; 
            }

            default -> {
                return false;
            }

        }
    }

}
