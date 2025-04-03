package entities;

import components.enums.*;
import components.types.containers.Container;
import gameEvents.Actions.*;

public class Ship {

    private final GameLevel level;

	private final ShipTile[][] shipComponents;

	private float firePower;
	private int motorPower;
	private int waresValue;
	private int voidConnectors;
	private int humansCounter;
	private int cellsCounter;
	private int aliensCounter;

    public Ship(ShipTile[][] components, GameLevel level){
            shipComponents = components;
            this.level = level;

            scanShip();
    }

    private void checkComponent(int x, int y){

        if(shipComponents[x][y].getComponent() != null){
            shipComponents[x][y].setScanned(true);

            if(x+1 <= level.getBoardX() && !shipComponents[x+1][y].isScanned()){
                checkComponent(x+1, y);
            }
            
            if(x-1 >= 0 && !shipComponents[x-1][y].isScanned()){
                checkComponent(x-1, y);
            }

            if(y+1 <= level.getBoardY() && !shipComponents[x][y+1].isScanned()){
                checkComponent(x, y+1);
            }

            if(y-1 >= 0 && !shipComponents[x][y-1].isScanned()){
                checkComponent(x, y-1);
            }
        }
    }

	private  void scanShip(){

        for(int i = 0; i < level.getBoardX(); i++){
            for(int j = 0; j < level.getBoardY(); j++){
                shipComponents[i][j].setScanned(false);
            }
        }

        checkComponent((level.getBoardX()/2)+1, (level.getBoardY()/2)+1);

        for(int i = 0; i < level.getBoardX(); i++){
            for(int j = 0; j < level.getBoardY(); j++){
                if(!shipComponents[i][j].isScanned()){
                    shipComponents[i][j].setComponent(null);
                }
                else{
                    // TODO add a switch case to update ship values
                }
            }
        }
	}

	public boolean  getHit(Shoot s){
        
        switch (s.getDirection()) {
            
            case ProjectileDirection.FRONT -> {
                for(int i = 0; i < level.getBoardY(); i++){
                    
                    if(shipComponents[i][s.getComingTile()].getComponent() != null){
                        
                        if(!shipComponents[i][s.getComingTile()].isProtectedTile()){
                            scanShip();
                            return shipComponents[i][s.getComingTile()].getComponent().tryBreak(s.getType());
                        }
                        else{
                            scanShip();
                            return false;
                        }
                    }
                }
                scanShip();
                return false;
            }

            case ProjectileDirection.BACK -> {
                for(int i = (level.getBoardY()-1); i >= 0; i--){
                    
                    if(shipComponents[i][s.getComingTile()].getComponent() != null){
                        
                        if(!shipComponents[i][s.getComingTile()].isProtectedTile()){
                            scanShip();
                            return shipComponents[i][s.getComingTile()].getComponent().tryBreak(s.getType());
                        }
                        else{
                            scanShip();
                            return false;
                        }
                    }
                }
                scanShip();
                return false;
            }

            case ProjectileDirection.LEFT -> {
                for(int i = 0; i < level.getBoardX(); i++){
                    
                    if(shipComponents[s.getComingTile()][i].getComponent() != null){
                        
                        if(!shipComponents[s.getComingTile()][i].isProtectedTile()){
                            scanShip();
                            return shipComponents[s.getComingTile()][i].getComponent().tryBreak(s.getType());
                        }
                        else{
                            scanShip();
                            return false;
                        }
                    }
                }
                scanShip();
                return false; 
            }

            case ProjectileDirection.RIGHT -> {
                for(int i = (level.getBoardX()-1); i >= 0 ; i--){
                    
                    if(shipComponents[s.getComingTile()][i].getComponent() != null){
                        
                        if(!shipComponents[s.getComingTile()][i].isProtectedTile()){
                            scanShip();
                            return shipComponents[s.getComingTile()][i].getComponent().tryBreak(s.getType());
                        }
                        else{
                            scanShip();
                            return false;
                        }
                    }
                }
                scanShip();
                return false; 
            }

            default -> {
                scanShip();
                return false;
            }

        }
	}

    public boolean storeWares(Ware[] w){

        for(int i = 0; i < level.getBoardX(); i++){
            for(int j = 0; j < level.getBoardY(); j++){
                if(shipComponents[i][j].getComponent() instanceof Container){
                    //if(shipComponents[i][j].getComponent().store(w)) 
                    return true;
                }
            }
        }

        return false;
    }

	public boolean isPlayable(){
		return (shipComponents[(level.getBoardX()/2)+1][(level.getBoardY()/2)+1].getComponent() != null);
	}

    public float getFirePower() {
        return firePower;
    }

    public int getMotorPower() {
        return motorPower;
    }

    public int getWaresValue() {
        return waresValue;
    }

    public int getVoidConnectors() {
        return voidConnectors;
    }

    public int getHumansCounter() {
        return humansCounter;
    }

    public int getCellsCounter() {
        return cellsCounter;
    }

    public int getAliensCounter() {
        return aliensCounter;
	}

}
