package items;

import items.enums.WareType;

public class Ware implements Item {
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
}
