package eventCards;

import java.util.ArrayList;

import entities.GameLevel;
import entities.Player;
import entities.board.Board;
import events.AddItem;
import events.RemoveItem;
import items.Battery;
import items.Item;
import items.Ware;
import ui.Graphic;

public class Smugglers extends Card {

	private final int firepower;
	private final int waresToLose;
	private final Ware[] wares;
	
	public Smugglers(Graphic graphic, GameLevel level) {
		super(graphic, "Contrabbandieri", "",level, 2);
		this.firepower=Deck.getRandom(level.toInt(), 8);
		this.waresToLose=Deck.getRandom(level.toInt(), 3);
		this.wares=Deck.generateWares(2, 5);
	}

	@Override
	public void execute(Board board) {
		
		ArrayList<Player> players=board.getPlayers();
		ArrayList<Ware> waresofplayer=null;
		int batteriesofplayer=0;
		ArrayList<Item> itemstoremove=new ArrayList<Item>();
		
		for(Player p:players) {
			boolean toBePunished=false;
			graphic.printMessage(p.getName()+" sei il prossimo bersaglio!");
			if(p.getShip().getMaxFirePower()>=firepower) {
				if(graphic.askBooleanUser("Hai i mezzi per difenderti! Lo vuoi fare?")){
					float fp=p.getShip().getFirePower();
					if(fp>firepower) {
						graphic.printMessage("Hai vinto! Ecco il tuo bottino: "+wares.toString()); //orribile ma yolo
						if(graphic.askBooleanUser("Lo accetti?")) {
							AddItem putWares=new AddItem(graphic,wares,p);
							putWares.start();
							super.lostFlyDays(board, p);
							break;
						}
						else {
							graphic.printMessage("Hai rifiutato di caricare le merci! Peggio per te...");
						}
						break;
					}
					else if(fp==firepower) {
						graphic.printMessage("Pareggio! Prossima vittima");
					}
					else {
						toBePunished=true;
					}
				}
				else {
					toBePunished=true;
				}
			}
			else {
				toBePunished=true;
			}
			
			if(toBePunished) {
				graphic.printMessage("Devi perdere parti dei tuoi beni!");
				waresofplayer=p.getShip().getWares();
				batteriesofplayer=p.getShip().getBatteries();
				for(int i=0;i<waresToLose;i++) {
					if(waresofplayer.size()>0)
					{
						itemstoremove.add(waresofplayer.get(i));
					}
					else {
						if(batteriesofplayer>0) {
							itemstoremove.add(new Battery());
							batteriesofplayer--;
						}
					}
				}
				Item[] it=new Item[itemstoremove.size()];
				for(int i=0;i<it.length;i++) {
					it[i]=itemstoremove.get(i);
				}
				RemoveItem r=new RemoveItem(graphic,it,p);
				r.start();
				itemstoremove.clear();
			}
		}
		graphic.printBoard(board);
		graphic.printAlert("Evento " + super.getName() + " terminato!...");
        graphic.waitForUser("premi per continuare...");    
	}
}
