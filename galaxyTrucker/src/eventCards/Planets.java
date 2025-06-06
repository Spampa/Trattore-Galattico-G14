package eventCards;

import java.util.ArrayList;

import entities.GameLevel;
import entities.Player;
import entities.board.Board;
import events.Planet;
import ui.Graphic;

public class Planets extends Card {
	
	private Planet[] planets;

	/*public Planets(Graphic graphic, GameLevel level, int numberOfPlanets) {
		super(graphic, "Planets", "", level, new Random().nextInt(level.toInt())+2);
		
		if(numberOfPlanets < MIN_PLANETS || numberOfPlanets > MAX_PLANETS) throw new IllegalArgumentException("Number of planets must be between 2 and 4.");
		this.planets = new Planet[numberOfPlanets];
		
	}*/
	
	public Planets(Graphic graphic, GameLevel level) {
		super(graphic, "Planets", "", level, Deck.getRandom(level.toInt(),3));  //giorni di volo sono randomici in base a livello della carta
		this.planets = new Planet[Deck.getRandom(level.toInt(), 2)];			//idem numero di pianeti (lvl 1 avrà massimo 2 pianeti, lvl 2 massimo 4)
		this.generatePlanets();
	}

	@Override
	public void execute(Board board) {
		ArrayList<Player> players = board.getPlayers();
		
		for(Planet planet : planets) {
			graphic.printMessage(planet.getName() + " contiene: " + planet.toStringItems());
		}
		
		for(Player player : players) {
			if(areAllPlayersLandedOnAllPlanets()) break;
			
			int planetIndex;
			boolean isPlanetSet = true;
			do {
				if(graphic.askBooleanUser(player.getName() + " vuoi atterrare?")) {
					planetIndex = graphic.askIntUser( player.getName() + " seleziona il pianeta su cui atterrare ", 1, planets.length) - 1;
					isPlanetSet = planets[planetIndex].getPlayer() != null;
					
					if(!isPlanetSet) {
						graphic.printMessage(player.getName() + " atterrato su " + planets[planetIndex].getName());
						planets[planetIndex].setPlayer(player);
					}
					else {
						graphic.printAlert("Pianeta gia' occupato da " + planets[planetIndex].getPlayer().getName() + "!");
					}
				}
				else {
					isPlanetSet = false;
				}
			}while(isPlanetSet);
		}
		
		for(Planet planet : planets) {
			if(planet.getPlayer() == null) {
				continue;
			}
			graphic.printMessage(planet.getPlayer().getName() + " inizia lo sbarco: ");
			planet.start();
			super.lostFlyDays(board, planet.getPlayer());
		}
	}
	
	private Planet[] generatePlanets() {
		String[] planetsNames = new String[] {"Marte", "Giove", "Venere", "Saturno"};
		
		for(int i = 0; i < planets.length; i++) {
			planets[i] = new Planet(super.graphic, planetsNames[i], Deck.generateWares(level.toInt() + 1, 5)); //numero di merci sarà mediamente maggiore per livelli di partita più difficili
		}
		
		return planets;
	}
	
	private boolean areAllPlayersLandedOnAllPlanets() {
		for(Planet planet : planets) {
			if(planet.getPlayer() == null) return false;
		}
		return true;
	}

}
