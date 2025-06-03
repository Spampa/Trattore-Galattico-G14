package eventCards;

import entities.GameLevel;
import events.Planet;
import items.Ware;
import randomizer.ItemsRandomizer;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import ui.Graphic;

public class Deck {
    private final Stack<Card> cards;
    private final Graphic graphic;
    private final GameLevel level;

    public Deck(GameLevel level, Graphic graphic) {
        this.cards = new Stack<>();
        this.graphic = graphic;
        this.level=level;
        initializeDeck();
    }

    private void initializeDeck() {
    	
    		cards.add(new PirateAttackCard(graphic));
    		cards.add(new SlaverShipCard(graphic));
    		cards.add(new Smugglers(graphic,1,getRandom(level.toInt(),6),getRandom(level.toInt(),2),generateWares(1,3)));
    		cards.add(new CombatZone(graphic));
    		cards.add(new StarDustCard(graphic));
    		cards.add(new AbandonedStationCard(graphic));
    		cards.add(new AbandonedStationCard(graphic));
    		cards.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),2), getRandom(level.toInt(),3)));
    		cards.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),2), getRandom(level.toInt(),3)));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new Planets(graphic, GameLevel.I, getRandom(level.toInt(),2), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new Planets(graphic, GameLevel.I, getRandom(level.toInt(),2), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new Planets(graphic, GameLevel.I, getRandom(level.toInt(),2), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new Planets(graphic, GameLevel.I, getRandom(level.toInt(),2), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new AsteroidSwarm(graphic));
    		cards.add(new AsteroidSwarm(graphic));
    		cards.add(new AsteroidSwarm(graphic));
    	
    	if(level.toInt() >= GameLevel.II.toInt()) {
    		cards.add(new PirateAttackCard(graphic));
    		cards.add(new SlaverShipCard(graphic));
    		cards.add(new Smugglers(graphic,1,getRandom(level.toInt(),7),getRandom(level.toInt(),3),generateWares(2,4)));
    		cards.add(new CombatZone(graphic));
    		cards.add(new StarDustCard(graphic));
    		cards.add(new AbandonedStationCard(graphic));
    		cards.add(new AbandonedStationCard(graphic));
    		cards.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),3), getRandom(level.toInt(),4)));
    		cards.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),3), getRandom(level.toInt(),4)));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new Planets(graphic, GameLevel.II, getRandom(level.toInt(),3), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new Planets(graphic, GameLevel.II, getRandom(level.toInt(),3), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new Planets(graphic, GameLevel.II, getRandom(level.toInt(),3), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new Planets(graphic, GameLevel.II, getRandom(level.toInt(),3), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new AsteroidSwarm(graphic));
    		cards.add(new AsteroidSwarm(graphic));
    		cards.add(new AsteroidSwarm(graphic));
    		cards.add(new EpidemicCard(graphic));
    	}
    	
    	if(level.toInt() >= GameLevel.III.toInt()) {
    		cards.add(new PirateAttackCard(graphic));
    		cards.add(new SlaverShipCard(graphic));
    		cards.add(new Smugglers(graphic,2,getRandom(level.toInt(),8),getRandom(level.toInt(),4),generateWares(3,5)));
    		cards.add(new CombatZone(graphic));
    		cards.add(new AbandonedStationCard(graphic));
    		cards.add(new AbandonedStationCard(graphic));
    		cards.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),4), getRandom(level.toInt(),5)));
    		cards.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),4), getRandom(level.toInt(),5)));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new OpenSpaceCard(graphic));
    		cards.add(new Planets(graphic, GameLevel.III, getRandom(level.toInt(),4), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new Planets(graphic, GameLevel.III, getRandom(level.toInt(),4), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new Planets(graphic, GameLevel.III, getRandom(level.toInt(),4), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new Planets(graphic, GameLevel.III, getRandom(level.toInt(),4), generatePlanets(getRandom(level.toInt(),1), level)));
    		cards.add(new AsteroidSwarm(graphic));
    		cards.add(new AsteroidSwarm(graphic));
    		cards.add(new AsteroidSwarm(graphic));
    		cards.add(new EpidemicCard(graphic));
    		cards.add(new AlienSabotageCard(graphic));
    	}
       
        Collections.shuffle(cards);
    }
    
    private int getRandom(int number, int offset) {
    	return new Random().nextInt(number)+ offset;
    }
    
	private Planet[] generatePlanets(int length, GameLevel level) {
		String[] planetsNames = new String[] {"Marte", "Giove", "Venere", "Saturno"};
		Planet[] planets = new Planet[length];
		
		for(int i = 0; i < length; i++) {
			planets[i] = new Planet(graphic, planetsNames[i], this.generateWares(level.toInt() + 1, 5));
		}
		
		return planets;
	}
    
    private Ware[] generateWares(int min, int max) {
    	ItemsRandomizer r = new ItemsRandomizer();
    	return r.getRandomWares(min, max);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }
}