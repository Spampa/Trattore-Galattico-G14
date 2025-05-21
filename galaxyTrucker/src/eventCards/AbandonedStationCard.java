package eventCards;

import entities.*;
import entities.board.Board;
import events.AddItem;
import java.util.ArrayList;
import java.util.Random;
import ui.Graphic;

public class AbandonedStationCard extends Card {
	
	private final int spacemanCost;

    public AbandonedStationCard(GameLevel level, Graphic graphic, int spacemanCost) {
        super(graphic, "Stazione Abbandonata", "",  level, 1);
        this.spacemanCost = spacemanCost;
    }
    
    public AbandonedStationCard(GameLevel level, Graphic graphic) {
        super(graphic, "Stazione Abbandonata", "",  level, 1);
        Random r = new Random();
        this.spacemanCost = r.nextInt(4)+2;
    }


    @Override
    public void execute(Board b) {
    	ArrayList<Player> players = b.getPlayers();
    	
    	for(Player p: players) {
    		if(p.getShip().getSpacemans() < this.spacemanCost) {
    			graphic.printAlert(p.getName() + " OOOPS! non hai abbastanza equipaggio per approfittare del evento!");
    			break;
    		}
    		if(graphic.askUser(p.getName()+  " vuoi atterrare sulla stazione abbandonata?")) {
    			AddItem a = new AddItem(graphic, p);
    			a.start();
    			super.lostFlyDays(b, p);
    			return;
    		}
    	}
    }

}