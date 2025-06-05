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
    		
    		addCardsFromLvl1();
    		
    		if(level.toInt()>1) {
    			addCardsFromLvl2();
    		}
    		if(level.toInt()>2) {
    			addCardsFromLvl3();
    		}
       
        Collections.shuffle(cards);
    }
    
    private void addCardsFromLvl1(){
    	Stack<Card> temp=new Stack<Card>();
    	int number=8;
    	temp.add(new PirateAttackCard(graphic));
		temp.add(new SlaverShipCard(graphic));
		temp.add(new Smugglers(graphic,1,getRandom(level.toInt(),6),getRandom(level.toInt(),2),generateWares(1,3)));
		temp.add(new CombatZone(graphic));
		temp.add(new StarDustCard(graphic));
		temp.add(new AbandonedStationCard(graphic));
		temp.add(new AbandonedStationCard(graphic));
		temp.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),2), getRandom(level.toInt(),3)));
		temp.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),2), getRandom(level.toInt(),3)));
		temp.add(new OpenSpaceCard(graphic));
		temp.add(new OpenSpaceCard(graphic));
		temp.add(new OpenSpaceCard(graphic));
		temp.add(new OpenSpaceCard(graphic));
		temp.add(new Planets(graphic, GameLevel.I, getRandom(level.toInt(),2), generatePlanets(getRandom(level.toInt(),1), level)));
		temp.add(new Planets(graphic, GameLevel.I, getRandom(level.toInt(),2), generatePlanets(getRandom(level.toInt(),1), level)));
		temp.add(new Planets(graphic, GameLevel.I, getRandom(level.toInt(),2), generatePlanets(getRandom(level.toInt(),1), level)));
		temp.add(new Planets(graphic, GameLevel.I, getRandom(level.toInt(),2), generatePlanets(getRandom(level.toInt(),1), level)));
		temp.add(new AsteroidSwarm(graphic));
		temp.add(new AsteroidSwarm(graphic));
		temp.add(new AsteroidSwarm(graphic));
		Collections.shuffle(temp);
		if(level!=GameLevel.I) {
			number=4;
		}
		for(int i=0;i<number;i++) {
			cards.add(temp.pop());
		}
		
    }
    
    private void addCardsFromLvl2(){
    	Stack<Card> temp=new Stack<Card>();
    	int number=8;
    	temp.add(new PirateAttackCard(graphic));
    	temp.add(new SlaverShipCard(graphic));
    	temp.add(new Smugglers(graphic,1,getRandom(level.toInt(),7),getRandom(level.toInt(),3),generateWares(2,4)));
    	temp.add(new CombatZone(graphic));
    	temp.add(new StarDustCard(graphic));
    	temp.add(new AbandonedStationCard(graphic));
    	temp.add(new AbandonedStationCard(graphic));
    	temp.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),3), getRandom(level.toInt(),4)));
    	temp.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),3), getRandom(level.toInt(),4)));
    	temp.add(new OpenSpaceCard(graphic));
    	temp.add(new OpenSpaceCard(graphic));
    	temp.add(new OpenSpaceCard(graphic));
    	temp.add(new Planets(graphic, GameLevel.II, getRandom(level.toInt(),3), generatePlanets(getRandom(level.toInt(),1), level)));
    	temp.add(new Planets(graphic, GameLevel.II, getRandom(level.toInt(),3), generatePlanets(getRandom(level.toInt(),1), level)));
    	temp.add(new Planets(graphic, GameLevel.II, getRandom(level.toInt(),3), generatePlanets(getRandom(level.toInt(),1), level)));
    	temp.add(new Planets(graphic, GameLevel.II, getRandom(level.toInt(),3), generatePlanets(getRandom(level.toInt(),1), level)));
    	temp.add(new AsteroidSwarm(graphic));
    	temp.add(new AsteroidSwarm(graphic));
    	temp.add(new AsteroidSwarm(graphic));
    	temp.add(new EpidemicCard(graphic));
		Collections.shuffle(temp);
		if(level!=GameLevel.II) {
			number=4;
		}
		for(int i=0;i<number;i++) {
			cards.add(temp.pop());
		}
		
    }
    
    private void addCardsFromLvl3(){
    	Stack<Card> temp=new Stack<Card>();
    	int number=8;
    	temp.add(new PirateAttackCard(graphic));
    	temp.add(new SlaverShipCard(graphic));
    	temp.add(new Smugglers(graphic,2,getRandom(level.toInt(),8),getRandom(level.toInt(),4),generateWares(3,5)));
    	temp.add(new CombatZone(graphic));
    	temp.add(new AbandonedStationCard(graphic));
    	temp.add(new AbandonedStationCard(graphic));
    	temp.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),4), getRandom(level.toInt(),5)));
    	temp.add(new AbandonedShip(graphic, getRandom(level.toInt(),2), getRandom(level.toInt(),4), getRandom(level.toInt(),5)));
    	temp.add(new OpenSpaceCard(graphic));
    	temp.add(new OpenSpaceCard(graphic));
    	temp.add(new OpenSpaceCard(graphic));
    	temp.add(new Planets(graphic, GameLevel.III, getRandom(level.toInt(),4), generatePlanets(getRandom(level.toInt(),1), level)));
    	temp.add(new Planets(graphic, GameLevel.III, getRandom(level.toInt(),4), generatePlanets(getRandom(level.toInt(),1), level)));
    	temp.add(new Planets(graphic, GameLevel.III, getRandom(level.toInt(),4), generatePlanets(getRandom(level.toInt(),1), level)));
    	temp.add(new Planets(graphic, GameLevel.III, getRandom(level.toInt(),4), generatePlanets(getRandom(level.toInt(),1), level)));
    	temp.add(new AsteroidSwarm(graphic));
    	temp.add(new AsteroidSwarm(graphic));
    	temp.add(new AsteroidSwarm(graphic));
    	temp.add(new EpidemicCard(graphic));
    	temp.add(new AlienSabotageCard(graphic));
		Collections.shuffle(temp);
		for(int i=0;i<number;i++) {
			cards.add(temp.pop());
		}
		
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