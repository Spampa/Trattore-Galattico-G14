package randomizer;
import components.Component;
import components.Connector;
import components.enums.Side;

import java.util.Random;

public class ConnectorsRandomizer {
	public Connector[] getRandomConnectors() {
		Random r = new Random();
		Connector[] connectors = new Connector[Component.sideCount];
		
		for(int i = 0; i < Component.sideCount; i++) {
			int randomNumber = r.nextInt(Component.sideCount);
			connectors[i] = Connector.intToConnector(randomNumber);
		}
		
		return connectors;
	}
	
	public Connector[] getRandomConnectors(Side exceptSide) {
		Random r = new Random();
		Connector[] connectors = new Connector[Component.sideCount];
		
		for(int i = 0; i < Component.sideCount; i++) {
			if(i == exceptSide.getNumber()) {
				connectors[i] = Connector.EMPTY;
				continue;
			}
			int randomNumber = r.nextInt(Component.sideCount);
			connectors[i] = Connector.intToConnector(randomNumber);
		}
		
		return connectors;
	}
}
