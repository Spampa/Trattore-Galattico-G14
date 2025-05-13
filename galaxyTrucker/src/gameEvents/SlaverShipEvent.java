package gameEvents;

import entities.*;
import ui.Graphic;
import components.enums.Side;
import entities.Ship;
import gameEvents.Actions.ProjectileType;
import gameEvents.Actions.Shoot;
import gameEvents.Actions.Action;
import flightBoard.Board;
import java.util.Random;

public class SlaverShipEvent extends Event {
    private final Player player;
    private final Board board;
    private final Graphic graphic;

    public SlaverShipEvent(Player player, Board board, Graphic graphic) {
        super(new Action[] {}, EventType.SLAVER_SHIP);
        this.player = player;
        this.board = board;
        this.graphic = graphic;
    }

    @Override
    public void startEvent() {
        Ship ship = player.getPlayerShip();
        graphic.printAlert("Nave schiavista attacca! Rapito 1 equipaggio!");
        //ship.setter da aggiungere(ship.getHumansCounter() - 1);

        if (ship.getFirePower() > 0 && graphic.askUser("Vuoi sparare?")) {
            boolean success = new Random().nextBoolean();
            if (success) {
                new Shoot(Side.RIGHT, ProjectileType.BIG_CANNON, 
                        new Random().nextInt(ship.getGameLevel().getBoardY())).getHit(ship);
            } else {
                //ship.setter da aggiungere(ship.getHumansCounter() - 1);
                //servirebbe un metodo per muovere indietro il player
            }
        }
    }
}