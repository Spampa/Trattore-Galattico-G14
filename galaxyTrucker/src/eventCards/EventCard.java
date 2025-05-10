package eventCards;

import entities.Player;
import gameEvents.EventType;

public abstract class EventCard {
    private final String name;
    private final String description;
    private final EventType type;

    protected EventCard(String name, String description, EventType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public EventType getType() { return type; }

    public abstract void executeEvent(Player[] players);
}