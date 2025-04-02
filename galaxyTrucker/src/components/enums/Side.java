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
}
