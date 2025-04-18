package components.models.containers;

import components.Connector;
import items.Ware;

public abstract class WareStorage extends Container<Ware>{

	public WareStorage(int maxCapacity, Connector[] connectors) {
		super(maxCapacity, connectors);
	}

}
