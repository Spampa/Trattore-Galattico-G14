package eventCards;

import java.util.ArrayList;

import entities.GameLevel;
import entities.Player;
import entities.board.Board;
import events.RemoveItem;
import items.Item;
import items.Spaceman;
import ui.Graphic;

public class AbandonedShip extends Card{
	
	private Item[] spacemen;
	private int credits;
	
	public AbandonedShip(Graphic graphic, GameLevel level) {
		super(graphic, "Nave abbandonata", "", level, Deck.getRandom(level.toInt(), 2));
		this.credits=Deck.getRandom(level.toInt(),4);
		this.spacemen=new Item[Deck.getRandom(level.toInt(),3)];
		for(int i=0; i<spacemen.length;i++) {
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
					graphic.printMessage("Crediti posseduti: "+p.getCosmicCredit());
					super.lostFlyDays(board, p);
					break;
				}
				graphic.printMessage(p.getName()+" hai scelto di non sbarcare!");
			}
			else {
				graphic.printAlert("Non puoi sbarcare su questa nave!");
			}
		}
		graphic.printBoard(board);
		graphic.printAlert("Evento " + super.getName() + " terminato!...");
        graphic.waitForUser("premi per continuare...");   	
	}
}
