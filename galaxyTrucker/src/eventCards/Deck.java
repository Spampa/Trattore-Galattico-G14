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
        cards.add(new SimpleEventCard(
            "Abandoned Station",
            "Guadagni 3 crediti se hai almeno 1 alieno a bordo",
            EventType.ABANDONED_STATION
        ));
        cards.add(new SimpleEventCard(
            "Slavers",
            "Perdi 1 membro dell'equipaggio a caso",
            EventType.SLAVERS
        ));
        cards.add(new SimpleEventCard(
            "Comet",
            "Danno a tutta una colonna della nave",
            EventType.COMET
        ));
        cards.add(new SimpleEventCard(
            "Merchant Planet",
            "Vendi merci per crediti (1-3 per tipo)",
            EventType.MERCHANT_PLANET
        ));
        cards.add(new SimpleEventCard(
            "Sabotage",
            "Perdi 1 componente non essenziale a caso",
            EventType.SABOTAGE
        ));
        cards.add(new SimpleEventCard(
            "Cosmic Radiation",
            "Tutti gli equipaggi subiscono 1 danno",
            EventType.COSMIC_RADIATION
        ));
        // TODO aggiungere tutte le altre carte
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public EventCard drawCard() {
        return cards.isEmpty() ? null : cards.pop();

        //idea di sviluppo: switch(card.getType()){case ASTEROIDS[...], case PIRATE_ATTACK[...], ecc...}
    }
}