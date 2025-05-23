package entities;

import java.util.ArrayList;

import entities.ship.Ship;
import titles.Title;
import ui.Graphic;

public class Player implements Comparable<Player> {

    private final Ship ship;
    private final String name;
	private int moves;

    public Player(String name, Ship ship) {
        this.ship = ship;
        this.name = name;
        this.moves = 0;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean checkPlayer() {
        return ship.isPlayable();
    }
    
    public void setMoves(int moves) {
    	this.moves = moves;
    }

    public void incrementMoves(int moves) {
		this.moves += moves;						//used in board when moving forward	
	}
	
	public int getMoves() {
		return this.moves;
	}

    public String getName() {
        return name;
    }

	@Override
	public int compareTo(Player p) {
		if(this.moves > p.moves) return -1;
		if(this.moves < p.moves) return 1;
		return 0;
	}
	
	@Override
	public String toString() {
		return name + ", moves: " + moves;
	}
	
	//metodo per scelta titolo (un player può avere SOLO un titolo, mentre gli altri: o dati ad altri senza, o scartati) 
	//POTREBBE DOVER ESSERE SPOSTATO ALTROVE (es dove si usa lo scanner della GameLogic)
	public Title chooseYourTitle(ArrayList<Title> titlesofplayer, Player[] players, ArrayList<Title> titles, Graphic graphic) {	
		//Scanner sc=new Scanner(System.in);
		int i_chosen;
		Title chosen=null;
		int[] plyrs=null;
		//System.out.println(this.name+" al momento possiedi "+titlesofplayer.size()+" titoli:");
		graphic.printMessage(this.name+" al momento possiedi "+titlesofplayer.size()+" titoli:");
		for(Title t:titlesofplayer) {
			//System.out.println(titlesofplayer.indexOf(t)+": "+t.getType());
			graphic.printMessage(titlesofplayer.indexOf(t)+": "+t.getType());
		}
		//System.out.println("Quale desideri tenere? Premi il numero corrispondente");
		i_chosen=graphic.askIntUser("Quale desideri tenere? Premi il numero corrispondente", 0, titlesofplayer.size());
		/*do {
			i_chosen=sc.nextInt();
			if(i_chosen>titlesofplayer.size()||i_chosen<0)
				System.out.println("Numero non valido. Riprova");
		} while(i_chosen>titlesofplayer.size()||i_chosen<0);*/
		chosen=titlesofplayer.get(i_chosen);
		titlesofplayer.remove(chosen);
			
		while(titlesofplayer.size()>0) {
			for(Title t:titlesofplayer) {
				//System.out.println("Cosa vuoi fare con questo titolo? "+t.getType());
				graphic.printMessage("Cosa vuoi fare con questo titolo? "+t.getType());
				for(Title q:titles) {
					for(int i=0;i<players.length;i++) {
						if(q.getTitleHolder()==players[i]) {
							plyrs[i]++;
						}
					}
				}
				boolean a=false;
				for(int i=0;i<players.length;i++) {
					plyrs=new int[players.length];
					if(plyrs[i]==0) {
						//System.out.println("Dare a giocatore "+players[i].name+ " visto che non ha titoli? 0:no, 1:sì");
						a=graphic.askUser("Dare a giocatore "+players[i].name+ " visto che non ha titoli?");
						/*do {
							a=sc.nextInt();
							if(a<0||a>1)
								System.out.println("Numero non valido. Riprova");
						} while(a<0||a>1);*/
					}
					if(a) {
						//System.out.println("Hai assegnato il titolo al giocatore "+players[i].name);
						graphic.printMessage("Hai assegnato il titolo al giocatore "+players[i].name);
						t.setTitleHolder(players[i]);
						break;
					}	
				}
				if(!a) {
					//System.out.println("Titolo non assegnabile a nessuno, quindi viene scartato");
					graphic.printMessage("Titolo non assegnabile a nessuno, quindi viene scartato");
					t.setTitleHolder(null);
				}
				titlesofplayer.remove(t);				
			}
		}
		//sc.close();
		return chosen;
	}
}


