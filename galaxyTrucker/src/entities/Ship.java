package entities;

import components.*;
import components.enums.*;
import components.models.*;
import components.models.containers.*;
import items.*;

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

    public Ship(GameLevel level){
        this.level = level;
        shipComponents = getShipBoard();
    }

    private ShipTile[][] getShipBoard(){

        ShipTile[][] s = new ShipTile[level.getBoardY()][level.getBoardX()];
        Connector[] c = {Connector.UNIVERSAL, Connector.UNIVERSAL, Connector.UNIVERSAL, Connector.UNIVERSAL};

        switch (level) {
            case GameLevel.I -> {
                s[0][3] = new ShipTile();

                s[1][2] = new ShipTile();
                s[1][3] = new ShipTile();
                s[1][4] = new ShipTile();

                s[2][1] = new ShipTile();
                s[2][2] = new ShipTile();
                s[2][3] = new ShipTile();
                s[2][4] = new ShipTile();
                s[2][5] = new ShipTile();

                s[3][1] = new ShipTile();
                s[3][2] = new ShipTile();
                s[3][3] = new ShipTile();
                s[3][4] = new ShipTile();
                s[3][5] = new ShipTile();

                s[4][1] = new ShipTile();
                s[4][2] = new ShipTile();
                s[4][4] = new ShipTile();
                s[4][5] = new ShipTile();
                
                s[level.getBoardY()/2][level.getBoardX()/2].setComponent(new HousingUnit(c, true));
            }
            case GameLevel.II -> {
                s[0][2] = new ShipTile();
                s[0][4] = new ShipTile();

                s[1][1] = new ShipTile();
                s[1][2] = new ShipTile();
                s[1][3] = new ShipTile();
                s[1][4] = new ShipTile();
                s[1][5] = new ShipTile();

                s[2][0] = new ShipTile();
                s[2][1] = new ShipTile();
                s[2][2] = new ShipTile();
                s[2][3] = new ShipTile();
                s[2][4] = new ShipTile();
                s[2][5] = new ShipTile();
                s[2][6] = new ShipTile();

                s[3][0] = new ShipTile();
                s[3][1] = new ShipTile();
                s[3][2] = new ShipTile();
                s[3][3] = new ShipTile();
                s[3][4] = new ShipTile();
                s[3][5] = new ShipTile();
                s[3][6] = new ShipTile();

                s[4][0] = new ShipTile();
                s[4][1] = new ShipTile();
                s[4][2] = new ShipTile();
                s[4][4] = new ShipTile();
                s[4][5] = new ShipTile();
                s[4][6] = new ShipTile();
                
                s[level.getBoardY()/2][level.getBoardX()/2].setComponent(new HousingUnit(c, true));
            }
            case GameLevel.III ->{
                s[0][4] = new ShipTile();
                
                s[1][3] = new ShipTile();
                s[1][4] = new ShipTile();
                s[1][5] = new ShipTile();

                s[2][0] = new ShipTile();
                s[2][2] = new ShipTile();
                s[2][3] = new ShipTile();
                s[2][4] = new ShipTile();
                s[2][5] = new ShipTile();
                s[2][6] = new ShipTile();
                s[2][8] = new ShipTile();

                s[3][0] = new ShipTile();
                s[3][1] = new ShipTile();
                s[3][2] = new ShipTile();
                s[3][3] = new ShipTile();
                s[3][4] = new ShipTile();
                s[3][5] = new ShipTile();
                s[3][6] = new ShipTile();
                s[3][7] = new ShipTile();
                s[3][8] = new ShipTile(); 

                s[4][0] = new ShipTile();
                s[4][1] = new ShipTile();
                s[4][2] = new ShipTile();
                s[4][3] = new ShipTile();
                s[4][4] = new ShipTile();
                s[4][5] = new ShipTile();
                s[4][6] = new ShipTile();
                s[4][7] = new ShipTile();
                s[4][8] = new ShipTile();

                s[5][0] = new ShipTile();
                s[5][1] = new ShipTile();
                s[5][3] = new ShipTile();
                s[5][4] = new ShipTile();
                s[5][5] = new ShipTile();
                s[5][7] = new ShipTile();
                s[5][8] = new ShipTile();

                s[level.getBoardY()/2][level.getBoardX()/2].setComponent(new HousingUnit(c, true));
            }
        }

        for(int i = 0; i < level.getBoardY(); i++){
            for(int j = 0; j < level.getBoardX(); j++){
                if(s[i][j] == null) s[i][j] = new ShipTile(true);
            }
        }

        return s;
    }

    private void checkComponent(Position p){

        if(!shipComponents[p.getY()][p.getX()].isIsSpace() && shipComponents[p.getY()][p.getX()].getComponent() != null){
            shipComponents[p.getY()][p.getX()].setScanned(true);

            if(p.getY()+1 <= level.getBoardY()&& !shipComponents[p.getY()+1][p.getX()].isScanned()){
                checkComponent(new Position(p.getX(), p.getY()+1)); 
            }
            
            if(p.getY()-1 >= 0 && !shipComponents[p.getY()-1][p.getX()].isScanned()){
                checkComponent(new Position(p.getX(), p.getY()-1));
            }

            if(p.getX()+1 <= level.getBoardX() && !shipComponents[p.getY()][p.getX()+1].isScanned()){
                checkComponent(new Position(p.getX()+1, p.getY()));
            }

            if(p.getX()-1 >= 0 && !shipComponents[p.getY()][p.getX()-1].isScanned()){
                checkComponent( new Position(p.getX()-1, p.getY()) );
            }
        }
    }

	public  void scanShip(){

        this.firePower = 0;
        this.motorPower = 0;
        this.waresValue = 0;
        this.voidConnectors = 0;
        this.humansCounter = 0;
        this.cellsCounter = 0;
        this.aliensCounter = 0;

        for(int i = 0; i < level.getBoardX(); i++){
            for(int j = 0; j < level.getBoardY(); j++){
                shipComponents[j][i].setScanned(false);
            }
        }

        checkComponent(new Position( level.getBoardX()/2, level.getBoardY()/2 ));

        for(int i = 0; i < level.getBoardY(); i++){
            for(int j = 0; j < level.getBoardX(); j++){
                if(!shipComponents[i][j].isScanned()){
                    shipComponents[i][j].setComponent(null);
                }
                else{

                    switch (shipComponents[i][j].getComponent()) {
                        
                        case Cannon c-> {
                            firePower += c.getFirePower();
                        }

                        case Engine e ->{
                            e.getEnginePower();
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

    public boolean  setComponet(Component c, Position p){

        if(shipComponents[p.getY()][p.getX()].isIsSpace() || shipComponents[p.getY()][p.getX()].getComponent() != null) return false;

        if(shipComponents[p.getY()+1][p.getX()].getComponent() == null 
        && shipComponents[p.getY()-1][p.getX()].getComponent() == null 
        && shipComponents[p.getY()][p.getX()+1].getComponent() == null 
        && shipComponents[p.getY()][p.getX()-1].getComponent() == null) return false;

        if(c instanceof Engine){
           if(shipComponents[p.getY()+1][p.getX()].getComponent() != null) return false;
        }

        if(c instanceof Cannon){
            switch (c.getOrientation()) {
                case Side.UP -> {
                    if(shipComponents[p.getY()-1][p.getX()].getComponent() != null) return false;
                }
                case Side.DOWN ->{
                    if(shipComponents[p.getY()+1][p.getX()].getComponent() != null) return false;
                }
                case Side.LEFT ->{
                    if(shipComponents[p.getY()][p.getX()-1].getComponent() != null) return false;
                }
                case Side.RIGHT ->{
                    if(shipComponents[p.getY()][p.getX()+1].getComponent() != null) return false;
                }
            }
        }

        if(p.getY() > 0) {
            if(shipComponents[p.getY()-1][p.getX()].getComponent() != null && !checkConnectors(c.getConnector(Side.UP), shipComponents[p.getY()-1][p.getX()].getComponent().getConnector(Side.DOWN))) return false;
        }
        if(p.getY() < level.getBoardY()-1){
            if(shipComponents[p.getY()+1][p.getX()].getComponent() != null && !checkConnectors(c.getConnector(Side.DOWN), shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.UP))) return false;
        }
        if(p.getX() < level.getBoardX()-1){
            if(shipComponents[p.getY()][p.getX()+1].getComponent() != null && !checkConnectors(c.getConnector(Side.RIGHT), shipComponents[p.getY()][p.getX()+1].getComponent().getConnector(Side.LEFT))) return false;
        }
        if(p.getX() > 0) {
            if(shipComponents[p.getY()][p.getX()-1].getComponent() != null && !checkConnectors(c.getConnector(Side.LEFT), shipComponents[p.getY()][p.getX()-1].getComponent().getConnector(Side.RIGHT))) return false;   
        }
        
        if(shipComponents[p.getY()][p.getX()].setComponent(c)){
            if(c instanceof Engine){
                shipComponents[p.getY()+1][p.getX()].setIsSpace(true);
            }

            if(c instanceof Cannon){
                switch (c.getOrientation()) {
                    case Side.UP -> {
                        shipComponents[p.getY()-1][p.getX()].setIsSpace(true);
                    }
                    case Side.DOWN ->{
                        shipComponents[p.getY()+1][p.getX()].setIsSpace(true);
                    }
                    case Side.LEFT ->{
                        shipComponents[p.getY()][p.getX()-1].setIsSpace(true);
                    }
                    case Side.RIGHT ->{
                        shipComponents[p.getY()][p.getX()+1].setIsSpace(true);
                    }
                }
            }

            scanShip();
            return true; 
        }

        return false;
    }

    private boolean checkConnectors(Connector c1, Connector c2){

        switch (c1) {
            case UNIVERSAL -> {
                if(c2 != Connector.EMPTY) return true;
            }
            case DOUBLE -> {
                if(c2 == Connector.DOUBLE || c2 == Connector.UNIVERSAL) return true;
            }
            case SINGLE -> {
                if(c2 == Connector.SINGLE || c2 == Connector.UNIVERSAL) return true;
            }
            case EMPTY ->{
                if(c2 == Connector.EMPTY) return true;
            }
        }

        return false;
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

    @Override
    public String toString(){
        return """
               SHIP INFOS:
               Fire power: """ + this.firePower + "\n" + "Motor power: " + this.motorPower + "\n" + "wares value: " + this.waresValue + "\n" + "Humans: " + this.humansCounter + "\n" + "Batteries: " + this.cellsCounter + "\n";
    }
}
