package logics;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GameLogic game = new GameLogic();
		
		game.play();
		sc.close();
	}

}
