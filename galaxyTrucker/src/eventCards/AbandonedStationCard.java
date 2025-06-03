package eventCards;

import entities.*;
import entities.board.Board;
import events.AddItem;
import items.Ware;
import randomizer.ItemsRandomizer;

import java.util.ArrayList;
import java.util.Random;
import ui.Graphic;

public class AbandonedStationCard extends Card {
	
	private final int spacemanCost;

    public AbandonedStationCard(Graphic graphic, int spacemanCost) {
        super(graphic, "Stazione Abbandonata", "", 1);
        this.spacemanCost = spacemanCost;
    }
    
    public AbandonedStationCard(Graphic graphic) {
        super(graphic, "Stazione Abbandonata", "", 1);
        Random r = new Random();
        this.spacemanCost = r.nextInt(4)+2;
    }


    @Override
    public void execute(Board b) {
    	ArrayList<Player> players = b.getPlayers();
    	
    	ItemsRandomizer ir = new ItemsRandomizer();
    	Ware[] wares = ir.getRandomWares(1, 4);
    	
    	String s = "";
    	for(Ware w : wares) {
    		s += w.getName() + ", ";
    	}
    	graphic.printMessage("La stazione abbandonata contiene: " + s);
    	
    	for(Player p: players) {
    		if(p.getShip().getSpacemans() < this.spacemanCost) {
    			graphic.printAlert(p.getName() + " OOOPS! non hai abbastanza equipaggio per approfittare dell' evento!");
    		}
    		else if(graphic.askUser(p.getName()+  " vuoi atterrare sulla stazione abbandonata?")) {
    			AddItem a = new AddItem(graphic, wares, p);
    			a.start();
    			super.lostFlyDays(b, p);
    			return;
    		}
    	}
    }

}