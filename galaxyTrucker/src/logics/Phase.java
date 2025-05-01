package logics;

public abstract class Phase {
    protected  final GameLogic game;
    
    public Phase(GameLogic game){
        this.game = game;
    }

    //funzione richiamate nel loop di gioco ad ogni iterazione
    public abstract void update(); 

    public abstract  void start();

    public abstract void end();

}
