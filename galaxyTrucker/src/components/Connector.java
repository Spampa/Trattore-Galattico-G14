package components;

public enum Connector {
	EMPTY(0), SINGLE(1), DOUBLE(2), UNIVERSAL(3);
	
	private int number;
	
	Connector(int number){
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
}
