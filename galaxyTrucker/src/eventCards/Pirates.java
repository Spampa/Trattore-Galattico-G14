package eventCards;

import java.util.ArrayList;

import components.enums.Side;
import entities.GameLevel;
import entities.Player;
import entities.board.Board;
import events.Shoot;
import gameEvents.enums.ProjectileType;
import ui.Graphic;

public class Pirates extends Card {
    private final int firepower;
    private final int credits;
    private final Shoot[] hits;

    public Pirates(Graphic graphic, GameLevel level) {
        super(graphic, "Pirati", "",level, Deck.getRandom(level.toInt(),2));
		this.firepower=Deck.getRandom(level.toInt(), 8);
		this.credits=Deck.getRandom(2*level.toInt(), 10);
		this.hits=new Shoot[Deck.getRandom(level.toInt(), 5)];
		for(int i = 0; i < hits.length; i++) {
    		hits[i] = new Shoot(graphic, Side.intToSide(Deck.getRandom(4, 0)),ProjectileType.intToPType(Deck.getRandom(2, 0)));
    	}
    }

    @Override
    public void execute(Board board) {
    	ArrayList<Player> targets=new ArrayList<Player>();
		
		for(Player p:board.getPlayers()) {
			boolean toBePunished=false;
			graphic.printMessage(p.getName()+" sei il prossimo bersaglio!");
			if(p.getShip().getMaxFirePower()>=firepower) {
				if(graphic.askBooleanUser("Hai i mezzi per difenderti! Lo vuoi fare?")){
					float fp=p.getShip().getFirePower();
					if(fp>firepower) {
						graphic.printMessage("Hai vinto! Ecco il tuo bottino: "+credits); //orribile ma yolo
						if(graphic.askBooleanUser("Lo accetti?")) {
							p.addCosmicCredit(credits);
							super.lostFlyDays(board, p);
							break;
						}
						else {
							graphic.printMessage("Come pensi di vincere senza i crediti cosmici? Peggio per te...");
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
				targets.add(p);
			}
		}
		
		if(targets.size()>0) {
			for(Shoot h:hits) {
				for(Player t:targets) {
					h.setPlayer(t);
					h.start();
				}
			}
		}
		
		graphic.printAlert("Evento" + super.getName() + " terminato!...");
        graphic.waitForUser("premi per continuare...");    
    }    
}