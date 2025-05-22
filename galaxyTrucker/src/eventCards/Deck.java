package eventCards;

import entities.GameLevel;
import java.util.Collections;
import java.util.Stack;
import ui.Graphic;

public class Deck {
    private final Stack<Card> cards;

    public Deck(GameLevel level, Graphic graphic) {
        this.cards = new Stack<>();
        initializeDeck(level, graphic);
    }

    private void initializeDeck(GameLevel level, Graphic graphic) {
        cards.add(new SlaverShipCard(level, graphic));
        cards.add(new AlienSabotageCard(graphic));
        cards.add(new AsteroidSwarm(level, graphic));
        
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }
}