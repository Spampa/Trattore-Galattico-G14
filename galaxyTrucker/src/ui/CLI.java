package ui;

import components.Component;
import components.enums.Side;
import components.models.*;
import components.models.containers.BatteryStorage;
import components.models.containers.HousingUnit;
import components.models.containers.WareStorage;
import entities.GameLevel;
import entities.Player;
import entities.Position;
import entities.Ship;
import entities.ShipTile;
import eventCards.EventCard;
import flightBoard.Board;
import flightBoard.Space;

import java.util.ArrayList;
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
		String out = s.toString() + "\n     ";

		for(int i = 0; i < s.getGameLevel().getBoardX(); i++){
			out += "  "+i+"  ";
		}
		out += "\n";

		for(int y = 0; y < s.getGameLevel().getBoardY(); y++){
			for(int sideIndex = 0; sideIndex < 3; sideIndex++) {
				
				if(sideIndex == 1) {
					out+="\n  "+y+"  ";
				}
				else {
					out+="\n     ";
				}
				
				for(int x = 0; x < s.getGameLevel().getBoardX(); x++){
					if(board[y][x].isIsSpace()){
						out += "     ";
					}
					else if(board[y][x].getComponent() == null) {
						if(sideIndex == 0) {
							out += this.printSingleSymbol("+");
						}
						else if(sideIndex == 1) {
							out += printTripleLayer("+", "X", "+");
						}
						else {
							out += this.printSingleSymbol("+");
						}
					}
					else {
						Component component = board[y][x].getComponent();
						if(sideIndex == 0) {
							out += this.printSingleSymbol(Integer.toString(component.getConnector(Side.UP).getNumber()));
						}
						else if(sideIndex == 1) {
							out += printTripleLayer(
									Integer.toString(component.getConnector(Side.LEFT).getNumber()),
									this.getComponentIcon(component),
									Integer.toString( component.getConnector(Side.RIGHT).getNumber())
								);
						}
						else {
							out += this.printSingleSymbol(Integer.toString(component.getConnector(Side.DOWN).getNumber()));
						}
					}
					
				}
			}
		}
		
		System.out.println(out);
		this.printRow();
	}
	
	//print ship private function
	private String printSingleSymbol(String symbol) {
		return "+ " + symbol + " +";
	}
	
	private String printTripleLayer(String leftSymbol, String centerSymbol, String rightSymbol) {
		return leftSymbol + " " + centerSymbol + " " + rightSymbol;
	}

	@Override
	public String getComponentIcon(Component c) {
		String icon;

		switch (c) {
			case Cannon can -> {
				icon = "C";
            }
			case Engine en ->{
				icon = "E";
			}
			case WareStorage con ->{
				icon = "S";
			}
			case HousingUnit house ->{
				if(house.isCore()) icon = "N";
				else icon = "H";
			}
			case BatteryStorage bat ->{
				icon = "B";
			}
			
			case Shield shield -> {
				icon = "O";
			}
			case null ->{
				icon = "X";
			}
			default -> icon = "?";
		}

		return icon;
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
	public ArrayList<Player> setPlayers(int playerCount, GameLevel level) {
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < playerCount; i++) {
			String name;
			System.out.print("Inserisci il nome del giocatore " + (i+1) + ": ");
			name = sc.nextLine();
			players.add(new Player(name, new Ship(level)));
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
    			this.printAlert("La pila degli scarti è vuota!");
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
    public void printCard(EventCard c) {
        this.printRow();
		System.out.println("CARTA EVENTO PESCATA: \n");
		System.out.println("Nome: \u001B[33m"+ c.getName() + "\u001B[0m \n");
		System.out.println("Descrizione: " + c.getDescription()+ "\n");
		this.waitForUser("premere un tasto per iniziare l'evento...");
    }

	@Override
	public void waitForUser(String message) {
		this.printRow();
		System.out.println(message);
		sc.nextLine();
	}

	@Override
	public void printShipsRecap(ArrayList<Player> players) {
        System.out.println("Recap delle navi in volo...");
		this.printRow();
        for(Player p : players){
            System.out.println(p.getPlayerName() + " la tua nave è:");
            this.printShip(p.getPlayerShip());
        }
        this.waitForUser("premere un tasto per continuare...");
	}

	@Override
	public boolean askUser(String message) {
		int answer;
		this.printRow();
		do { 
			System.out.print(message + "(NO: 0, SI: 1): ");
			answer = Integer.parseInt(sc.nextLine());
		} while (answer < 0 && answer > 1);

		return answer == 1;
	}
	
	//TODO: optimize
	public void printBoard(Board board) {
		int rowSize = board.getNumberOfSpaces() / 2 - 2;
		
		
		for(int i = board.getNumberOfSpaces() - rowSize / 2; i <= board.getNumberOfSpaces() + rowSize / 2; i++) {
			Space space = board.getSpace(i % board.getNumberOfSpaces());
			if(space.getPlayer() == null) {
				System.out.print(" _ ");
			}
			else {
				System.out.print(" ♟️ ");
				//System.out.print(" ♟️ " + space.getPlayer().getPlayerName() + " ");
			}
		}
		
		for(int j = 1; j <= 2; j++) {
			System.out.println();
			Space space = board.getSpace(board.getNumberOfSpaces() - rowSize / 2 - j);
			if(space.getPlayer() == null) {
				System.out.print(" _ ");
			}
			else {
				System.out.print(" ♟️ ");
				//System.out.print(" ♟️ " + space.getPlayer().getPlayerName() + " ");
			}
			
			for(int i = 0; i < rowSize - 2; i++) {
				System.out.print("   ");
			}
			
			space = board.getSpace(rowSize / 2 + j);
			if(space.getPlayer() == null) {
				System.out.print(" _ ");
			}
			else {
				System.out.print(" ♟️ ");
				//System.out.print(" ♟️ " + space.getPlayer().getPlayerName() + " ");
			}
			System.out.println();
		}
		
		
		for(int i = board.getNumberOfSpaces() - rowSize / 2 - 3; i >= rowSize / 2  + 3; i--) {
			Space space = board.getSpace(i % board.getNumberOfSpaces());
			if(space.getPlayer() == null) {
				System.out.print(" _ ");
			}
			else {
				System.out.print(" ♟️ ");
				//System.out.print(" ♟️ " + space.getPlayer().getPlayerName() + " ");
			}
		}

		this.printRow();
	}
}
