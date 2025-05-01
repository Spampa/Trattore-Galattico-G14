package logics.phases;
import java.util.Scanner;

import components.Component;
import entities.ComponentPool;
import entities.Player;
import logics.GameLogic;

public class BuildPhase extends  Phase{
	
	private final ComponentPool pool;
	private Player players[];
	private int position;

    public BuildPhase(GameLogic game) {
        super(game);
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
    	
    	Scanner sc = new Scanner(System.in);
    	
    	for(Player player : players) {
    		boolean endTurn = false;
    		int choice;
    		
    		System.out.println(player.getPlayerName() + " tocca a te!");
    		
    		do {
        		System.out.println("Premi 0 per pescare, Premi 1 per guardare gli scarti");
        		
        		choice = Integer.parseInt(sc.nextLine());
    		}while(choice != 0 && choice != 1);
    		
    		
    		if(choice == 0) { //draw component
    			Component component = pool.draw();
    			System.out.println("Hai ottenuto: \n" + component);
    			
    			do {
    				System.out.println("Vuoi scartare il componente? (1 Si, 0 No)");
    				
    				choice = Integer.parseInt(sc.nextLine());
    				
    				if(choice == 1) {
    					pool.discardDraw();
    					endTurn = true;
    				}
    				else {
    					int x, y;
    					System.out.println("Inserire posizione x del componente sulla nave: ");
    					x = Integer.parseInt(sc.nextLine());
    					
    					System.out.println("Inserire posizione y del componente sulla nave: ");
    					y = Integer.parseInt(sc.nextLine());
    					
    					if(player.getPlayerShip().setComponet(component, x, y)) {
    						endTurn = true;
    					}
    				}
    			}while(!endTurn);
    		}
    		else {
    			//TODO: select component from pool
    		}
    		
    		
    	}
    }

    @Override
    public void end() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'end'");
    }
    
    private boolean arePlayerFinish() {
    	
    	/*FIXME: pawn bug
    	for(Player player : players) {
    		if(player.getPawn().getPosition() == 0) {
    			return false;
    		}
    	}
    	*/
    	return false;
    }
    
}
