package items;

import items.enums.WareType;

public class Ware implements Item, Comparable<Ware> {
	private final WareType type;
	
	public Ware(WareType type) {
		this.type = type;
	}
	
	public boolean isCommon() {
		return type.isCommon();
	}
	
	public int getValue() {
		return type.getValue();
	}

	@Override
	public String getName() {
		return type.getName();
	}

	@Override
	public int compareTo(Ware o) {				//serve in smugglers
		if(this.getValue()>o.getValue()) {
			return -1;
		}
		else if(this.getValue()==o.getValue()) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
