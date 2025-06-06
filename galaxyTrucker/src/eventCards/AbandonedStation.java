package eventCards;

import entities.*;
import entities.board.Board;
import events.AddItem;
import items.Ware;

import java.util.ArrayList;
import ui.Graphic;

public class AbandonedStation extends Card {
	
	private final int spacemanCost;

    public AbandonedStation(Graphic graphic, GameLevel level) {
        super(graphic, "Stazione Abbandonata", "", 1);
        this.spacemanCost = Deck.getRandom(level.toInt(), 5);
    }
    
    
    @Override
    public void execute(Board b) {
    	ArrayList<Player> players = b.getPlayers();
    	Ware[] wares = Deck.generateWares(level.toInt()+1, 4);
    	
    	String s = "";
    	for(Ware w : wares) {
    		s += w.getName() + ", ";
    	}
    	graphic.printMessage("La stazione abbandonata contiene: " + s);
    	
    	for(Player p: players) {
    		if(p.getShip().getSpacemans() < this.spacemanCost) {
    			graphic.printAlert(p.getName() + " OOOPS! non hai abbastanza equipaggio per approfittare dell' evento!");
    		}
    		else if(graphic.askBooleanUser(p.getName()+  " vuoi atterrare sulla stazione abbandonata?")) {
    			AddItem a = new AddItem(graphic, wares, p);
    			a.start();
    			super.lostFlyDays(b, p);
    			break;
    		}
    	}
    	
    	graphic.printAlert("Evento" + super.getName() + " terminato!...");
        graphic.waitForUser("premi per continuare..."); 
    }

}