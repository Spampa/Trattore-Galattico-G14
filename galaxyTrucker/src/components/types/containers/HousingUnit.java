package components.types.containers;

import components.Connector;

public class HousingUnit extends Container {
	private final boolean core;
	
	public HousingUnit(Connector[] connectors) {
		super(2, connectors);
		core = false;
	}
	
	public HousingUnit(Connector[] connectors, boolean core) {
		super(2, connectors);
		this.core = core;
	}

	@Override
	public void add(ContentType content) {
		if(content != ContentType.ASTRONAUT) {
			//TODO: throw error
			System.out.println("Error Only astronaut allowed");
			return;
		}
		super.increment();
	}

	@Override
	public void remove() {
		super.decrement();
	}
	
	public boolean isCore() {
		return core;
	}
	
	@Override
	public String toString() {
		return "Container: Housing Unit\n"
				+ "Is the core: " + core + "\n"
				+ super.toString();
	}
}
