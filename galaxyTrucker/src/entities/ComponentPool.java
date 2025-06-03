package entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import components.Component;
import randomizer.ComponentRandomizer;

public class ComponentPool {
	
	private static List<Component> discardedComponents;
	private static List<Component> pool;
	private Component lastDrawComponent;
	
	public ComponentPool(GameLevel level) {
		ComponentRandomizer cr = new ComponentRandomizer(level);
		
		pool = cr.generateComponentsSet();
		discardedComponents = new ArrayList<Component>();
		lastDrawComponent = null;
		
		Collections.shuffle(pool);
	}
	
	public int getPoolSize() {
		return pool.size();
	}
	
	public List<Component> getDiscardedComponents() {
		return discardedComponents;
	}
	
	public String printDiscardedComponents() {
		String s = "";
		for(int i = 0; i < discardedComponents.size(); i++) {
			s += "ID elemento: " + i + "\n";
			s += discardedComponents.get(i)+"\n\n";
		}
		return s;
	}
	
	public Component draw() {
		if(pool.size() == 0) {
			System.out.println("Pool Vuoto");
			return null;
		}
		
		lastDrawComponent = pool.getFirst();
		pool.removeFirst();
		return lastDrawComponent;
	}
	
	public Component getDiscard(int index) {
		lastDrawComponent = discardedComponents.get(index);
		return lastDrawComponent;
	}
	
	public boolean discardDraw() {
		boolean status = discardedComponents.add(lastDrawComponent);
		lastDrawComponent = null;
		return status;
	}

}