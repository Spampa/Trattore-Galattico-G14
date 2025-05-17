package eventCards;

import entities.*;
import events.Shoot;
import components.enums.Side;
import gameEvents.enums.ProjectileType;
import ui.Graphic;
import java.util.Random;
import flightBoard.Board;

public class AsteroidFieldCard extends Card {
    private static final int BASE_DAMAGE = 2;
    private static final int ENGINE_SAFE_THRESHOLD = 3;
    
    public AsteroidFieldCard(Board board, Graphic graphic) {
        super("Campo Asteroidi", 
              "2 danni casuali alla nave (1 se motori >= 3)", 
              board, graphic);
    }

    @Override
    public void executeEvent(Ship ship, Player player) {
        int damageCount = (ship.getMotorPower() >= ENGINE_SAFE_THRESHOLD) ? 1 : BASE_DAMAGE;
        graphic.printAlert("Entrato in un campo asteroidi! Danni attesi: " + damageCount);

        for (int i = 0; i < damageCount; i++) {
            
            ProjectileType asteroidType = new Random().nextBoolean() ? 
                ProjectileType.BIG_ASTEROID : ProjectileType.SMALL_ASTEROID;

            Side randomSide = Side.values()[new Random().nextInt(Side.values().length)];
            int randomPosition = new Random().nextInt(
                (randomSide == Side.UP || randomSide == Side.DOWN) ? 
                ship.getGameLevel().getBoardX() : ship.getGameLevel().getBoardY()
            );
            
            new Shoot(randomSide, asteroidType, randomPosition).getHit(ship);
        }

        //se non erro c'è anche il bonus per le navi con scudi ma non mi ricordo di preciso la card quindi per ora nada
    }
}