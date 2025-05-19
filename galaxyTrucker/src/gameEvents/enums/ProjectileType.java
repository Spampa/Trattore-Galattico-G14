package gameEvents.enums;

public enum ProjectileType {
    BIG_CANNON, SMALL_CANNON, BIG_ASTEROID, SMALL_ASTEROID;
	
	//add counters
	
	public static ProjectileType intToPType(int i) {
		switch(i) {
		case 0: return BIG_CANNON;
		
		case 1: return SMALL_CANNON;
		
		case 2: return BIG_ASTEROID;
		
		case 3: return SMALL_ASTEROID;
		
		default: throw new IllegalArgumentException("value isn't in the acceptable range (0-3)");
	}
	}
}
