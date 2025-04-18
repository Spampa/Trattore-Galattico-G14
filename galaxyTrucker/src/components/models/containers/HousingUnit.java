package components.models.containers;

import components.Connector;
import items.Spaceman;

public class HousingUnit extends Container<Spaceman> {
	private final static int SIZE = 5;
	private final boolean core;
	
	public HousingUnit(Connector[] connectors) {
		super(SIZE, connectors);
		this.core = false;
	}

	public HousingUnit(Connector[] connectors, boolean core) {
		super(SIZE, connectors);
		this.core = core;
	}
	
	public boolean isCore() {
		return core;
	}
	
	@Override
	public String toString() {
		return "Container of Spaceman" + "\n" + super.toString();
	}
}
