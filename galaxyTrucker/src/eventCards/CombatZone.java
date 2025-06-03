package eventCards;

import components.enums.Side;
import entities.*;
import entities.board.Board;
import events.RemoveItem;
import events.Shoot;
import gameEvents.enums.ProjectileType;
import items.Item;
import items.Spaceman;
import java.util.ArrayList;
import ui.Graphic;

public class CombatZone extends Card { 
    private final static int DAY_TO_LOSE = 3;
    private final static Item[] SPACEMAN_TO_LOSE = {new Spaceman(), new Spaceman()};

    private final Shoot FIRST_SHOOT;
    private final Shoot SECOND_SHOOT;
    
    public CombatZone(Graphic graphic, String name, String description, GameLevel level) {
        super(graphic, name, description, level);
        this.FIRST_SHOOT  = new Shoot(graphic, Side.DOWN, ProjectileType.SMALL_CANNON);
        this.SECOND_SHOOT  = new Shoot(graphic, Side.DOWN, ProjectileType.BIG_CANNON);
    }

    
    @Override
    public void execute(Board board) {
        ArrayList<Player> players =  board.getPlayers();
        Player firstToPunish = null, secondToPunish = null, thirdToPunish = null;
        RemoveItem loseSpaceman;

        graphic.printAlert("controlliamo gli equipaggi!...");
        for(Player p: players){
            if(firstToPunish == null || firstToPunish.getShip().getSpacemans() > p.getShip().getSpacemans()){
                firstToPunish = p;
            }
        }
        graphic.printMessage(firstToPunish.getName() + "sei il Giocatore con meno equipaggio mi spiace ma perderai " + DAY_TO_LOSE + " giorni di volo!...");
        board.moveBack(DAY_TO_LOSE, firstToPunish);
        graphic.waitForUser("primo per continuare...");


        graphic.printAlert("Ora controlliamo la potenza motrice!...");
        graphic.waitForUser("primo per continuare...");
        for(Player p: players){
            float minPower = 0;
            if(secondToPunish == null){
                float motorPow = p.getShip().getMotorPower();
                if(minPower > motorPow){
                    secondToPunish = p;
                    minPower = motorPow;
                }
            }
        }
        loseSpaceman = new RemoveItem(graphic, SPACEMAN_TO_LOSE, secondToPunish);
        graphic.printMessage(secondToPunish.getName() + "sei il Giocatore con meno potenza motrice mi spiace ma perderai " + SPACEMAN_TO_LOSE.length + " membri dell'equipaggio!...");
        loseSpaceman.start();
        graphic.waitForUser("primo per continuare...");


        graphic.printAlert("In fine controlliamo la potenza di fuoco!...");
        graphic.waitForUser("primo per continuare...");
        for(Player p: players){
            float minPower = 0;
            if(thirdToPunish == null){
                float firePow = p.getShip().getFirePower();
                if(minPower > firePow){
                    thirdToPunish = p;
                    minPower = firePow;
                }
            }
        }
        FIRST_SHOOT.setPlayer(thirdToPunish);
        SECOND_SHOOT.setPlayer(thirdToPunish);

        graphic.printMessage(thirdToPunish.getName() + "sei il Giocatore con meno potenza di fuoco mi spiace ma affronterai 2 cannonate...");
        FIRST_SHOOT.start();
        SECOND_SHOOT.start();
        graphic.waitForUser("primo per continuare...");

        graphic.printAlert("Evento" + super.getName() + " terminato!...");
        graphic.waitForUser("primo per continuare...");    

    }
}
