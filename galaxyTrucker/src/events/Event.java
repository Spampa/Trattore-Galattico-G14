package events;

import ui.Graphic;

public abstract class Event {
	protected Graphic graphic;
	
	public Event(Graphic graphic) {
		this.graphic = graphic;
	}
	
    public abstract void start();
}
