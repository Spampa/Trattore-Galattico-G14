package entities;

import java.util.Random;

public class Dices {
	private static int firstDice;
	private static int secondDice;
	
	public int roll() {
		Random random = new Random();
		firstDice = random.nextInt(6) + 1;
		secondDice = random.nextInt(6) + 1;
		
		return firstDice + secondDice;
	}
	
	public static int getFirstDice() {
		return firstDice;
	}

	public static int getSecondDice() {
		return secondDice;
	}

	
}
