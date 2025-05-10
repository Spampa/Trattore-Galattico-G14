package eventCards;

import entities.Player;
import gameEvents.EventType;

public class SimpleEventCard extends EventCard {
    public SimpleEventCard(String name, String description, EventType type) {
        super(name, description, type);
    }

    @Override
    public void executeEvent(Player[] players) {
        getType().execute(players);
    }
}