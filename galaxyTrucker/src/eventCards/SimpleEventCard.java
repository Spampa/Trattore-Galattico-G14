package eventCards;

import entities.Ship;
import gameEvents.EventType;

public class SimpleEventCard extends EventCard {
    public SimpleEventCard(String name, String description, EventType type) {
        super(name, description, type);
    }

    @Override
    public void executeEvent(Ship ship) {
        getType().execute(ship);
    }
}