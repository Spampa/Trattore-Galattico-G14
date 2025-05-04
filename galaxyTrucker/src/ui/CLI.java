package ui;

import components.Component;
import components.models.*;
import components.models.containers.BatteryStorage;
import components.models.containers.HousingUnit;
import components.models.containers.WareStorage;
import entities.GameLevel;
import entities.Player;
import entities.Position;
import entities.Ship;
import entities.ShipTile;
import java.util.List;
import java.util.Scanner;

public class CLI implements Graphic{
	Scanner sc;
	
	public CLI() {
		sc = new Scanner(System.in);
	}
	
	public void close() {
		sc.close();
	}
	
	//cli private functions
	private void clear() {		
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
		//TODO: final version remove for, is for debug
		System.out.println("\n-----------------------------------------------------\n");
	}
	
	private void printRow() {
		System.out.println("\n-----------------------------------------------------\n");
	}
	
	//global functions
	@Override
	public void printAlert(String message) {
		this.printRow();
		System.out.println("\u001B[33m" + message + "\u001B[0m");
		this.printRow();
	}
	
	@Override
	public void printMessage(String message) {
		this.printRow();
		System.out.println(message);
		this.printRow();
	}
	
	@Override
	public void printShip(Ship s) {
		this.printRow();
		ShipTile[][] board = s.getShipComponets();
		String out = "   ";

		for(int i = 0; i < s.getGameLevel().getBoardX(); i++){
			out += " "+i+" ";
		}
		out += "\n";

		for(int j = 0; j < s.getGameLevel().getBoardY(); j++){
			for(int i = 0; i < s.getGameLevel().getBoardX(); i++){
				if(i == 0){
					out+="\n "+j+" ";
				}
				if(!board[j][i].isIsSpace()) out+=getComponentIcon(board[j][i].getComponent());
				else out+="   ";
			}
		}
		
		System.out.println(out);
		this.printRow();
	}

	@Override
	public String getComponentIcon(Component c) {
		char icon;

		switch (c) {
			case Cannon can -> {
				icon = 'C';
            }
			case Engine en ->{
				icon = 'E';
			}
			case WareStorage con ->{
				icon = 'S';
			}
			case HousingUnit house ->{
				if(house.isCore()) icon = 'N';
				else icon = 'H';
			}
			case BatteryStorage bat ->{
				icon = 'B';
			}
			case null ->{
				icon = 'X';
			}
			default -> icon = '?';
		}

		return (" "+icon+" ");
	}
	
	@Override
	public Position setComponentPosition() {
		Position position = new Position();
		
		System.out.print("Inserire posizione x del componente sulla nave: ");
		position.setX(Integer.parseInt(sc.nextLine()));
				
		System.out.print("Inserire posizione y del componente sulla nave: ");
		position.setY(Integer.parseInt(sc.nextLine()));
		
		return position;
	}
	
	//initialize game functions
	@Override
	public GameLevel setGameLevel() {
		int level;
		do {
			System.out.print("Selezione il livello della Partita (1-3): ");
			
			level = Integer.parseInt(sc.nextLine());
			
			if(level < 1 || level > 3) System.out.println("Sono accettati solo da 1 a 3 giocatori! \n");
			
		}while(level < 1 || level > 3);
		
		this.clear();
		
		switch (level) {
			case 1 ->{
				return GameLevel.I;
			}
			case 2 ->{
				return GameLevel.II;
			}
			case 3 ->{
				return GameLevel.III;
			}
			default ->{
				System.out.println("error");
				return null;
			}
		}
	}

	@Override
	public int setPlayerCount() {
		int playerCount;
		do {
			System.out.print("Inserisci il numero di giocatori: ");
			
			playerCount = Integer.parseInt(sc.nextLine());
			
			if(playerCount < 2 || playerCount > 4) System.out.println("Sono accettati solo da 2 a 4 giocatori! \n");
			
		}while(playerCount < 2 || playerCount > 4);
		this.clear();
		return playerCount;
	}

	@Override
	public Player[] setPlayers(int playerCount, GameLevel level) {
		Player[] players = new Player[playerCount];
		for(int i = 0; i < playerCount; i++) {
			String name;
			System.out.print("Inserisci il nome del giocatore " + (i+1) + ": ");
			name = sc.nextLine();
			players[i] = new Player(name, new Ship(level));
		}
		this.clear();
		return players;
	}

	//build phase functions
	@Override
	public int drawOrPeekComponent(int poolSize) {
		int choice;
		do {
			System.out.println("---- FAI LA TUA MOSSA ----");
    		System.out.println("0 - pescare un nuovo componente");
    		System.out.println("1 - pescare dalla pila degli scarti");
    		
    		System.out.print("\nScelta: ");
    		choice = Integer.parseInt(sc.nextLine());
    		
    		if(choice == 1 && poolSize == 0) {
    			this.printAlert("La pila degli scarti è vuota!\n");
    		}
		}while((choice != 0 && choice != 1) || (choice == 1 && poolSize == 0));
		this.clear();
		return choice;
	}

	@Override
	public void printComponent(Component component) {
		// TODO Auto-generated method stub
		System.out.println("Hai ottenuto:\n");
		System.out.println(component);
		this.printRow();
	}
	
	@Override
	public boolean acceptComponentDraw() {
		String choice;
		do {
			System.out.print("Vuoi mantenere il componente? (0 No, 1 Si): ");
			choice = sc.nextLine();
		}while(!choice.equals("0") && !choice.equals("1"));
		
		return choice.equals("1");
	}


	@Override
	public void printDiscardedComponents(List<Component> discardedComponents) {
		System.out.println("Lista dei componenti: ");
		// TODO Auto-generated method stub
		for(int i = 0; i < discardedComponents.size(); i++) {
			System.out.println("Indice componente: " + i);
			System.out.println(discardedComponents.get(i).toString());
			this.printRow();
		}
	}
	
	@Override
	public int getDiscardComponentIndex(int size) {
		int choice;
		do {
			System.out.println("Inserisci indice del componente da scegliere (valori tra 0 - " + (size - 1) + "):");
			choice = Integer.parseInt(sc.nextLine());
			
			if(choice < 0 || choice >= size) {
				this.printAlert("Valore non valido!");
			}
		}while(choice < 0 || choice >= size);
		
		return choice;
	}
	
	@Override
	public boolean isBuildFinish() {
		String choice;
		do {
			System.out.print("Hai finito si costruire la nave? (0 No, 1 Si): ");
			choice = sc.nextLine();
		}while(!choice.equals("0") && !choice.equals("1"));
		
		this.clear();
		return choice.equals("1");
	}
}
