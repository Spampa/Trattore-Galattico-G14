package entities;

import components.*;

public class ShipTile {
    private boolean scanned = false;
    private final boolean  isSpace;
    private Component component;

    public ShipTile(){
        isSpace = false;
    }

    public ShipTile(boolean isSpace){
        this.isSpace = isSpace;
    }
    
    public void setComponent(Component component) {
        if(isSpace){
            this.component = component;
        }
    }

    public Component getComponent() {
        return component;
    }
    
    public void setScanned(boolean scanned) {
        this.scanned = scanned;
    }
    
    public boolean isScanned() {
        return scanned;
    }


}
