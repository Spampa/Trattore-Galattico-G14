package components.types.containers;
import components.Component;
import components.Connector;

public abstract class Container extends Component {
	private final int maxCapacity;
	private int currentCapacity;
	
	public Container(int maxCapacity, Connector[] connectors) {
		super(connectors);
		this.maxCapacity = maxCapacity;
		this.currentCapacity = 0;
	}
	
	public abstract void add(ContentType content);
	
	public int getCurrentCapacity() {
		return currentCapacity;
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	public void remove() {
		if(currentCapacity > 0) {
			currentCapacity--;
		}
	}
	
	@Override
	public String toString() {
		return "Max Capacity: " + maxCapacity + "\n"
				+ "Current Capacity: " + currentCapacity + "\n"
				+ super.toString();
	}
}
