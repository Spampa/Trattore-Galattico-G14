package items.enums;

public enum WareType {
	REDWARE("Red Ware", 4, false), YELLOWARE("Yellow Ware", 3, true), GREENWARE("Green Ware", 2, true), BLUEWARE("Blue Ware", 1, true);
	
	private final String name;
	private final int value;
	private final boolean common;
	
	WareType(String name, int value, boolean common){
		this.name = name;
		this.value = value;
		this.common = common;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isCommon() {
		return common;
	}
}
