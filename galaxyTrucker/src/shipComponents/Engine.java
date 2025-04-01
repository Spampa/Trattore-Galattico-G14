package shipComponents;

public class Engine extends Component {
	private final ComponentType type;
	private final int batteryRequired;
	
	public Engine(ComponentType type, Connector[] connectors) {
		//exception
		if(connectors[Side.DOWN.getNumber()] != null) {
			//TODO manage exception, there isn't connectors at the bottom of the engine
		}
		
		this.type = type;
		super(connectors);
		
		if(type == ComponentType.DOUBLE) {
			batteryRequired = 1;
		}
		else {
			batteryRequired = 0;
		}
	}
	
	@Override
	public void rotate() {
		return;
	}
	
	@Override
	public String toString() {
		return "Component: Engine" + "\n" +
				"Type: " + type + "\n" +
				"Battery Required: " + batteryRequired + "\n" +
				super.toString();
	}
	
	public static void main(String[] args) {
		Connector[] connectors = new Connector[4];
		
		connectors[0] = Connector.SINGLE;
		connectors[1] = Connector.DOUBLE;
		connectors[2] = Connector.TRIPLE;
		connectors[3] = Connector.EMPTY;
		
		Component c = new Engine(ComponentType.DOUBLE, connectors);
		System.out.println(c + "\n");
			
		c.rotate();
		System.out.println(c);
	}
	
}
