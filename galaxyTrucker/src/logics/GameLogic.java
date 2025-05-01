package logics;

import entities.*;
import logics.phases.BuildPhase;
import logics.phases.EndPhase;
import logics.phases.FlyPhase;
import logics.phases.InitPhase;
import logics.phases.Phase;

public class GameLogic {
	private Player[] players;
	private GameLevel level;
	private final Phase[] phases = new Phase[4];
	private int phaseIndex;
	private boolean playing;

	public GameLogic(){
		phases[0] = new InitPhase(this);
		phases[1] = new BuildPhase(this);
		phases[2] = new FlyPhase(this);
		phases[3] = new EndPhase(this);


		phaseIndex = 0;
		playing = true;
	}
	
	public void  play() {
		//TODO inizializzazione partita, montaggio nave, svolgimento partita, calcolo punteggi
		
		phases[phaseIndex].start();

		while(playing) {
			phases[phaseIndex].update();
		}
		
	}

	public void switchPhase(){
		phases[phaseIndex].end();
		phaseIndex = (phaseIndex+1)%phases.length;
		phases[phaseIndex].start();
	}

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setLevel(GameLevel level) {
        this.level = level;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public Player[] getPlayers() {
        return players;
    }

    public GameLevel getLevel() {
        return level;
    }

}
