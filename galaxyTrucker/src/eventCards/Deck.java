package eventCards;

import entities.GameLevel;
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
        cards.add(new Sabotage(graphic));
        //initializeDeck();
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
    	temp.add(new Pirates(graphic, GameLevel.I));
		temp.add(new SlaverShipCard(graphic));
		temp.add(new Smugglers(graphic, GameLevel.I));
		temp.add(new CombatZone(graphic, GameLevel.I));
		temp.add(new StarDustCard(graphic));
		temp.add(new AbandonedStation(graphic, GameLevel.I));
		temp.add(new AbandonedStation(graphic, GameLevel.I));
		temp.add(new AbandonedShip(graphic, GameLevel.I));
		temp.add(new AbandonedShip(graphic, GameLevel.I));
		temp.add(new OpenSpace(graphic));
		temp.add(new OpenSpace(graphic));
		temp.add(new OpenSpace(graphic));
		temp.add(new OpenSpace(graphic));
		temp.add(new Planets(graphic, GameLevel.I));
		temp.add(new Planets(graphic, GameLevel.I));
		temp.add(new Planets(graphic, GameLevel.I));
		temp.add(new Planets(graphic, GameLevel.I));
		temp.add(new AsteroidSwarm(graphic, GameLevel.I));
		temp.add(new AsteroidSwarm(graphic, GameLevel.I));
		temp.add(new AsteroidSwarm(graphic, GameLevel.I));
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
    	temp.add(new Pirates(graphic, GameLevel.II));
    	temp.add(new SlaverShipCard(graphic));
    	temp.add(new Smugglers(graphic, GameLevel.II));
    	temp.add(new CombatZone(graphic, GameLevel.II));
    	temp.add(new StarDustCard(graphic));
    	temp.add(new AbandonedStation(graphic, GameLevel.II));
    	temp.add(new AbandonedStation(graphic, GameLevel.II));
    	temp.add(new AbandonedShip(graphic, GameLevel.II));
    	temp.add(new AbandonedShip(graphic, GameLevel.II));
    	temp.add(new OpenSpace(graphic));
    	temp.add(new OpenSpace(graphic));
    	temp.add(new OpenSpace(graphic));
    	temp.add(new Planets(graphic, GameLevel.II));
    	temp.add(new Planets(graphic, GameLevel.II));
    	temp.add(new Planets(graphic, GameLevel.II));
    	temp.add(new Planets(graphic, GameLevel.II));
    	temp.add(new AsteroidSwarm(graphic, GameLevel.II));
    	temp.add(new AsteroidSwarm(graphic, GameLevel.II));
    	temp.add(new AsteroidSwarm(graphic, GameLevel.II));
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
    	temp.add(new Pirates(graphic, GameLevel.III));
    	temp.add(new SlaverShipCard(graphic));
    	temp.add(new Smugglers(graphic, GameLevel.III));
    	temp.add(new CombatZone(graphic, GameLevel.III));
    	temp.add(new AbandonedStation(graphic, GameLevel.III));
    	temp.add(new AbandonedStation(graphic, GameLevel.III));
    	temp.add(new AbandonedShip(graphic, GameLevel.III));
    	temp.add(new AbandonedShip(graphic, GameLevel.III));
    	temp.add(new OpenSpace(graphic));
    	temp.add(new OpenSpace(graphic));
    	temp.add(new OpenSpace(graphic));
    	temp.add(new Planets(graphic, GameLevel.III));
    	temp.add(new Planets(graphic, GameLevel.III));
    	temp.add(new Planets(graphic, GameLevel.III));
    	temp.add(new Planets(graphic, GameLevel.III));
    	temp.add(new AsteroidSwarm(graphic, GameLevel.III));
    	temp.add(new AsteroidSwarm(graphic, GameLevel.III));
    	temp.add(new AsteroidSwarm(graphic, GameLevel.III));
    	temp.add(new EpidemicCard(graphic));
    	temp.add(new Sabotage(graphic));
		Collections.shuffle(temp);
		for(int i=0;i<number;i++) {
			cards.add(temp.pop());
		}
		
    }
    
    protected static int getRandom(int base, int offset) {		//richiamato ogni volta che devo generare un numero random con offset (num astronauti, giorni di volo, potenza di fuoco richiesta ecc)
    	return new Random().nextInt(base)+ offset;
    }
    
    protected static Ware[] generateWares(int min, int max) {	//richiamato ogni volta che devo generare un set di wares
    	ItemsRandomizer r = new ItemsRandomizer();
    	return r.getRandomWares(min, max);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }
}