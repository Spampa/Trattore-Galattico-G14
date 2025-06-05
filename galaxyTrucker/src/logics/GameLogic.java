package logics;

import entities.*;
import entities.board.Board;
import java.io.IOException;
import java.util.ArrayList;
import logics.phases.*;
import saveload.SaveLoadManager;
import ui.Graphic;

public class GameLogic {
	//game entitites
	private ArrayList<Player> players;
	private GameLevel level;
	private Board board;
	
	private final Phase[] phases = new Phase[4];
	private int phaseIndex;
	private boolean playing;
	
	private SaveLoadManager slm;
	
	private final Graphic graphic;

	public GameLogic(Graphic graphic){
		this.graphic = graphic;
		phases[0] = new InitPhase(this, graphic);
		phases[1] = new BuildPhase(this, graphic);
		phases[2] = new FlyPhase(this, graphic);
		phases[3] = new EndPhase(this, graphic);

		phaseIndex = 0;
		playing = true;
		
		try {
			slm = new SaveLoadManager(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		if(!slm.isVoid()) {
			if(graphic.askBooleanUser("Caricare partita precedente?")) {
				slm.loadGame();
			}
		}
		
		phases[phaseIndex].start();

		while(playing) {
			phases[phaseIndex].update();
			try {
				slm.saveGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
    
    public void setPhaseIndex(int phaseIndex) {
    	this.phaseIndex = phaseIndex;
    }
    
	public int getPhaseIndex() {
		return phaseIndex;
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

    public Graphic getGraphic() {
        return graphic;
    }

}
