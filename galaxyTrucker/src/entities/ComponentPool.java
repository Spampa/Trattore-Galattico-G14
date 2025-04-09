package entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import components.*;
import randomizer.ComponentRandomizer;

public class ComponentPool {
	
	private static List<Component> discardedComponents;
	private static List<Component> pool;
	
	private Component lastDrawComponent;
	
	public ComponentPool() {
		ComponentRandomizer cr = new ComponentRandomizer();
		
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
	
	public Component draw() {
		if(pool.size() == 0) {
			System.out.println("Pool Vuoto");
			return null;
		}
		
		lastDrawComponent = pool.getFirst();
		pool.removeFirst();
		return lastDrawComponent;
	}
	
	public boolean discardDraw() {
		boolean status = discardedComponents.add(lastDrawComponent);
		lastDrawComponent = null;
		return status;
	}
	
	public static void main(String[] args) {
		ComponentPool p = new ComponentPool();
		
		System.out.println(p.draw());
		p.discardDraw();
		System.out.println(p.getDiscardedComponents());
	}
}