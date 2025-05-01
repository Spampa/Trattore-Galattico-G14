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
    	
    	for(Player player : players) {
    		cli.printMessage("Giocatore \u001B[34m" + player.getPlayerName() + "\u001B[0m tocca a te! \n");
    		
    		boolean endTurn = false;
    		int choice = cli.drawOrPeekComponent(pool.getDiscardedComponents().size());
    		cli.clear();
    		
    		if(choice == 0) { //draw component
    			Component component = pool.draw();
    			cli.printComponent(component);
    			cli.printRow();
    			
    			do {
    				if(cli.acceptComponentDraw()) {
    					endTurn = cli.insertComponent(player, component);
    					if(!endTurn) {
    						cli.clear();
    						cli.printAlert("Posizione non valida!");
    					}
    				}
    				else {
    	    			pool.discardDraw();
    					endTurn = true;
    				}
    			} while(!endTurn);
    			
    			cli.printRow();
    			cli.printMessage("\u001B[32mInserimento corretto\u001B[0m");
    			cli.clear();
    		}
    		else {
    			/*
    			//TODO: select component from pool
    			System.out.println("Scegli uno dei componenti nella pila degli scarti:\n");
    			System.out.println(pool.printDiscardedComponents());
    			
    			do {
        			System.out.println("Inserisci id del componente per selezionarlo: ");
        			choice = Integer.parseInt(sc.nextLine());
        			
        			if(choice < 0 || choice >= pool.printDiscardedComponents().length()) {
        				System.out.println("\nId inserito non valido!\n");
        			}
    			}while(choice < 0 || choice >= pool.printDiscardedComponents().length());

    			Component component = pool.getDiscard(choice);*/
    			
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
