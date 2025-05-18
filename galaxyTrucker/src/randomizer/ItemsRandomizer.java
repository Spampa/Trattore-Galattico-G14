package randomizer;

import java.util.Random;

import items.Ware;
import items.enums.WareType;

public class ItemsRandomizer {
	private Random random;
	
	public ItemsRandomizer() {
		random = new Random();
	}
	
	public Ware[] getRandomWares(int min, int max) {
		int numOfItems = random.nextInt(max - min + 1) + min;
		
		WareType[] values = WareType.values();
		Ware[] generatedWares = new Ware[numOfItems];
		
		for(int i = 0; i < numOfItems; i++) {
			WareType type = values[random.nextInt(values.length)];
			generatedWares[i] = new Ware(type);
		}
		
		return generatedWares;
	}
}
