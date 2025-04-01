package shipComponents;

public enum ComponentType {
	//TODO: add other types
	SINGLE(1), DOUBLE(2);
	
	private final int number;
	
	ComponentType(int number){
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
}
