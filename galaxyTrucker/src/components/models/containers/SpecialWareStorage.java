package components.models.containers;

import components.Connector;
import components.enums.ContainerSize;

public class SpecialWareStorage extends WareStorage {
	private final static int MIN_SIZE = 1;
	
	public SpecialWareStorage(ContainerSize size, Connector[] connectors) {
		super(size.toInteger() + MIN_SIZE, connectors);
	}
	
	@Override
	public String toString() {
		return "Container of Special Wares" + "\n" + super.toString();
	}
	
}
