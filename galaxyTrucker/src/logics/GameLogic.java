package logics;

import java.util.ArrayList;

import entities.*;
import entities.board.Board;
import logics.phases.*;
import ui.Graphic;

public class GameLogic {
	//game entitites
	private ArrayList<Player> players;
	private GameLevel level;
	private Board board;
	
	private final Phase[] phases = new Phase[4];
	private int phaseIndex;
	private boolean playing;

	public GameLogic(Graphic graphic){
		phases[0] = new InitPhase(this, graphic);
		phases[1] = new BuildPhase(this, graphic);
		phases[2] = new FlyPhase(this, graphic);
		phases[3] = new EndPhase(this, graphic);

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

    public void setPlayers(ArrayList<Player> players) {
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GameLevel getLevel() {
        return level;
    }

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
