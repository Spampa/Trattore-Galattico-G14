package logics.phases;

import logics.GameLogic;
import ui.Graphic;

public class FlyPhase extends Phase{

    public FlyPhase(GameLogic game, Graphic graphic){
        super(game, graphic);
    }
    
    @Override
    public void start() {
    	graphic.printMessage("Inizio fase di volo!");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void end() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'end'");
    }
    
}
