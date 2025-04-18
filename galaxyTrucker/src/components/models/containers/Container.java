package components.models.containers;

import java.util.ArrayList;
import java.util.List;

import components.Component;
import components.Connector;
import components.Rotatable;
import items.Item;

public abstract class Container<T extends Item> extends Component implements Rotatable {
	private final int maxCapacity;
	private List<T> content;
	
	public Container(int maxCapacity, Connector[] connectors) {
		super(connectors);
		
		this.maxCapacity = maxCapacity;
		content = new ArrayList<T>();	
	}
	
	public boolean add(T item) {
		if(content.size() >= maxCapacity) {
			return false;
		}
		return content.add(item);
	}
	
	public T remove() {
		return content.removeFirst();
	}
	
	public int getCurrentCapacity() {
		return content.size();
	}
	
	public List<T> getContent() {
		return content;
	}

	@Override
	public void rotate() {
		super.orientation = super.orientation.rotate();
	}
	
	@Override
	public String toString() {
		return "Capacity: " + content.size() + "/" + maxCapacity + "\n" + super.toString();
	}
}
