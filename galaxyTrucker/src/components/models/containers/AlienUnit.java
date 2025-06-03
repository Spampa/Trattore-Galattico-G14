package components.models.containers;

import components.Connector;
import items.Alien;
import items.enums.AlienType;

public class AlienUnit extends Container<Alien> {
	private final static int SIZE = 1;
	private final static int START_ALIENS = 1;
	private final AlienType type;
	
	public AlienUnit(Connector[] connectors, AlienType type) {
		super(SIZE, connectors);
		this.type = type;
		this.fill(START_ALIENS);
	}
	
	public AlienUnit(Connector[] connectors, AlienType type, int quantity) {
		super(SIZE, connectors);
		this.type = type;
		this.fill(quantity);
	}
	
	private void fill(int numOfItems) {
		if(numOfItems > super.getMaxCapacity()) throw new IllegalArgumentException("numOfItems is more than container size");
		
		for(int i = 0; i < numOfItems; i++) {
			super.add(new Alien(type));
		}
	}
	
	@Override
	public String toString() {
		return "Container of "+ type + " aliens" + "\n" + super.toString();
	}
}
