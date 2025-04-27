package gameEvents.Actions;

import entities.Ship;

public class Shoot extends Action {

    private final ProjectileDirection direction;
    private final ProjectileType type;
    private final int comingTile;

    public Shoot(ProjectileDirection direction, ProjectileType type, int comingTile){
        super("Shoot");
        this.direction = direction;
        this.type = type;
        this.comingTile = comingTile;
    }

    public ProjectileDirection getDirection() {
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
            
            case ProjectileDirection.FRONT -> {
                for(int i = 0; i < s.getGameLevel().getBoardY(); i++){
                    
                    if(s.getShipComponets()[i][comingTile].getComponent() != null){
                        
                        if(!s.getShipComponets()[i][comingTile].isProtectedTile()){
                            s.scanShip();
                            return s.getShipComponets()[i][comingTile].getComponent().tryBreak(type); //TODO cambia in controllo dei connettori esposti
                        }
                        else{
                            s.scanShip();
                            return false;
                        }
                    }
                }
                s.scanShip();
                return false;
            }

            case ProjectileDirection.BACK -> {
                for(int i = (s.getGameLevel().getBoardY()-1); i >= 0; i--){
                    
                    if(s.getShipComponets()[i][comingTile].getComponent() != null){
                        
                        if(!s.getShipComponets()[i][comingTile].isProtectedTile()){
                            s.scanShip();
                            return s.getShipComponets()[i][comingTile].getComponent().tryBreak(type);
                        }
                        else{
                            s.scanShip();
                            return false;
                        }
                    }
                }
                s.scanShip();
                return false;
            }

            case ProjectileDirection.LEFT -> {
                for(int i = 0; i < s.getGameLevel().getBoardX(); i++){
                    
                    if(s.getShipComponets()[comingTile][i].getComponent() != null){
                        
                        if(!s.getShipComponets()[comingTile][i].isProtectedTile()){
                            s.scanShip();
                            return s.getShipComponets()[comingTile][i].getComponent().tryBreak(type);
                        }
                        else{
                            s.scanShip();
                            return false;
                        }
                    }
                }
                s.scanShip();
                return false; 
            }

            case ProjectileDirection.RIGHT -> {
                for(int i = (s.getGameLevel().getBoardX()-1); i >= 0 ; i--){
                    
                    if(s.getShipComponets()[comingTile][i].getComponent() != null){
                        
                        if(!s.getShipComponets()[comingTile][i].isProtectedTile()){
                            s.scanShip();
                            return s.getShipComponets()[comingTile][i].getComponent().tryBreak(type);
                        }
                        else{
                            s.scanShip();
                            return false;
                        }
                    }
                }
                s.scanShip();
                return false; 
            }

            default -> {
                s.scanShip();
                return false;
            }

        }
    }

}
