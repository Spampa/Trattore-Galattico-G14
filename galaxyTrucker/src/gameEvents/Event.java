package gameEvents;

import gameEvents.Actions.*;

public abstract  class Event {
    private final Action[] actions;
    private final EventType type;

    public Event(Action[] actions, EventType type){
        this.actions = actions;
        this.type = type;
    }

    public abstract void startEvent();

    public EventType getType() {
        return type;
    }


}
