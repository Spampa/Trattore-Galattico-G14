package components.models.containers;

public enum ContentType {
	BATTERY(0), ASTRONAUT(0), RED_WARES(4), YELLOW_WARES(3), GREEN_WARES(2), BLUE_WARES(1);
	
	private final int value;
	
	ContentType(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
