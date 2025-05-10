package eventCards;

import components.enums.Side;
import entities.Player;
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
    public void executeEvent(Player[] players) {
        System.out.println("PIRATI ATTACCANO CON " + projectileCount + " PROIETTILI!");
        
        
        for(int i=0; i<projectileCount; i++) {
            ProjectileType type = (i % 2 == 0) ? ProjectileType.BIG_CANNON : ProjectileType.SMALL_CANNON;
            //new Shoot
        }
        
        for(Player p: players){
            if(p.getPlayerShip().getMotorPower() < 2) {
            System.out.println("Nave troppo lenta! Danno extra");
            //ship.getHit();
            }
        }

    }
}