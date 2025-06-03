package eventCards;

import components.enums.Side;
import entities.GameLevel;
import entities.board.Board;
import ui.Graphic;

public class PirateAttackCard extends Card {
    private final int projectileCount;
    private final Side direction;

    public PirateAttackCard(Graphic graphic) {
        super(graphic, "Incursione Pirata", "Attacco con proiettili da destra");
        this.projectileCount = 3;
        this.direction = Side.RIGHT;
    }

    @Override
    public void execute(Board b) {
        // System.out.println("PIRATI ATTACCANO CON " + projectileCount + " PROIETTILI!");
        
        
        // for(int i=0; i<projectileCount; i++) {
        //     ProjectileType type = (i % 2 == 0) ? ProjectileType.BIG_CANNON : ProjectileType.SMALL_CANNON;
        //     //new Shoot
        // }
        
        // for(Player p: players){
        //     if(p.getShip().getMotorPower() < 2) {
        //     System.out.println("Nave troppo lenta! Danno extra");
        //     //ship.getHit();
        //     }
        // }

    }
}