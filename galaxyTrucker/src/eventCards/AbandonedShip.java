package eventCards;

import java.util.ArrayList;

import entities.Player;
import entities.board.Board;
import events.RemoveItem;
import items.Item;
import items.Spaceman;
import ui.Graphic;

public class AbandonedShip extends Card{
	
	private Item[] spacemen;
	private int credits;
	
	public AbandonedShip(Graphic graphic, int lostdays,int spacemen, int credits) {
		super(graphic, "Nave abbandonata", "", lostdays);
		this.credits=credits;
		this.spacemen=new Item[spacemen];
		for(int i=0; i<spacemen;i++) {
			this.spacemen[i]=new Spaceman();
		}
	}

	@Override
	public void execute(Board board) {
		ArrayList<Player> players=board.getPlayers();
		for(Player p:players) {
			
			if(p.getShip().getSpacemans()>=spacemen.length) {
				if(graphic.askBooleanUser(p.getName()+" vuoi salire sulla nave abbandonata?")) {
					RemoveItem loseSpacemen= new RemoveItem(graphic, spacemen, p);
					loseSpacemen.start();
					p.addCosmicCredit(credits);
					super.lostFlyDays(board, p);
					break;
				}
				graphic.printMessage(p.getName()+" hai scelto di non sbarcare!");
			}
			else {
				graphic.printAlert("Non puoi sbarcare su questa nave!");
			}
		}
		graphic.printAlert("Evento" + super.getName() + " terminato!...");
        graphic.waitForUser("premi per continuare...");   	
	}
}
