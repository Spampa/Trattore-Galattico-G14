package components.models;

import components.Component;
import components.Connector;
import items.enums.AlienType;

public class LifeSupport extends Component {
	
	private final AlienType type;
	
	public LifeSupport(Connector[] connectors, AlienType type) {
		super(connectors);
		this.type = type;
	}
	
	public AlienType getAlienType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "Life support for " + type + "aliens\n" + super.toString();
	}

}
