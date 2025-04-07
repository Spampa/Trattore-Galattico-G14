package components.types.containers;

import components.Connector;

public class NormalStorage extends Container {
	private final ContainerSize size;
	private ContentType[] wares;

	public NormalStorage(ContainerSize size, Connector[] connectors) {
		super(
				size == ContainerSize.SMALL ? 2 : 3,
				connectors
			);
		this.size = size;
		wares = new ContentType[size == ContainerSize.SMALL ? 2 : 3];
	}
	
	public ContainerSize getSize() {
		return size;
	}

	@Override
	public boolean add(ContentType content) {
		if(content == ContentType.ASTRONAUT || content == ContentType.BATTERY || content == ContentType.RED_WARES) {
			//TODO: error
			System.out.println("Error only common wares are allowed");
			return false;
		}
		
		if(super.getCurrentCapacity() >= super.getMaxCapacity()) {
			//TODO: throw error
			System.out.println("Error the container is filled");
			return false;
		}

		wares[super.getCurrentCapacity()] = content;
		super.increment();
		return true;
	}

	@Override
	public boolean remove() {
		super.decrement();
		wares[super.getCurrentCapacity()] = null;
		return true;
	}
	
	public ContentType[] getWares() {
		return wares;
	}
	
	@Override
	public String toString() {
		return "Container: Normal Storage\n" + super.toString();
	}
}
