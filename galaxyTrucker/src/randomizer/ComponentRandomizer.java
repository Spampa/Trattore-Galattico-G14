package randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import components.Component;
import components.enums.*;
import components.models.*;
import components.models.containers.*;
import entities.GameLevel;
import items.enums.AlienType;

public class ComponentRandomizer {
	
	private ConnectorsRandomizer connectorsRandomizer = new ConnectorsRandomizer();
	private List<Component> componentSet;
	private final GameLevel level;
	
	public ComponentRandomizer(GameLevel level) {
		this.level = level;
		componentSet = new ArrayList<Component>();
	}
	
	public List<Component> generateComponentsSet() {
		//generate components
		componentSet.addAll(this.generatePipes(8));
		
		componentSet.addAll(this.generateCannons(25, MountType.SINGLE));
		componentSet.addAll(this.generateCannons(11, MountType.DOUBLE));
		
		componentSet.addAll(this.generateEngines(21, MountType.SINGLE));
		componentSet.addAll(this.generateEngines(9, MountType.DOUBLE));
		
		componentSet.addAll(this.generateBatteriesContainer(6, ContainerSize.BIG));
		componentSet.addAll(this.generateBatteriesContainer(11, ContainerSize.SMALL));
		
		componentSet.addAll(this.generateSpecialStorage(3, ContainerSize.BIG));
		componentSet.addAll(this.generateSpecialStorage(6, ContainerSize.SMALL));
		
		componentSet.addAll(this.generateNormalStorage(6, ContainerSize.BIG));
		componentSet.addAll(this.generateNormalStorage(9, ContainerSize.SMALL));
		
		componentSet.addAll(this.generateSpacemanUnit(17));
		
		componentSet.addAll(this.generateShields(8));
		
		if(level.toInt() >= 2) {
			componentSet.addAll(this.generateLifeSupport(7, AlienType.PURPLE));
			componentSet.addAll(this.generateLifeSupport(7, AlienType.BROWN));
		}
		
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
	
	private List<Component> generateBatteriesContainer(int max, ContainerSize size) {
		List<Component> s = new ArrayList<Component>();
		for(int i = 0; i < max; i++) {
			s.add(new BatteryStorage(size, connectorsRandomizer.getRandomConnectors()));
		}
		return s;
	}
	
	private List<Component> generateSpecialStorage(int max, ContainerSize size) {
		List<Component> s = new ArrayList<Component>();
		for(int i = 0; i < max; i++) {
			s.add(new SpecialWareStorage(size, connectorsRandomizer.getRandomConnectors()));
		}
		return s;
	}
	
	private List<Component> generateNormalStorage(int max, ContainerSize size) {
		List<Component> s = new ArrayList<Component>();
		for(int i = 0; i < max; i++) {
			s.add(new NormalWareStorage(size, connectorsRandomizer.getRandomConnectors()));
		}
		return s;
	}
	
	private List<Component> generateSpacemanUnit(int max) {
		List<Component> s = new ArrayList<Component>();
		for(int i = 0; i < max; i++) {
			s.add(new SpacemanUnit(connectorsRandomizer.getRandomConnectors()));
		}
		return s;
	}
	
	private List<Component> generateLifeSupport(int max, AlienType type) {
		List<Component> s = new ArrayList<Component>();
		for(int i = 0; i < max; i++) {
			s.add(new LifeSupport(connectorsRandomizer.getRandomConnectors(), type));
		}
		return s;
	}
	
	private List<Component> generateShields(int max) {
		List<Component> s = new ArrayList<Component>();
		for(int i = 0; i < max; i++) {
			s.add(new Shield(connectorsRandomizer.getRandomConnectors()));
		}
		return s;
	}
}
