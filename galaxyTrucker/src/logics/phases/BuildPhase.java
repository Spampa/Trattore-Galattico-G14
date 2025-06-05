package logics.phases;
import java.util.ArrayList;

import components.Component;
import components.Rotatable;
import entities.ComponentPool;
import entities.Player;
import entities.Position;
import logics.GameLogic;
import ui.Graphic;

public class BuildPhase extends  Phase{
	
	private ComponentPool pool;
	private ArrayList<Player> players;
	private ArrayList<Player> finishedPlayers;

    public BuildPhase(GameLogic game, Graphic graphic){
        super(game, graphic);
        finishedPlayers = new ArrayList<Player>();
    }
    
    @Override
    public void start() {
    	this.pool = new ComponentPool(game.getLevel());
        this.players = game.getPlayers();
        System.out.println("Starting Building Phase");
    }

    @Override
    public void update() {
    	if(arePlayerFinish()) {
    		game.switchPhase();
    		return;
    	}
    	
    	for(Player player : players) {
    		graphic.printMessage("Giocatore \u001B[34m" + player.getName() + "\u001B[0m tocca a te!");
    		int choice = graphic.drawOrPeekComponent(pool.getDiscardedComponents().size());
    		
    		if(choice == 0) { //draw component
    			Component component = pool.draw();
    			graphic.printComponent(component);

    			//insert loop
    			while(!this.insertComponent(player,component));
    		}
    		else {
    			graphic.printDiscardedComponents(pool.getDiscardedComponents());
    			int componentIndex = graphic.getDiscardComponentIndex(pool.getDiscardedComponents().size());
    			
    			Component component = pool.getDiscard(componentIndex);
    			graphic.printComponent(component);
    			
    			//insert loop
    			while(!this.insertComponent(player,component));
    		}
    		
    		//set endTurn
    		graphic.printShip(player.getShip());
    		if(graphic.askUser("Hai finito di costruire la nave?")) {
    			finishedPlayers.add(player);
    		}
    	}
    }

    @Override
    public void end() {
    	game.setPlayers(finishedPlayers);
    	graphic.printMessage("Fine fase di costruzione della nave");
    }
    
    private boolean arePlayerFinish() {
    	return finishedPlayers.size() == players.size();
    }
    
    private boolean insertComponent(Player player, Component component) {
		if(graphic.askUser("Vuoi mantenere il componente?")) {
			this.rotateComponent(component);
			
			graphic.printShip(player.getShip());
			Position position = graphic.askComponentPosition();
			
			boolean isSet = player.getShip().setComponent(component, position);
			if(isSet) {
				graphic.printMessage("\u001B[32mInserimento corretto\u001B[0m");
			}
			else {
				graphic.printAlert("Posizione non valida!");
			}
			return isSet;
		}
		else {
			pool.discardDraw();
			return true;
		}
    }
    
    private void rotateComponent(Component component) {
    	if(!(component instanceof Rotatable)) return;
    	
		//rotate loop
		while(graphic.askUser("Vuoi ruotare il componente?")) {
			((Rotatable) component).rotate();
			graphic.printComponent(component);
		}
    }
}
