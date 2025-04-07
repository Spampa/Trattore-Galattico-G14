package components.types.containers;

import components.Connector;

public class BatteryContainer extends Container {
	private final ContainerSize size;
	
	public BatteryContainer(ContainerSize size, Connector[] connectors) {
		super(
				size == ContainerSize.SMALL ? 2 : 3,
				connectors
			);
		this.size = size;
	}
	
	public ContainerSize getSize() {
		return size;
	}

	@Override
	public boolean add(ContentType content) {
		if(content != ContentType.BATTERY) {
			// TODO Error only batteries allowed
			System.out.println("Error Only batteries allowed");
			return false;
		}
		
		super.increment();
		return true;
	}

	@Override
	public boolean remove() {
		super.decrement();
		return true;
	}
	
	@Override
	public String toString() {
		return "Container: Battery Container\n" + super.toString();
	}
}
