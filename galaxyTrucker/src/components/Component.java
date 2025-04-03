package components;
import components.enums.Side;
import gameEvents.Actions.ProjectileType;

public abstract class Component {
	private final Connector connectors[]; //element 0: top of the square, element 2: bottom of the square
	protected Side orientation = Side.UP;
	private boolean broke = false;
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

	public boolean tryBreak(ProjectileType projectile){  // TODO implement this method to prevent not exposed component to be broke by small asteroid, and if it the component is broken set the broke parameter to true;
		this.broke = true;
		return true;
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

    public boolean isBroke() {
        return broke;
    }
}
