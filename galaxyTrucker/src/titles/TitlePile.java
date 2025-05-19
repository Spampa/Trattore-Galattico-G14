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
		this.titles.add(new TitleFreightHauler());
		this.titles.add(new TitlePowerTrucker());
		this.titles.add(new TitleMasterEngineer());
		this.titles.add(new TitleCruiseCaptain());
		this.titles.add(new TitleXenoquartermaster());
		this.titles.add(new TitleCorridorist());
		this.graphic=graphic;
	}
	
	public TitlePile(ArrayList<Title> definitivetitles, Graphic graphic) {
		this.graphic=graphic;
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
				//System.out.println("Titolo "+t.getType().name()+" assegnato in base alla posizione!" );
				graphic.printMessage("Titolo "+t.getType().name()+" assegnato in base alla posizione!");
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
				definitivetitles.add(p.chooseYourTitle(titlesofplayer, players, this.titles, this.graphic));
			}
			
			titlesofplayer.clear();   		//svuota la lista 
		}
		return definitivetitles;			//arraylist con i titoli di ciascun giocatore (ciascuno ne ha uno ed uno solo)
		
	}
	/*in end_phase1: 	TitlePile tp=new TitlePile(graphic);
	 * 					tp.assignAllTitles(players);
	 * 					TitlePile tpdefinitive=TitlePile(tp.getDefinitiveTitles(players), graphic);    //questo set di titoli si userà nelle end-phase2 e 3
	*/		
}

