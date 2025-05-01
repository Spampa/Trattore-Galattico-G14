package logics;

import entities.GameLevel;
import entities.Ship;
import java.util.Scanner;

public class InitPhase extends  Phase {
    private Player[] players;
	private GameLevel level;

    public InitPhase(GameLogic game){
        super(game);
    }

	@Override
    public void start() {	
		System.out.println("Benvenuto! \n");
    }

    @Override
    public void update(){
        Scanner sc = new Scanner(System.in);
		int pNum;
    	int gameLevel;

		do {
			System.out.println("Selezione il livello della Partita (1-3):");
			
			gameLevel= Integer.parseInt(sc.nextLine());
			
			if(gameLevel < 1 || gameLevel > 3) System.out.println("Sono accettati solo da 1 a 3 giocatori! \n");
			
		}while(gameLevel < 1 || gameLevel > 3);
		
		
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
			default ->{
				level = null;
				System.out.println("error");
			}
		}



		do {
			System.out.println("Inserisci il numero di giocatori:");
			
			pNum = Integer.parseInt(sc.nextLine());
			
			if(pNum < 2 || pNum > 4) System.out.println("Sono accettati solo da 2 a 4 giocatori! \n");
			
		}while(pNum < 2 || pNum > 4);
		
		
		
		players = new Player[pNum];
		
		for(int i = 0; i < pNum; i++) {
			String name;
			System.out.println("Inserisci il nome del giocatore " + (i+1) + "\n");
			name = sc.nextLine();
			players[i] = new Player(name, new Ship(level));
		}
		
		sc.close();

		game.switchPhase();
    }

    @Override
    public void end() {
		game.setPlayers(players);
		game.setLevel(level);

		System.out.println("fine fase di inizializazione \n");
		System.out.println("players:" + players.length + " " + "livello partita:" + level + "\n");
    }

}
