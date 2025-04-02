package eventCards;

import gameEvents.Event;
import gameEvents.Action;

public class SimpleEventCard implements EventCard {
    private final String name;
    private final String description;
    private final Action[] actions;  

    public SimpleEventCard(String name, String description, Action... actions) {
        this.name = name;
        this.description = description;
        this.actions = actions;
    }

    @Override
    public Event generateEvent() {
        return new Event(actions, EventType.CARD_EVENT) {
            @Override
            public void startEvent() {
                
                for (Action action : actions) {
                    
                }
            }
        };
    }

    @Override
    public String getName() { return name; }
    @Override
    public String getDescription() { return description; }

}