package titles;

import java.util.ArrayList;

import entities.Player;
import titles.cards.*;
import ui.Graphic;

public class TitlePile {
	private ArrayList<Title> titles;
	private final Graphic graphic;
	public TitlePile(Graphic graphic) {
		this.titles=new ArrayList<Title>();
		this.titles.add(new FreightHauler());
		this.titles.add(new PowerTrucker());
		this.titles.add(new MasterEngineer());
		this.titles.add(new CruiseCaptain());
		this.titles.add(new Xenoquartermaster());
		this.titles.add(new Corridorist());
		this.graphic=graphic;
	}
	
	/*public TitlePile(ArrayList<Title> definitivetitles, Graphic graphic) {
		this.graphic=graphic;
		this.titles=new ArrayList<Title>();
		for(Title t:definitivetitles) {
			this.titles.add(t);
		}
	}*/
	
	

	public void assignAllTitles(Player[] players) {
		for(Title t:titles) {
			int max_i=t.findElibiglePlayer(players);
			try {
				t.assignToPlayer(players, max_i);
			} catch (SpecialAssignmentException e) {
				graphic.printMessage("Titolo "+t.getType().name()+" assegnato in base alla posizione!");
			}
		}
	}
	
	public void getDefinitiveTitles(Player[] players){			//restituisce i titoli che verranno tenuti dai giocatori
		ArrayList<Title> titlesofplayer=new ArrayList<Title>();
		ArrayList<Title> definitivetitles=new ArrayList<Title>();
		for(Player p:players) {
			for(Title t:this.titles) {
				if(t.getTitleHolder()==p) {
					titlesofplayer.add(t);
				}
			}
			
			if(titlesofplayer.size()==1) { 	//se un giocatore ha un unico titolo allora avrà solo quello
				definitivetitles.add(titlesofplayer.getFirst()); 
			}
			
			if(titlesofplayer.size()>1) {	//se giocatore ha più di un titolo, ne può tenere soltanto uno (decidere quale)
				definitivetitles.add(p.chooseYourTitle(titlesofplayer, players, this.titles, this.graphic));
			}
			
			titlesofplayer.clear();   		//svuota la lista 
		}
		
		int count=0;								//blocco aggiuntivo in cui si controlla che effettivamente non ci siano giocatori senza titoli:
		for(int i=0;i<players.length;i++){			//in teoria giocatori con più titoli si possono rifiutare di assegnare gli altri titoli
			for(Title t:this.titles) {				//-->NECESSARIO controllo extra
				if(t.getTitleHolder()==players[i]) {
					count++;						//dopo la prima parte in teoria si arriva al massimo a count=1
				}
			}
			
			if(count==0) {							//caso in cui giocatore non ha titoli--> prendo il primo libero e glielo assegno
				for(Title t:this.titles) {
					if(t.getTitleHolder()==null) {
						t.setTitleHolder(players[i]);
						definitivetitles.add(t);
					}
				}
			}
			count=0;
		}
		
		for(Title t:this.titles) {					//rimuovo dalla pila di titoli quelli non utilizzati
			if(!definitivetitles.contains(t)) {
				titles.remove(t);
			}
		}
		
	}
	/*in end_phase1: 	TitlePile tp=new TitlePile(graphic);
	 * 					tp.assignAllTitles(players);
	 * 					tp.getDefinitiveTitles(players);    //questo set di titoli si userà nelle end-phase2 e 3
	*/		
}

