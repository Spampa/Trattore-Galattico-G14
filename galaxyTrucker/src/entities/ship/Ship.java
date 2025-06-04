package entities.ship;

import components.*;
import components.enums.*;
import components.models.*;
import components.models.containers.*;
import entities.GameLevel;
import entities.Position;
import gameEvents.enums.*;
import items.*;
import items.enums.AlienType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import ui.Graphic;

public class Ship {

    private final GameLevel level;
    private final Graphic g;

	private final ShipTile[][] shipComponents;

	private ArrayList<Cannon> cannons = new ArrayList<Cannon>();  
	private ArrayList<Engine> engines = new ArrayList<Engine>(); 
    private ArrayList<Shield> shields = new ArrayList<Shield>(); 
	private ArrayList<WareStorage> wareStorages = new ArrayList<WareStorage>(); 
	private ArrayList<SpacemanUnit> spacemanUnits = new ArrayList<SpacemanUnit>(); 
	private ArrayList<AlienUnit> alienUnits = new ArrayList<AlienUnit>();
	private ArrayList<BatteryStorage> batteryStorages = new ArrayList<BatteryStorage>();
	private ArrayList<Component> lostComponents = new ArrayList<Component>();
	// private ArrayList<AlienHousingUnit> AlieUnits;

    public Ship(GameLevel level, Graphic g){
        this.level = level;
        this.g = g;
        shipComponents = Ship.getShipLevelBoard(level);
    }
    
    public Ship(GameLevel level, Graphic g, ShipTile[][] shipComponents) {
        this.level = level;
        this.g = g;
        
        //TODO: check if table is correct for the level
        this.shipComponents = shipComponents;
        this.scanShip();
    }

    public boolean isPlayable(){
		return (shipComponents[(level.getBoardY()/2)][(level.getBoardX()/2)].getComponent() != null);
	}

    private boolean  findPathToCore(Position p){

        boolean r1 = false, r2 = false, r3 = false, r4 = false;

        if(getComponent(p) != null){
            if(getComponent(p) instanceof SpacemanUnit possibleCore){
                if(possibleCore.isCore()) return true;
            }
            else shipComponents[p.getY()][p.getX()].setScanned(true);
        }

        if(p.getY()+1 < level.getBoardY() 
        && !shipComponents[p.getY()+1][p.getX()].isScanned() 
        && shipComponents[p.getY()+1][p.getX()].getComponent() != null 
        && getComponent(p).getConnector(Side.DOWN) != Connector.EMPTY 
        && shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.UP) != Connector.EMPTY 
        && Connector.checkConnectors(getComponent(p).getConnector(Side.DOWN), shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.UP))){
            r1 = findPathToCore(new Position(p.getX(), p.getY()+1)); 
        }
        
        if(p.getY()-1 >= 0 
        && !shipComponents[p.getY()-1][p.getX()].isScanned() 
        && shipComponents[p.getY()-1][p.getX()].getComponent() != null
        && getComponent(p).getConnector(Side.UP) != Connector.EMPTY 
        && shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.DOWN) != Connector.EMPTY 
        && Connector.checkConnectors(getComponent(p).getConnector(Side.UP), shipComponents[p.getY()-1][p.getX()].getComponent().getConnector(Side.DOWN))){
            r2 = findPathToCore(new Position(p.getX(), p.getY()-1));
        }

        if(p.getX()+1 < level.getBoardX() 
        && !shipComponents[p.getY()][p.getX()+1].isScanned() 
        && shipComponents[p.getY()][p.getX()+1].getComponent() != null
        && getComponent(p).getConnector(Side.RIGHT) != Connector.EMPTY 
        && shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.LEFT) != Connector.EMPTY 
        && Connector.checkConnectors(getComponent(p).getConnector(Side.RIGHT), shipComponents[p.getY()][p.getX()+1].getComponent().getConnector(Side.LEFT))){
            r3 = findPathToCore(new Position(p.getX()+1, p.getY()));
        }

        if(p.getX()-1 > 0 
        && !shipComponents[p.getY()][p.getX()-1].isScanned() 
        && shipComponents[p.getY()][p.getX()-1].getComponent() != null 
        && getComponent(p).getConnector(Side.LEFT) != Connector.EMPTY 
        && shipComponents[p.getY()+1][p.getX()].getComponent().getConnector(Side.RIGHT) != Connector.EMPTY 
        && Connector.checkConnectors(getComponent(p).getConnector(Side.LEFT), shipComponents[p.getY()][p.getX()-1].getComponent().getConnector(Side.RIGHT))){
            r4 = findPathToCore( new Position(p.getX()-1, p.getY()) );
        }

        return r1 || r2 || r3 || r4;
    }

    private void checkComponent(Position p){

        for(int i = 0; i < level.getBoardX(); i++){
            for(int j = 0; j < level.getBoardY(); j++){
                shipComponents[j][i].setScanned(false);
            }
        }

        if(shipComponents[p.getY()][p.getX()].getComponent() != null && !findPathToCore(p)){
            shipComponents[p.getY()][p.getX()].setComponent(null);
        }
    }

    private void setProtectedTile(int x, int y, Cannon c){
        switch (c.getOrientation()) {
            case Side.UP ->{
                shipComponents[0][x].setCannonProtected(true);
            }
            case Side.DOWN ->{
                shipComponents[level.getBoardY()-1][x].setCannonProtected(true);
            }
            case Side.RIGHT ->{
                shipComponents[y][level.getBoardX()-1].setCannonProtected(true);
            }
            case Side.LEFT ->{
                shipComponents[y][0].setCannonProtected(true);
            }
        }
    }

    private void setProtectedTile(Shield s){
        if(s.isSideProtected(Side.UP)){
            for(int i = 0; i < level.getBoardX(); i++) {
                shipComponents[0][i].setShieldProtected(true);
            }
        }
        if(s.isSideProtected(Side.DOWN)){
            for(int i = 0; i < level.getBoardX(); i++) {
                shipComponents[level.getBoardY()-1][i].setShieldProtected(true);
            }
        }
        if(s.isSideProtected(Side.LEFT)){
            for(int i = 0; i < level.getBoardY(); i++) {
                shipComponents[i][0].setShieldProtected(true);
            }
        }

        if(s.isSideProtected(Side.LEFT)){
            for(int i = 0; i < level.getBoardY(); i++) {
                shipComponents[i][level.getBoardX()-1].setShieldProtected(true);
            }
        }
    }
    
    
    private boolean convertToAlien(Position p) {
    	HashSet<AlienType> supports = new HashSet<AlienType>();
    	SpacemanUnit su;
    	
    	try {
    		su = (SpacemanUnit)getComponent(p);
    	}
    	catch(Exception ex){
    		throw new IllegalArgumentException("elemento non convertibile nel tipo richiesto");
    	}
    	
    	if(getComponent(p).getConnector(Side.DOWN) != Connector.EMPTY && shipComponents[p.getY()+1][p.getX()].getComponent() instanceof LifeSupport ls) {
    		if(Connector.checkConnectors(getComponent(p).getConnector(Side.DOWN), ls.getConnector(Side.UP))) {
    			supports.add(ls.getAlienType());
    		}
    	}
    	
    	if(getComponent(p).getConnector(Side.UP) != Connector.EMPTY && shipComponents[p.getY()-1][p.getX()].getComponent() instanceof LifeSupport ls) {
    		if(Connector.checkConnectors(getComponent(p).getConnector(Side.UP), ls.getConnector(Side.DOWN))) {
    			supports.add(ls.getAlienType());
    		}
    	}
    	
    	if(getComponent(p).getConnector(Side.RIGHT) != Connector.EMPTY && shipComponents[p.getY()][p.getX()+1].getComponent() instanceof LifeSupport ls) {
    		if(Connector.checkConnectors(getComponent(p).getConnector(Side.RIGHT), ls.getConnector(Side.LEFT))) {
    			supports.add(ls.getAlienType());
    		}
    	}
    	
    	if(getComponent(p).getConnector(Side.LEFT) != Connector.EMPTY && shipComponents[p.getY()][p.getX()-1].getComponent() instanceof LifeSupport ls) {
    		if(Connector.checkConnectors(getComponent(p).getConnector(Side.LEFT), ls.getConnector(Side.RIGHT))) {
    			supports.add(ls.getAlienType());
    		}
    	}
    	
    	if(supports.size() > 1) {
    		if(g.askUser("scegli il tipo in cui convertire la unit (0: viola, 1: marrone)")) {
    			shipComponents[p.getY()][p.getX()].setComponent(new AlienUnit(su.getConnectors() ,AlienType.BROWN));
    		}
    		else shipComponents[p.getY()][p.getX()].setComponent(new AlienUnit(su.getConnectors() ,AlienType.PURPLE));
    		
    		
    		alienUnits.add((AlienUnit)getComponent(p));
    		
    		return true;
    	}
    	
    	return false;
    }


    public  void scanShip(){

        this.cannons = new ArrayList<>();  
	    this.engines = new ArrayList<>(); 
	    this.wareStorages = new ArrayList<>(); 
	    this.spacemanUnits = new ArrayList<>(); 
	    this.batteryStorages = new ArrayList<>();

        for(int i = 0; i < level.getBoardX(); i++){
            for(int j = 0; j < level.getBoardY(); j++){
                checkComponent(new Position( i, j));
            }
        }
        

        for(int i = 0; i < level.getBoardY(); i++){
            for(int j = 0; j < level.getBoardX(); j++){
                if(shipComponents[i][j].getComponent() != null){
                    switch (shipComponents[i][j].getComponent()) {
                        
                        case Cannon c-> {
                            cannons.add(c);
                            setProtectedTile(j, i, c);
                            break;
                        }

                        case Shield s ->{
                            shields.add(s);
                            setProtectedTile(s);
                            break;
                        }
    
                        case Engine e ->{
                           engines.add(e);
                           break;
                        }
    
                        case SpacemanUnit h ->{
                        	if(!convertToAlien(new Position(j, i))) {
                        		spacemanUnits.add(h);	
                        	}
                            break;
                        }
                        
                        case AlienUnit au ->{
                        	alienUnits.add(au);
                        	break;
                        }
                        
                        case WareStorage ws->{
                            wareStorages.add(ws);
                            break;
                        }

                        case BatteryStorage bt ->{
                            batteryStorages.add(bt);
                            break;
                        }
    
                        default ->{
                            break;
                        }
    
                    }              
                }
            }
        }
	}

    public boolean  setComponent(Component c, Position p){

        if(shipComponents[p.getY()][p.getX()].isIsSpace() || shipComponents[p.getY()][p.getX()].getComponent() != null) return false;


        if(c instanceof Engine){
           if(p.getY()+1 < level.getBoardY() && shipComponents[p.getY()+1][p.getX()].getComponent() != null) return false;
        }

        if(c instanceof Cannon){
            switch (c.getOrientation()) {
                case Side.UP -> {
                    if(p.getY()-1 >= 0 && shipComponents[p.getY()-1][p.getX()].getComponent() != null) return false;
                }
                case Side.DOWN ->{
                    if(p.getY()+1 < level.getBoardY() && shipComponents[p.getY()+1][p.getX()].getComponent() != null) return false;
                }
                case Side.LEFT ->{
                    if(p.getX()-1 >= 0 && shipComponents[p.getY()][p.getX()-1].getComponent() != null) return false;
                }
                case Side.RIGHT ->{
                    if(p.getX()+1 < level.getBoardX() && shipComponents[p.getY()][p.getX()+1].getComponent() != null) return false;
                }
            }
        }

        for(int i = 0; i < level.getBoardX(); i++){
            for(int j = 0; j < level.getBoardY(); j++){
                shipComponents[j][i].setScanned(false);
            }
        }
        
        shipComponents[p.getY()][p.getX()].setComponent(c);

        if(!findPathToCore(p)){
            shipComponents[p.getY()][p.getX()].setComponent(null);
            return false;  
        } 
        else{
            if(c instanceof Engine){
                shipComponents[p.getY()+1][p.getX()].setIsSpace(true);
            }
    
            if(c instanceof Cannon){
                switch (c.getOrientation()) {
                    case Side.UP -> {
                        if(p.getY()-1 >= 0) shipComponents[p.getY()-1][p.getX()].setIsSpace(true);
                    }
                    case Side.DOWN ->{
                        if(p.getY()+1 < level.getBoardY()) shipComponents[p.getY()+1][p.getX()].setIsSpace(true);
                    }
                    case Side.LEFT ->{
                        if(p.getX()-1 >= 0) shipComponents[p.getY()][p.getX()-1].setIsSpace(true);
                    }
                    case Side.RIGHT ->{
                        if(p.getX()+1 < level.getBoardX()) shipComponents[p.getY()][p.getX()+1].setIsSpace(true);
                    }
                }
            }
    
            scanShip();
            return true; 
        }
    }
    
    public Component breakComponent(Position p) {
    	Component c = getComponent(p);
    	
    	this.shipComponents[p.getY()][p.getX()].setComponent(null);
    	
    	return c;	
    }

    public Component breakComponent(Position p, ProjectileType pType, Side s){

        switch (pType) {
            case ProjectileType.SMALL_ASTEROID -> {
                if(getComponent(p).getConnector(s) == Connector.EMPTY || shipComponents[p.getY()][p.getX()].isShieldProtected()){
                    return null;
                }
            }
            case ProjectileType.BIG_ASTEROID -> {
                if(shipComponents[p.getY()][p.getX()].isCannonProtected()){
                    return null;
                }
            }
            case ProjectileType.SMALL_CANNON -> {
                if(shipComponents[p.getY()][p.getX()].isShieldProtected()){
                    return null;
                }
            }
            case ProjectileType.BIG_CANNON ->{
                break;
            }
        }
        lostComponents.add(getComponent(p));
        shipComponents[p.getY()][p.getX()].setComponent(null);
        scanShip();
        return lostComponents.getLast();
    }

    public boolean addItem(Position p, Item i) {
    	Component c = this.getComponent(p);
    	
    	switch (i){
            case Battery b ->{
            	if(c instanceof BatteryStorage storage) {
            		return storage.add(b);
            	}
            	else {
            		throw new WrongComponentPosition();
            	}
            }

            case Ware w ->{
            	if(c instanceof WareStorage storage) {
            		return storage.add(w);
            	}
            	else {
            		throw new WrongComponentPosition();
            	}
            }

            case  Spaceman s ->{
            	if(c instanceof SpacemanUnit storage) {
            		return storage.add(s);
            	}
            	else {
            		throw new WrongComponentPosition();
            	}
            }

            default ->{
                throw new UknownItemType();
            }
        }


    }

    public boolean removeItem(Position p, Item i) {
    	Component c = this.getComponent(p);
    	
    	    switch (i){
            case Battery b ->{
            	
            	if(c instanceof BatteryStorage storage) {
            		return storage.remove() != null;
            	}
            	else {
            		throw new WrongComponentPosition();
            	}
            	
            }

            case Ware w ->{
            	
            	if(c instanceof WareStorage storage) {
            		return storage.remove(w.getName()) != null;
            	}
            	else {
            		throw new WrongComponentPosition();
            	}
            	
            }

            case  Spaceman s ->{
            	
            	if(c instanceof SpacemanUnit storage) {
            		return storage.remove() != null;
            	}
            	else {
            		throw new WrongComponentPosition();
            	}
            	
            }

            default ->{
                return false;
            }
        }
    }

    private boolean  useBattery(int n){
        if(n <= getBatteries()){
            
            for(BatteryStorage bt : batteryStorages){
                while(bt.getCurrentCapacity() > 0 || n > 0){
                    bt.remove();
                    n--;
                }
                if(n == 0) return true;
            }
            //TODO
            // for(int i = 0; i < n; i++){
            //     removeItem(position, item);
            // }
        }
        return false;
    }

    public static ShipTile[][] getShipLevelBoard(GameLevel level){

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
                
                s[level.getBoardY()/2][level.getBoardX()/2].setComponent(new SpacemanUnit(c, true));
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
                
                s[level.getBoardY()/2][level.getBoardX()/2].setComponent(new SpacemanUnit(c, true));
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

                s[level.getBoardY()/2][level.getBoardX()/2].setComponent(new SpacemanUnit(c, true));
            }
        }

        for(int i = 0; i < level.getBoardY(); i++){
            for(int j = 0; j < level.getBoardX(); j++){
                if(s[i][j] == null) s[i][j] = new ShipTile(true);
            }
        }

        return s;
    }
    
    
    public ShipTile[][] getShipComponets(){
        return shipComponents.clone();
    }

    public int getMaxMotorPower(){
        int value = 0;
        for(Engine e : engines){
            value += e.getEnginePower();
        }
        return value;
    }

    public int getMaxFirePower(){
        int value = 0;
        for(Cannon c : cannons){
            value += c.getFirePower();
        }
        return value;
    }

    public float getFirePower() {
        float power = 0;
        for(Cannon c : cannons){
            if(c.getBatteryRequired() > 0){

                if(g.askUser("vuoi utilizzare batterie per attivare un cannone doppio?") && useBattery(c.getBatteryRequired())) power += c.getFirePower();

            }
            else power += c.getFirePower();
        }
        return power;
    }

    public int getMotorPower() {
        int power = 0;
        for(Engine e : engines){
            if(e.getBatteryRequired() > 0){

                if(g.askUser("vuoi utilizzare batterie per attivare un cannone doppio?") && useBattery(e.getBatteryRequired())) power += e.getEnginePower();

            }
            else power += e.getEnginePower();
        }
        return power;
    }

    public int getWaresValue() {
        int value = 0;
        for(WareStorage ws : wareStorages){
            for(Ware w : ws.getContent()){
                value += w.getValue();
            }
        }
        return value;
    }
    
    public Ware[] getWares() {
    	ArrayList<Ware> wares = new ArrayList<Ware>();
    	
    	for(WareStorage ws : wareStorages) {
    		for(Ware w : ws.getContent()) {
    			wares.add(w);
    		}
    	}
    	
    	Collections.sort(wares);
    	return (Ware[])wares.toArray();
    }

    private int numberOfVoidConnectors(Position p){
        int connectors = 0;
        if(getComponent(p).getConnector(Side.UP) != Connector.EMPTY  && shipComponents[p.getY()-1][p.getX()].getComponent() == null){
            connectors++;
        }
        if(getComponent(p).getConnector(Side.DOWN) != Connector.EMPTY  && shipComponents[p.getY()+1][p.getX()].getComponent() == null){
            connectors++;
        }
        if(getComponent(p).getConnector(Side.LEFT) != Connector.EMPTY  && shipComponents[p.getY()][p.getX()-1].getComponent() == null){
            connectors++;
        }
        if(getComponent(p).getConnector(Side.RIGHT) != Connector.EMPTY  && shipComponents[p.getY()][p.getX()+1].getComponent() == null){
            connectors++;
        }
        return connectors;
    }

    public int getVoidConnectors() {
        int connectors = 0;
        for(int i = 0; i < level.getBoardX(); i++){
            for(int j = 0; j < level.getBoardY(); j++){
                if(shipComponents[j][i].getComponent() != null){
                    connectors += numberOfVoidConnectors(new Position(i, j));
                }
            }
        }
        return  connectors;
    }

    public int getSpacemans() {
        int value = 0;
        for(SpacemanUnit h : spacemanUnits){
            value += h.getCurrentCapacity();
        }
        return value;
    }

    public int getBatteries() {
        int value = 0;
        for(BatteryStorage bs : batteryStorages){
            value += bs.getCurrentCapacity();
        }
        return value;
    }
    
    public int getNumLostComponent() {
    	return lostComponents.size();
    }

    public int getAliensCounter() {
    	int value = 0;
        for(AlienUnit au : alienUnits) {
        	value += au.getCurrentCapacity();
        }
        return value;
	}

    public GameLevel getGameLevel(){
        return level;
    }
    
    public Component getComponent(Position position) {
    	return shipComponents[position.getY()][position.getX()].getComponent();
    }

    @Override
    public String toString(){
        return """
               SHIP INFOS:
               Fire power: """ + getMaxFirePower() + "\n" + "Motor power: " + getMaxMotorPower() + "\n" + "wares value: " + getWaresValue() + "\n" + "Humans: " + getSpacemans() + "\n" + "Batteries: " + getBatteries() + "\n";
    }
}
