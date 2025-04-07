package entities;
import components.*;
import randomizer.ComponentRandomizer;

public class ComponentPool {

	public static final int[] NUM_OF_COMPONENTS = {8, 25, 11, 21, 9, 17, 6, 9, 3, 6, 6, 11 }; //mancano i componenti non fatti
	public static final int MAX_DRAW = elaborateMaxDraw();
	
	private static Component[] discardedComponents;
	private static int drawCount;
	private static Component lastDrawComponent;
	
	public ComponentPool() {
		drawCount = 0;
		discardedComponents = new Component[MAX_DRAW];
		lastDrawComponent = null;
	}
	
	public int getDrawCount() {
		return drawCount;
	}
	
	public Component[] getDiscardedComponents() {
		return discardedComponents;
	}
	
	private static int elaborateMaxDraw() {
		int s = 0;
		for(int i = 0; i < NUM_OF_COMPONENTS.length; i++) {
			s += NUM_OF_COMPONENTS[i];
		}
		return s;
	}
	
	public Component draw() {
		if(drawCount >= MAX_DRAW) {
			System.out.println("Pool Vuoto");
			return null;
		}
		lastDrawComponent = ComponentRandomizer.getRandomComponent();
		drawCount++;
		return lastDrawComponent;
	}
	
	public void discardDraw() {
		if(lastDrawComponent == null) {
			return;
		}

		discardedComponents[drawCount - 1] = lastDrawComponent;
	}
	
	public static void main(String[] args) {
		ComponentPool pool = new ComponentPool();
		for(int i = 0; i < 5; i++) {
			pool.draw();
			pool.discardDraw();
		}
		
		Component[] discard = pool.getDiscardedComponents();
		for(int i = 0; i < discard.length; i++) {
			if(discard[i] != null) {
				System.out.println(discard[i] + "\n");
			}
		}
	}
}