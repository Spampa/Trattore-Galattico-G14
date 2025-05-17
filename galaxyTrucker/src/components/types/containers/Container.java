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
	
	public int getCurrentCapacity() {
		return currentCapacity;
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	protected void increment() {
		if(currentCapacity + 1 > maxCapacity) {
			//TODO: throw error
			System.out.println("The container is full");
			return;
		}
		currentCapacity++;
	}
	
	protected void decrement() {
		if(currentCapacity > 0) {
			currentCapacity--;
		}
	}
	
	public abstract void add(ContentType content);
	public abstract void remove();
	
	@Override
	public String toString() {
		return "Max Capacity: " + maxCapacity + "\n"
				+ "Current Capacity: " + currentCapacity + "\n"
				+ super.toString();
	}
}
