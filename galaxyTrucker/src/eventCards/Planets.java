package eventCards;

import java.util.ArrayList;
import java.util.Random;

import entities.GameLevel;
import entities.Player;
import entities.board.Board;
import events.Planet;
import randomizer.ItemsRandomizer;
import ui.Graphic;

public class Planets extends Card {
	
	private Planet[] planets;
	public static final int MIN_PLANETS = 2;
	public static final int MAX_PLANETS = 4;
	private ItemsRandomizer itemsRandomizer = new ItemsRandomizer();
	

	/*public Planets(Graphic graphic, GameLevel level, int numberOfPlanets) {
		super(graphic, "Planets", "", level, new Random().nextInt(level.toInt())+2);
		
		if(numberOfPlanets < MIN_PLANETS || numberOfPlanets > MAX_PLANETS) throw new IllegalArgumentException("Number of planets must be between 2 and 4.");
		this.planets = new Planet[numberOfPlanets];
		
	}*/
	
	public Planets(Graphic graphic, GameLevel level, int lostdays, Planet[] planets) {
		super(graphic, "Planets", "", level, lostdays);
		
		this.planets = planets;
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
				if(graphic.askUser(player.getName() + " vuoi atterrare?")) {
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
			graphic.printMessage(planet.getPlayer().getName() + " inizia lo sbarco: ");
			planet.start();
			super.lostFlyDays(board, planet.getPlayer());
		}
	}
	
	private boolean areAllPlayersLandedOnAllPlanets() {
		for(Planet planet : planets) {
			if(planet.getPlayer() == null) return false;
		}
		return true;
	}

}
