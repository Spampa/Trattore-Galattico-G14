package randomizer;

import java.util.ArrayList;
import java.util.List;

import components.Component;
import components.enums.MountType;
import components.enums.Side;
import components.models.*;

public class ComponentRandomizer {
	
	private ConnectorsRandomizer connectorsRandomizer = new ConnectorsRandomizer();
	private List<Component> componentSet;
	
	public ComponentRandomizer() {
		componentSet = new ArrayList<Component>();
	}
	
	public List<Component> generateComponentsSet() {
		//generate components
		componentSet.addAll(this.generatePipes(8));
		
		componentSet.addAll(this.generateCannons(25, MountType.SINGLE));
		componentSet.addAll(this.generateCannons(11, MountType.DOUBLE));
		
		componentSet.addAll(this.generateEngines(21, MountType.SINGLE));
		componentSet.addAll(this.generateEngines(9, MountType.DOUBLE));
		
		return componentSet;
	}
	
	private List<Component> generateCannons(int max, MountType t) {
		List<Component> s = new ArrayList<Component>();
		for(int i = 0; i < max; i++) {
			s.add(new Cannon(t, connectorsRandomizer.getRandomConnectors(Side.UP)));
		}
		return s;
	}
	
	private List<Component> generateEngines(int max, MountType t) {
		List<Component> s = new ArrayList<Component>();
		for(int i = 0; i < max; i++) {
			s.add(new Engine(t, connectorsRandomizer.getRandomConnectors(Side.DOWN)));
		}
		return s;
	}
	
	private List<Component> generatePipes(int max) {
		List<Component> s = new ArrayList<Component>();
		for(int i = 0; i < max; i++) {
			s.add(new Pipe(connectorsRandomizer.getRandomConnectors()));
		}
		return s;
	}
}
