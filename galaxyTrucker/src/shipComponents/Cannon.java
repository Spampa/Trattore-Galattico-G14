package shipComponents;

public class Cannon extends Component {
	private final ComponentType type;
	private double firePower;
	private final int batteryRequired;
	
	public Cannon(ComponentType type, Connector[] connectors ) {
		//exception
		if(connectors[Side.UP.getNumber()] != null) {
			//TODO manage exception, there isn't connectors at the top of the Cannon
		}
		
		this.type = type;
		super(connectors);
		firePower = type.getNumber();
		
		if(type == ComponentType.DOUBLE) {
			batteryRequired = 1;
		}
		else {
			batteryRequired = 0;
		}
	}
	
	public ComponentType getType() {
		return type;
	}
	
	public double getFirePower() {
		return firePower;
	}
	
	@Override
	public void rotate() {
		super.rotate();
		if(super.getOrientation() == Side.UP) {
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
