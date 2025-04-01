package shipComponents;

public abstract class Component {
	private final Connector connectors[]; //element 0: top of the square, element 2: bottom of the square
	private Side orientation = Side.UP;
	
	public static final int sideCount = 4;
	
	public Component(Connector[] connectors) {
		if(connectors.length != sideCount) {
			//TODO: throw new error if connectors.length != 4
		}
		this.connectors = connectors;
	}
	
	public Connector[] getConnectors() {
		return connectors;
	}
	
	public Connector getConnector(Side side) {
		return connectors[(orientation.getNumber() + side.getNumber()) % sideCount];
	}
	
	public Side getOrientation() {
		return orientation;
	}
	
	public void rotate() {
		orientation = orientation.rotate();
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "UP Connector: " + getConnector(Side.UP) + "\n";
		s += "LEFT Connector: " + getConnector(Side.LEFT) + "\n";
		s += "DOWN Connector: " + getConnector(Side.DOWN) + "\n";
		s += "RIGHT Connector: " + getConnector(Side.RIGHT) + "\n";
		s += "Orientation: " + orientation;
		return s;
	}
}
