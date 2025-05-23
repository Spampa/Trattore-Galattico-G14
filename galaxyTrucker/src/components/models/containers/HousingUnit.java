package components.models.containers;

import components.Connector;
import items.Spaceman;

public class HousingUnit extends Container<Spaceman> {
	private final static int SIZE = 5;
	private final static int START_SPACEMAN = 2;
	private final boolean core;
	
	public HousingUnit(Connector[] connectors) {
		super(SIZE, connectors);
		this.core = false;
		this.fill(START_SPACEMAN);
	}
	
	public HousingUnit(Connector[] connectors, int quantity) {
		super(SIZE, connectors);
		this.core = false;
		this.fill(quantity);
	}

	public HousingUnit(Connector[] connectors, boolean core) {
		super(SIZE, connectors);
		this.core = core;
		this.fill(START_SPACEMAN);
	}
	
	public HousingUnit(Connector[] connectors, boolean core, int quantity) {
		super(SIZE, connectors);
		this.core = core;
		this.fill(quantity);
	}
	
	public boolean isCore() {
		return core;
	}
	
	private void fill(int numOfItems) {
		for(int i = 0; i < numOfItems; i++) {
			super.add(new Spaceman());
		}
	}
	
	@Override
	public String toString() {
		return "Container of Spaceman" + "\n" + super.toString();
	}
}
