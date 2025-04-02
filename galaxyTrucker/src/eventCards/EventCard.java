package eventCards;

import gameEvents.Event;

public interface EventCard {
    Event generateEvent();  
    String getName();      
    String getDescription(); //ho messo la descrizione nel caso dovessimo fare la ui o qualcosa di grafico effettivamente.
}