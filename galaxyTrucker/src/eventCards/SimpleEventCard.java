package eventCards;

import gameEvents.EventType;

public class SimpleEventCard implements EventCard {
    private final String name;
    private final String description;
    private final EventType type;

    public SimpleEventCard(String name, String description, EventType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public EventType getType() { return type; }
}