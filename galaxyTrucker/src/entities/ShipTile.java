package entities;

import components.*;

public class ShipTile {
    private boolean scanned = false;
    private boolean  isSpace;
    private Component component;
    private boolean shieldProtected = false;
    private boolean cannonProtected = false;

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

    public boolean isShieldProtected() {
        return shieldProtected;
    }

    public void setProtectedTile(boolean protectedTile) {
        this.shieldProtected = protectedTile;
    }

    public boolean isIsSpace() {
        return isSpace;
    }

    public void setIsSpace(boolean isSpace) {
        this.isSpace = isSpace;
    }

    public boolean isCannonProtected() {
        return cannonProtected;
    }

    public void setCannonProtected(boolean cannonProtected) {
        this.cannonProtected = cannonProtected;
    }


}
