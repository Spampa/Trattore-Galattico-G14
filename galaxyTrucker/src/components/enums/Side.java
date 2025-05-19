package components.enums;

public enum Side {
	UP(0), RIGHT(1), DOWN(2), LEFT(3);
	
	private final int number;
	
	Side(int number){
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public Side rotate() {
		switch(this) {
			case UP:
				return RIGHT;
			case RIGHT:
				return DOWN;
			case DOWN:
				return LEFT;
			default:
				return UP;
		}
	}
	
	public static Side intToSide(int i) {
		switch(i) {
			case 0: return UP;
			
			case 1: return RIGHT;
			
			case 2: return DOWN;
			
			case 3: return LEFT;
			
			default: throw new IllegalArgumentException("value isn't in the acceptable range (0-3)");
		}
	}
}
