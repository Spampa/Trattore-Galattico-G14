package components.models;

import components.BatteryComponent;
import components.Component;
import components.Connector;
import components.enums.MountType;
import components.enums.Side;

public class Engine extends Component implements BatteryComponent {
	private final MountType type;
	private final int batteryRequired;
	
	public Engine(MountType type, Connector[] connectors) {
		super(connectors);
		//exception
		if(connectors[Side.DOWN.getNumber()] != Connector.EMPTY) {
			//TODO: manage exception, there isn't connectors at the bottom of the engine
			System.out.println("Not possible create a connector in the DOWN side");
		}
		
		this.type = type;
		
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

	public int getBatteryRequired() {
		return batteryRequired;
	}
	
	public int getEnginePower() {
		return type.getNumber();
	}
	
	@Override
	public String toString() {
		return "Component: Engine" + "\n" +
				"Type: " + type + "\n" +
				"Battery Required: " + batteryRequired + "\n" +
				super.toString();
	}
	
}
