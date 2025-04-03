package components.types;

import components.Component;
import components.Connector;
import components.Rotatable;
import components.enums.MountType;
import components.enums.Side;

public class Cannon extends Component implements Rotatable {
	private final MountType type;
	private double firePower;
	private final int batteryRequired;
	
	public Cannon(MountType type, Connector[] connectors ) {
		super(connectors);
		
		//exception
		if(connectors[Side.UP.getNumber()] != null) {
			//TODO manage exception, there isn't connectors at the top of the Cannon
		}
		this.type = type;
		
		firePower = type.getNumber();
		
		if(type == MountType.DOUBLE) {
			batteryRequired = 1;
		}
		else {
			batteryRequired = 0;
		}
	}
	
	public MountType getType() {
		return type;
	}
	
	public double getFirePower() {
		return firePower;
	}
	
	@Override
	public void rotate() {
		super.orientation = super.orientation.rotate();
		if(super.orientation == Side.UP) {
			firePower = type.getNumber();
		}
		else {
			firePower = (type.getNumber() * 0.5);
		}
	}
	
	@Override
	public String toString() {
		return "Component: Cannon" + "\n" +
				"Type: " + type + "\n" +
				"Battery Required: " + batteryRequired + "\n" + 
				"Fire Power: " + firePower + "\n" +
				super.toString();
	}
}
