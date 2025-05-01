package logics.phases;

import logics.GameLogic;
import ui.CLI;

public abstract class Phase {
    protected final GameLogic game;
    protected final CLI cli;
    
    public Phase(GameLogic game){
        this.game = game;
        cli = new CLI();
    }

    //funzione richiamate nel loop di gioco ad ogni iterazione
    public abstract  void start();
    
    public abstract void update(); 
    
    public abstract void end();

}
