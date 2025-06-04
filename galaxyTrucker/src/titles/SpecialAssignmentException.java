package titles;

import entities.Player;

public class SpecialAssignmentException extends Exception {	//potenzialmente da togliere (inutile)

	public SpecialAssignmentException(Title title, Player[] players) throws SpecialAssignmentException {
		players[0].addCosmicCredit(-2);
		title.assignToPlayer(players, 0); 			//si presuppone che i players siano ordinati in precedenza (Array.sort(players))
	}
	
}

