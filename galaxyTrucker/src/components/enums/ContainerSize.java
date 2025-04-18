package components.enums;

public enum ContainerSize {
	SMALL(0), BIG(1);
	
	private final int size;
	
	ContainerSize(int size){
		this.size = size;
	}
	
	public int toInteger() {
		return size;
	}
}
