package entities;

import components.*;
import components.enums.Side;
import components.models.*;
import components.models.containers.*;
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

    public Ship(GameLevel level){
            shipComponents = getShipBoard();
            this.level = level;
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
                
                s[2][3].setComponent(new HousingUnit(c, true));
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
                
                s[2][3].setComponent(new HousingUnit(c, true));
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

                s[3][4].setComponent(new HousingUnit(c, true));
            }
        }

        for(int i = 0; i < level.getBoardX(); i++){
            for(int j = 0; j < level.getBoardY(); j++){
                if(s[i][j] == null) s[i][j] = new ShipTile(true);
            }
        }

        return s;
    }

    private void checkComponent(int x, int y){

        if(!shipComponents[x][y].isIsSpace() && shipComponents[x][y].getComponent() != null){
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

        this.firePower = 0;
        this.motorPower = 0;
        this.waresValue = 0;
        this.voidConnectors = 0;
        this.humansCounter = 0;
        this.cellsCounter = 0;
        this.aliensCounter = 0;

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

    public boolean  setComponet(Component c, int x, int y){

        if(shipComponents[y][x].isIsSpace() || shipComponents[y][x].getComponent() != null) return false;

        if(c instanceof Engine){
           if(shipComponents[y+1][x].getComponent() != null) return false;
        }

        if(c instanceof Cannon){
            switch (c.getOrientation()) {
                case Side.UP -> {
                    if(shipComponents[y-1][x].getComponent() != null) return false;
                }
                case Side.DOWN ->{
                    if(shipComponents[y+1][x].getComponent() != null) return false;
                }
                case Side.LEFT ->{
                    if(shipComponents[y][x-1].getComponent() != null) return false;
                }
                case Side.RIGHT ->{
                    if(shipComponents[y][x+1].getComponent() != null) return false;
                }
            }
        }

        //TODO fix this shit cause it's awful 
        if(shipComponents[y-1][x].getComponent() != null && !checkConnectors(c.getConnector(Side.UP), shipComponents[y-1][x].getComponent().getConnector(Side.DOWN))) return false;
        if(shipComponents[y+1][x].getComponent() != null && !checkConnectors(c.getConnector(Side.DOWN), shipComponents[y+1][x].getComponent().getConnector(Side.UP))) return false;
        if(shipComponents[y][x+1].getComponent() != null && !checkConnectors(c.getConnector(Side.RIGHT), shipComponents[y][x+1].getComponent().getConnector(Side.LEFT))) return false;
        if(shipComponents[y][x-1].getComponent() != null && !checkConnectors(c.getConnector(Side.LEFT), shipComponents[y][x-1].getComponent().getConnector(Side.RIGHT))) return false;
        
        shipComponents[y][x].setComponent(c);
        scanShip();
        return true; 
    }

    private boolean checkConnectors(Connector c1, Connector c2){

        if(c1 == null || c2 == null) return true;

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

}
