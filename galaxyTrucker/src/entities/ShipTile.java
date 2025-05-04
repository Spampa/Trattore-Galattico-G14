package entities;

import components.*;

public class ShipTile {
    private boolean scanned = false;
    private boolean  isSpace;
    private Component component;
    private boolean protectedTile = false;

    public ShipTile(){
        this.isSpace = false;
    }

    public ShipTile(boolean isSpace){
        this.isSpace = isSpace;
    }
    
    public boolean  setComponent(Component component) {
        if(!isSpace){
            this.component = component;
            return true;
        }
        else return false;
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

    public boolean isProtectedTile() {
        return protectedTile;
    }

    public void setProtectedTile(boolean protectedTile) {
        this.protectedTile = protectedTile;
    }

    public boolean isIsSpace() {
        return isSpace;
    }

    public void setIsSpace(boolean isSpace) {
        this.isSpace = isSpace;
    }


}
