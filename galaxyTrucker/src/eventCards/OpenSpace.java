package eventCards;

import entities.*;
import entities.board.Board;
import entities.ship.Ship;
import ui.Graphic;

import java.util.ArrayList;

public class OpenSpace extends Card {

    public OpenSpace(Graphic graphic) {
        super(graphic, "Spazio Aperto", 
              "Ogni giocatore può guadagnare giorni di volo in base alla potenza dei suoi motori.");
    }

    @Override
    public void execute(Board board) {
        ArrayList<Player> players = board.getPlayers(); 
        Player[] v=new Player[players.size()];
        for(int i=0;i<v.length;i++) {
        	v[i]=players.get(i);
        }
        
        graphic.printMessage("Ogni giocatore avanza in ordine di rotta in base alla propria potenza motrice.");

        for (int i=0;i<v.length;i++) {
            Ship ship = v[i].getShip();
            graphic.printMessage(v[i].getName() + ", tocca a te.");

            int power = ship.getMotorPower(); 
            if (power == 0) {
                graphic.printAlert(v[i].getName() + " non ha potenza motrice disponibile.");
                continue;
            }

            board.moveForward(power, v[i]);  
            graphic.printMessage(v[i].getName() + " avanza di " + power + " spazi.");
            graphic.waitForUser("premi per continuare...");  
        }
        graphic.printBoard(board);
        graphic.printAlert("Evento " + super.getName() + " terminato!...");
        graphic.waitForUser("premi per continuare...");  
    }
}
