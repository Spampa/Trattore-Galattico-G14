package logics.phases;

import logics.GameLogic;
import ui.Graphic;

public abstract class Phase {
    protected final GameLogic game;
    protected final Graphic graphic;
    
    public Phase(GameLogic game, Graphic graphic){
        this.game = game;
        this.graphic = graphic;
    }

    //funzione richiamate nel loop di gioco ad ogni iterazione
    public abstract  void start();
    
    public abstract void update(); 
    
    public abstract void end();

}
