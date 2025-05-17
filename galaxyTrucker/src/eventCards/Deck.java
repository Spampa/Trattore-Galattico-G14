package eventCards;

import ui.Graphic;
import java.util.Collections;
import java.util.Stack;
import flightBoard.Board;

public class Deck {
    private final Stack<Card> cards;

    public Deck(Board board, Graphic graphic) {
        this.cards = new Stack<>();
        initializeDeck(board, graphic);
    }

    private void initializeDeck(Board board, Graphic graphic) {
        cards.add(new SlaverShipCard(board, graphic));
        cards.add(new AlienSabotageCard(board, graphic));
        cards.add(new AsteroidFieldCard(board, graphic));
        
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }
}