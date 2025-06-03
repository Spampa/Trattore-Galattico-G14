package items.enums;

public enum AlienType {
	PURPLE, BROWN;
	
	public static AlienType toAlien(int value) {
		switch(value) {
			case 0: return AlienType.PURPLE;
			case 1: return AlienType.BROWN;
			default: throw new IllegalArgumentException("Value passed can't be parsed to AlienType");
		}
	}
}
