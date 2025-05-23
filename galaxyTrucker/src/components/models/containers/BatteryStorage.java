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
		this.fill();
	}
	
	@Override
	public String toString() {
		return "Container of Battery" + "\n" + super.toString();
	}
	
	private void fill() {
		for(int i = 0; i < super.getMaxCapacity(); i++) {
			super.add(new Battery());
		}
	}
	
	public ContainerSize getContainerSize() {
		return size;
	}
}
