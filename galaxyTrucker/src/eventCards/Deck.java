package eventCards;

import java.util.Collections;
import java.util.Stack;
import gameEvents.EventType;

public class Deck {
    private Stack<EventCard> cards;

    public Deck() {
        this.cards = new Stack<>();
        initializeBaseDeck();
        shuffle();
    }

    private void initializeBaseDeck() {
        cards.add(new SimpleEventCard(
            "Asteroid Field",
            "La nave subisce 2 danni",
            EventType.ASTEROIDS
        ));
        cards.add(new SimpleEventCard(
            "Pirate Attack",
            "2 proiettili a sinistra",
            EventType.PIRATE_ATTACK
        ));

        // TODO aggiungere tutte le altre carte
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public EventCard drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }
}