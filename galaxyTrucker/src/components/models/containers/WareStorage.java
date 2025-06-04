package components.models.containers;

import components.Connector;
import items.Ware;
import items.enums.WareType;

public abstract class WareStorage extends Container<Ware>{

	public WareStorage(int maxCapacity, Connector[] connectors) {
		super(maxCapacity, connectors);
	}
	
	
	public Ware remove(String wt) {
		for(int i = 0; i < super.getContent().size(); i++) {
			if(super.getContent().get(i).getName() == wt) {
				Ware w = super.getContent().get(i);
				super.remove(i);
				return w;
			}
		}
		return null;
	}

}
