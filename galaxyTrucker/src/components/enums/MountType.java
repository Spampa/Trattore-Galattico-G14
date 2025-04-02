package components.enums;

public enum MountType {
	//TODO: add other types
	SINGLE(1), DOUBLE(2);
	
	private final int number;
	
	MountType(int number){
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
}
