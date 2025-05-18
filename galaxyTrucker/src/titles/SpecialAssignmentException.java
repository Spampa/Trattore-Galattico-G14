package titles;

import entities.Player;

public class SpecialAssignmentException extends Exception {

	public SpecialAssignmentException(Title title, Player[] players) throws SpecialAssignmentException {
			/*for(int i=0; i<players.length; i++) {
				if(players[i].getPosition()==1) {
					//TODO player.incrementCosmicCredits(-2);
					title.assignToPlayer(players, i);
					break;
			}
		}*/
		title.assignToPlayer(players, 0); 			//si presuppone che i players siano ordinati in precedenza (Array.sort(players))
	}
	
}

