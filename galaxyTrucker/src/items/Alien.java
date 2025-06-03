package items;

import items.enums.AlienType;

public class Alien implements Item {
	private final AlienType type;
	private final String name;
	
	public Alien(AlienType type) {
		this.type = type;
		name = "Alien " + type;
	}
	
	public AlienType getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}
}
