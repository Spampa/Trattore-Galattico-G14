package eventCards;

import components.enums.Side;
import entities.Ship;
import gameEvents.Actions.ProjectileType;
import gameEvents.EventType;

public class PirateAttackCard extends EventCard {
    private final int projectileCount;
    private final Side direction;

    public PirateAttackCard() {
        super("Incursione Pirata", 
              "Attacco con proiettili da destra", 
              EventType.PIRATE_RAID);
        this.projectileCount = 3;
        this.direction = Side.RIGHT;
    }

    @Override
    public void executeEvent(Ship ship) {
        System.out.println("PIRATI ATTACCANO CON " + projectileCount + " PROIETTILI!");
        
        
        for(int i=0; i<projectileCount; i++) {
            ProjectileType type = (i % 2 == 0) ? ProjectileType.BIG_CANNON : ProjectileType.SMALL_CANNON;
            //new Shoot
        }
        
    
        if(ship.getMotorPower() < 2) {
            System.out.println("Nave troppo lenta! Danno extra");
            //ship.getHit();
        }
    }
}