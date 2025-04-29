package logics;

import java.util.Scanner;

import entities.GameLevel;

public class GameExe {

	public static void main(String[] args) {
		Player[] p;
		Scanner sc = new Scanner(System.in);
		int pNum;
		int gameLevel;
		GameLogic game;
		
		System.out.println("Benvenuto! \n");
		do {
			System.out.println("Inserisci il numero di giocatori:");
			
			pNum = Integer.parseInt(sc.nextLine());
			
			if(pNum < 2 || pNum > 4) System.out.println("Sono accettati solo da 2 a 4 giocatori! \n");
			
		}while(pNum < 2 || pNum > 4);
		
		
		
		p = new Player[pNum];
		
		for(int i = 0; i < pNum; i++) {
			p = new Player();
		}
		
		do {
			System.out.println("Selezione il livello della Partita (1-3):");
			
			gameLevel= Integer.parseInt(sc.nextLine());
			
			if(gameLevel < 1 || gameLevel > 3) System.out.println("Sono accettati solo da 1 a 3 giocatori! \n");
			
		}while(gameLevel < 1 || gameLevel > 3);
		
		GameLevel level;
		
		switch (gameLevel) {
			case 1 ->{
				level = GameLevel.I;
			}
			case 2 ->{
				level = GameLevel.II;
			}
			case 3 ->{
				level = GameLevel.III;
			}
		}
		
		game = new GameLogic(p, level);
		
		game.play();
	}

}
