package eventCards;

import java.util.ArrayList;
import java.util.Collections;

import components.Component;
import components.models.containers.BatteryStorage;
import components.models.containers.WareStorage;
import entities.Player;
import entities.Position;
import entities.board.Board;
import entities.ship.Ship;
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
		ArrayList<Battery> batteriesofplayer=null;
		ArrayList<Item> itemstoremove=new ArrayList<Item>();
		
		for(Player p:players) {
			boolean toBePunished=false;
			graphic.printMessage(p.getName()+" sei il prossimo bersaglio!");
			if(p.getShip().getMaxFirePower()>=firepower) {
				if(graphic.askUser("Hai i mezzi per difenderti! Lo vuoi fare?")){
					float fp=p.getShip().getFirePower();
					if(fp>firepower) {
						graphic.printMessage("Hai vinto! Ecco il tuo bottino: "+wares.toString()); //orribile ma yolo
						if(graphic.askUser("Lo accetti?")) {
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
				waresofplayer=getWares(p.getShip());
				batteriesofplayer=getBatteries(p.getShip());
				for(int i=1;i<=waresToLose;i++) {
					if(waresofplayer.size()>0)
					{
						itemstoremove.add(waresofplayer.getFirst());
						waresofplayer.removeFirst();
					}
					else {
						if(batteriesofplayer.size()>0) {
							itemstoremove.add(batteriesofplayer.getFirst());
							batteriesofplayer.removeFirst();
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
	
	//questi due metodi potrebbero essere spostati dentro ship (da chiedere a Simone)
	private ArrayList<Ware> getWares(Ship s){
		ArrayList<Ware> wares=new ArrayList<Ware>();
		Component c=null;
		for(int i=0;i<s.getGameLevel().getBoardY();i++) {
			for(int j=0;j<s.getGameLevel().getBoardX();j++) {
				c=s.getComponent(new Position(j,i));
				if(c instanceof WareStorage) {
					wares.addAll(((WareStorage)c).getContent());
				}
			}
		}
		Collections.sort(wares);
		return wares;
	}
	
	private ArrayList<Battery> getBatteries(Ship s){
		ArrayList<Battery> batteries=new ArrayList<Battery>();
		Component c=null;
		for(int i=0;i<s.getGameLevel().getBoardY();i++) {
			for(int j=0;j<s.getGameLevel().getBoardX();j++) {
				c=s.getComponent(new Position(j,i));
				if(c instanceof BatteryStorage) {
					batteries.addAll(((BatteryStorage)c).getContent());
				}
			}
		}
		return  batteries;
	}

}
