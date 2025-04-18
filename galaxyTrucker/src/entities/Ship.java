package entities;

import components.models.*;
import components.models.containers.HousingUnit;
import components.models.containers.NormalWareStorage;
import components.models.containers.WareStorage;
import items.Ware;

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

	public  void scanShip(){

        //TODO reset al ship parameter before sanning the ship

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

                    switch (shipComponents[i][j].getComponent()) {
                        
                        case Cannon c-> {
                            firePower += c.getFirePower();
                        }

                        case Engine e ->{
                            //TODO use method e.getEnginePower();
                        }

                        case HousingUnit h ->{
                            humansCounter += h.getCurrentCapacity();
                        }
                        
                        case NormalWareStorage ns->{
                            for (Ware ct : ns.getContent()) {
                                waresValue += ct.getValue();
                            }
                        }

                        default ->{
                            return;
                        }

                    }

                }
            }
        }
	}

    public boolean storeWares(Ware w, int x, int y){
        
        if(shipComponents[x][y].getComponent() instanceof WareStorage container){
            return container.add(w);
        }
        return false;
    }

    public ShipTile[][] getShipComponets(){
        return shipComponents;
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

    public GameLevel getGameLevel(){
        return level;
    }

}
