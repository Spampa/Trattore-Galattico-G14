package randomizer;

import java.util.Random;
import components.*;
import components.enums.MountType;
import components.enums.Side;
import components.types.*;
import components.types.containers.*;
import entities.ComponentPool;

public class ComponentRandomizer {
	
	public static double[] SPAWN_RATIO = elaborateSpawnRatio();
	
	public static Component getRandomComponent() {
		Random r = new Random();
		Component c;
		
		double extracted = r.nextDouble(100);
		
		if(extracted < SPAWN_RATIO[0]) {
			c = new Pipe(ConnectorsRandomizer.getRandomConnectors());
		}
		else if(extracted < SPAWN_RATIO[1]) {
			c = new Cannon(MountType.SINGLE, ConnectorsRandomizer.getRandomConnectors(Side.UP));
		}
		else if(extracted < SPAWN_RATIO[2]) {
			c = new Cannon(MountType.DOUBLE, ConnectorsRandomizer.getRandomConnectors(Side.UP));
		}
		else if(extracted < SPAWN_RATIO[3]){
			c = new Engine(MountType.SINGLE, ConnectorsRandomizer.getRandomConnectors(Side.DOWN));
		}
		else if(extracted < SPAWN_RATIO[4]){
			c = new Engine(MountType.DOUBLE, ConnectorsRandomizer.getRandomConnectors(Side.DOWN));
		}
		else if(extracted < SPAWN_RATIO[5]) {
			c = new HousingUnit(ConnectorsRandomizer.getRandomConnectors());
		}
		else if(extracted < SPAWN_RATIO[6]) {
			c = new NormalStorage(ContainerSize.BIG, ConnectorsRandomizer.getRandomConnectors());
		}
		else if(extracted < SPAWN_RATIO[7]) {
			c = new NormalStorage(ContainerSize.SMALL, ConnectorsRandomizer.getRandomConnectors());
		}
		else if(extracted < SPAWN_RATIO[8]) {
			c = new SpecialStorage(ContainerSize.BIG, ConnectorsRandomizer.getRandomConnectors());
		}
		else if(extracted < SPAWN_RATIO[9]) {
			c = new SpecialStorage(ContainerSize.SMALL, ConnectorsRandomizer.getRandomConnectors());
		}
		else if(extracted < SPAWN_RATIO[10]) {
			c = new BatteryContainer(ContainerSize.SMALL, ConnectorsRandomizer.getRandomConnectors());
		}
		else {
			c = new BatteryContainer(ContainerSize.BIG, ConnectorsRandomizer.getRandomConnectors());
		}
		
		return c;
	}
	
	public static void main(String args[]) {
		Component c = ComponentRandomizer.getRandomComponent();
		System.out.println(c + "\n");
		
		
		c = ComponentRandomizer.getRandomComponent();
		System.out.println(c);
	}
	
	private static double[] elaborateSpawnRatio() {
		double[] ratio = new double[ComponentPool.NUM_OF_COMPONENTS.length];
		double s = 0;
		for(int i = 0; i < ComponentPool.NUM_OF_COMPONENTS.length; i++) {
			s += (ComponentPool.NUM_OF_COMPONENTS[i] * 100) / ComponentPool.MAX_DRAW;
			ratio[i] = s;
		}
		return ratio;
	}
}
