package eventCards;

import java.util.ArrayList;
import java.util.Arrays;
import entities.Player;
import entities.board.Board;
import events.AddItem;
import events.RemoveItem;
import items.Battery;
import items.Item;
import items.Ware;
import ui.Graphic;

public class Smugglers extends Card {

	private int firepower;
	private int waresToLose;
	private Ware[] wares;
	
	public Smugglers(Graphic graphic, int lostdays, int firepower, int waresToLose, Ware[] wares) {
		super(graphic, "Contrabbandieri", "", lostdays);
		this.firepower=firepower;
		this.waresToLose=waresToLose;
		this.wares=wares;
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
				RemoveItem r=new RemoveItem(graphic,(Item[]) itemstoremove.toArray(),p);
				r.start();
			}
		}
		graphic.printAlert("Evento" + super.getName() + " terminato!...");
        graphic.waitForUser("premi per continuare...");    
	}
}
