package components.models.containers;

import components.Connector;
import components.enums.ContainerSize;
import items.Battery;

public class BatteryStorage extends Container<Battery> {
	private final static int MIN_SIZE = 2;
	private final ContainerSize size;
	
	public BatteryStorage(ContainerSize size, Connector[] connectors) {
		super(size.toInteger() + MIN_SIZE, connectors);
		this.size = size;
		this.fill(super.getMaxCapacity());
	}
	
	public BatteryStorage(ContainerSize size, Connector[] connectors, int quantity) {
		super(size.toInteger() + MIN_SIZE, connectors);
		this.size = size;
		this.fill(quantity);
	}
	
	@Override
	public String toString() {
		return "Container of Battery" + "\n" + super.toString();
	}
	
	private void fill(int numOfItems) {
		if(numOfItems > super.getMaxCapacity()) throw new IllegalArgumentException("numOfItems is more than container size");
		
		for(int i = 0; i < numOfItems; i++) {
			super.add(new Battery());
		}
	}
	
	public ContainerSize getContainerSize() {
		return size;
	}
}
