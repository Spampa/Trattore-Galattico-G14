package components.types.containers;

import components.Connector;

public class SpecialStorage extends Container {
	private final ContainerSize size;
	private ContentType[] wares;

	public SpecialStorage(ContainerSize size, Connector[] connectors) {
		super(
				size == ContainerSize.SMALL ? 1 : 2,
				connectors
			);
		this.size = size;
		wares = new ContentType[size == ContainerSize.SMALL ? 1 : 2];
	}
	
	public ContainerSize getSize() {
		return size;
	}

	@Override
	public void add(ContentType content) {
		if(content == ContentType.ASTRONAUT || content == ContentType.BATTERY) {
			//TODO: error
			System.out.println("Error only wares are allowed");
			return;
		}
		
		if(super.getCurrentCapacity() >= super.getMaxCapacity()) {
			//TODO: throw error
			System.out.println("Error the container is filled");
			return;
		}
		wares[super.getCurrentCapacity()] = content;
		super.increment();
	}

	@Override
	public void remove() {
		super.decrement();
		wares[super.getCurrentCapacity()] = null;
	}
	
	public ContentType[] getWares() {
		return wares;
	}
	
	@Override
	public String toString() {
		return "Container: Special Storage\n" + super.toString();
	}
}
