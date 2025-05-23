package logics;

import java.util.Scanner;

import ui.CLI;
import ui.Graphic;

public class Game {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Graphic graphic = new CLI();
		GameLogic game = new GameLogic(graphic);
		
		game.play();
		sc.close();
	}
}
