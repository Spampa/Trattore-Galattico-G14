package components.types;

import components.Component;
import components.Connector;
import components.enums.MountType;
import components.enums.Side;

public class Engine extends Component {
	private final MountType type;
	private final int batteryRequired;
	
	public Engine(MountType type, Connector[] connectors) {
		super(connectors);
		//exception
		if(connectors[Side.DOWN.getNumber()] != null) {
			//TODO: manage exception, there isn't connectors at the bottom of the engine
		}
		
		this.type = type;
		
		if(type == MountType.DOUBLE) {
			batteryRequired = 1;
		}
		else {
			batteryRequired = 0;
		}
	}
	
	@Override
	public String toString() {
		return "Component: Engine" + "\n" +
				"Type: " + type + "\n" +
				"Battery Required: " + batteryRequired + "\n" +
				super.toString();
	}
	
}
