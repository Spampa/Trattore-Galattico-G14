package eventCards;

import entities.Player;
import events.EventType;

public class SimpleEventCard extends Card {
    public SimpleEventCard(String name, String description, EventType type) {
        super(name, description, type);
    }

    @Override
    public void executeEvent(Player[] players) {
        getType().execute(players);
    }
}