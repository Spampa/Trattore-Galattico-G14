package components.models;

import components.BatteryComponent;
import components.Component;
import components.Connector;
import components.Rotatable;
import components.enums.Side;

public class Shield extends Component implements Rotatable, BatteryComponent {
	
	private final int batteryRequired;
	
	public Shield(Connector[] connectors) {
		super(connectors);
		this.batteryRequired = 1;
	}
	
	public int getBatteryRequired() {
		return batteryRequired;
	}
	
	@Override
	public void rotate() {
		super.orientation = super.orientation.rotate();
	}
	
	public boolean isSideProtected(Side side) {
		if(side == super.orientation || side == super.orientation.rotate()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Component: Shield" + "\n" +
				"Battery Required: " + batteryRequired + "\n" + 
				"Sides protecteds: " + super.orientation + ", " + super.orientation.rotate() + "\n" +
				super.toString();
	}
	
}


