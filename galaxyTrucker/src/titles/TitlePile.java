package titles;

import java.util.ArrayList;

import entities.Player;
import titles.cards.*;

public class TitlePile {
	private ArrayList<Title> titles;
	public TitlePile() {
		this.titles=new ArrayList<Title>();
		this.titles.add(new TitleFreightHauler());
		this.titles.add(new TitlePowerTrucker());
		this.titles.add(new TitleMasterEngineer());
		this.titles.add(new TitleCruiseCaptain());
		this.titles.add(new TitleXenoquartermaster());
		this.titles.add(new TitleCorridorist());
	}
	
	public TitlePile(ArrayList<Title> definitivetitles) {
		this.titles=new ArrayList<Title>();
		for(Title t:definitivetitles) {
			this.titles.add(t);
		}
	}
	

	public void assignAllTitles(Player[] players) {
		for(Title t:titles) {
			int max_i=t.findElibiglePlayer(players);
			try {
				t.assignToPlayer(players, max_i);
			} catch (SpecialAssignmentException e) {
				System.out.println("Titolo "+t.getType().name()+" assegnato in base alla posizione!" );
			}
		}
	}
	
	public ArrayList<Title> getDefinitiveTitles(Player[] players){			//restituisce i titoli che verranno tenuti dai giocatori
		ArrayList<Title> titlesofplayer=new ArrayList<Title>();
		ArrayList<Title> definitivetitles=new ArrayList<Title>();
		for(Player p:players) {
			for(Title t:this.titles) {
				if(t.getTitleHolder()==p) {
					titlesofplayer.add(t);
				}
			}
			
			if(titlesofplayer.size()>1) {	//se giocatore ha più di un titolo, ne può tenere soltanto uno (decidere quale)
				definitivetitles.add(p.chooseYourTitle(titlesofplayer, players, this.titles));
			}
			
			titlesofplayer.clear();   		//svuota la lista 
		}
		return definitivetitles;			
		
	}
	/*in end_phase1: 	TitlePile tp=new TitlePile();
	 * 					tp.assignAllTitles(players);
	 * 					TitlePile tpdefinitive=TitlePile(tp.getDefinitiveTitles);    //questo set di titoli si userà nelle end-phase2 e 3
	*/		
}

