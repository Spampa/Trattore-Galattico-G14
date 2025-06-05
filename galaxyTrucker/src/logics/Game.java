package logics;

import java.util.Scanner;

import entities.GameLevel;
import ui.askBoolUser;
import ui.Graphic;

public class Game {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Graphic graphic = new askBoolUser();
		GameLogic game = new GameLogic(graphic);
		
		game.play();
		sc.close();
	}
}
