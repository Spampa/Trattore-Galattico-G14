package logics.phases;
import components.Component;
import entities.ComponentPool;
import entities.Player;
import entities.Position;
import logics.GameLogic;
import ui.Graphic;

public class BuildPhase extends  Phase{
	
	private final ComponentPool pool;
	private Player players[];
	private int position;

    public BuildPhase(GameLogic game, Graphic graphic){
        super(game, graphic);
        this.pool = new ComponentPool();
        this.position = 0;
    }
    
    @Override
    public void start() {
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
    		graphic.printMessage("Giocatore \u001B[34m" + player.getPlayerName() + "\u001B[0m tocca a te!");
    		int choice = graphic.drawOrPeekComponent(pool.getDiscardedComponents().size());
    		
    		if(choice == 0) { //draw component
    			Component component = pool.draw();
    			graphic.printComponent(component);
    			

    			while(!this.insertComponent(player,component));
    		}
    		else {
    			graphic.printDiscardedComponents(pool.getDiscardedComponents());
    			int componentIndex = graphic.getDiscardComponentIndex(pool.getDiscardedComponents().size());
    			
    			Component component = pool.getDiscard(componentIndex);
    			graphic.printComponent(component);
    			
    			while(!this.insertComponent(player,component));
    		}
    		
    		//set endTurn
    		graphic.printShip(player.getPlayerShip());
    		if(graphic.isBuildFinish()) {
    			position++;
    			player.setPosition(position);
    		}
    	}
    }

    @Override
    public void end() {
    	graphic.printMessage("Fine fase di costruzione della nave");
    }
    
    private boolean arePlayerFinish() {
    	for(Player player : players) {
    		if(player.getPosition() == 0) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean insertComponent(Player player, Component component) {
		if(graphic.acceptComponentDraw()) {
			graphic.printShip(player.getPlayerShip());
			Position position = graphic.setComponentPosition();
			
			boolean isSet = player.getPlayerShip().setComponet(component, position);
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
}
