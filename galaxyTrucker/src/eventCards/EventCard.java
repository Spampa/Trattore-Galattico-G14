package eventCards;

import gameEvents.EventType;

public interface EventCard {
    String getName();
    String getDescription();
    EventType getType();
}
