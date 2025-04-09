package components.models;
import components.Component;
import components.Connector;
import components.Rotatable;

public class Pipe extends Component implements Rotatable {
	
	public Pipe(Connector[] connectors ) {
		super(connectors);
	}

	@Override
	public void rotate() {
		super.orientation = super.orientation.rotate();
	}
	
	@Override
	public String toString() {
		return "Component: Pipe" + "\n" +
				super.toString();
	}
}
