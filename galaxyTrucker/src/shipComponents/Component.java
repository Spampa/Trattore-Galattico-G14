package shipComponents;

public class Component {
	private final ComponentType type;
	private final Connector connectors[]; //element 0: top of the square, element 2: bottom of the square
	private Side orientation = Side.UP;
	
	public static final int sideCount = 4;
	
	public Component(ComponentType type, Connector[] connectors) {
		this.type = type;
		if(connectors.length != sideCount) {
			//TODO: throw new error if connectors.length != 4
		}
		this.connectors = connectors;
	}
	
	public ComponentType getType() {
		return type;
	}
	
	public Connector[] getConnectors() {
		return connectors;
	}
	
	public Connector getConnector(Side side) {
		return connectors[(orientation.getNumber() + side.getNumber()) % sideCount];
	}
	
	public void rotate() {
		orientation = orientation.rotate();
	}
	
	@Override
	public String toString() {
		String s = "Type: " + type + "\n";
		s += getConnector(Side.UP) + "\n";
		s += getConnector(Side.LEFT) + "\n";
		s += getConnector(Side.DOWN) + "\n";
		s += getConnector(Side.RIGHT) + "\n";
		s += "Orientation: " + orientation;
		return s;
	}
	
	public static void main(String[] args) {
		Connector[] connectors = new Connector[4];
		
		connectors[0] = Connector.SINGLE;
		connectors[1] = Connector.DOUBLE;
		connectors[2] = Connector.TRIPLE;
		connectors[3] = Connector.EMPTY;
		
		Component c = new Component(ComponentType.DOUBLE_CANNON, connectors);
		System.out.println(c + "\n");
		
		c.rotate();
		System.out.println(c);
	}
}
