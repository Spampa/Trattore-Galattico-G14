package eventCards;

import java.util.Collections;
import java.util.Stack;
import gameEvents.EventType;

public class Deck {
    private Stack<EventCard> cards;

    public Deck() {
        this.cards = new Stack<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        cards.add(new SimpleEventCard(
            "Campo Asteroidi", 
            "2 danni casuali alla nave", 
            EventType.ASTEROID_FIELD
        ));
        
        cards.add(new SimpleEventCard(
            "Incursione Pirata", 
            "3 proiettili da destra", 
            EventType.PIRATE_RAID
        ));
        
        cards.add(new SimpleEventCard(
            "Stazione Abbandonata", 
            "Vendi alieni per crediti", 
            EventType.ABANDONED_STATION
        ));
        
        cards.add(new SimpleEventCard(
            "Nave Schiavista", 
            "Perdi 1 membro equipaggio", 
            EventType.SLAVER_SHIP
        ));
        
        cards.add(new SimpleEventCard(
            "Impatto Cometa", 
            "Distrugge una colonna", 
            EventType.COMET_STRIKE
        ));
        
        cards.add(new SimpleEventCard(
            "Avamposto Mercantile", 
            "Vendi merci a prezzi doppi", 
            EventType.MERCHANT_OUTPOST
        ));
        
        cards.add(new SimpleEventCard(
            "Sabotaggio", 
            "Perdi 1 componente casuale", 
            EventType.SABOTAGE
        ));
        
        cards.add(new SimpleEventCard(
            "Tempesta Cosmica", 
            "Danno a tutti i sistemi", 
            EventType.COSMIC_STORM
        ));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public EventCard drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }
}