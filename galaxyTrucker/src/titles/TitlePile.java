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
}

