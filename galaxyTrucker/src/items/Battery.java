package items;

public class Battery implements Item {
	private int power = 0;
	private final String name = "Battery";
	
	public Battery() {
		this.power = 1;
	}
	
	public Battery(int power) {
		this.power = power;
	}
	
	public int getBattery() {
		return power;
	}
	
	public String getName() {
		return name;
	}
}
