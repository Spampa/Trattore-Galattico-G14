package logics.phases;

import java.util.ArrayList;

import entities.Player;
import entities.board.Board;
import logics.GameLogic;
import ui.Graphic;

public class EndPhase extends Phase{
	
	private Board board;

    public EndPhase(GameLogic game, Graphic graphic){
        super(game, graphic);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public void start() {
    	board  = game.getBoard();
        graphic.printAlert("VOLO TERMINATO");
        graphic.printShipsRecap(board.getPlayers());
        graphic.waitForUser("premi per iniziare la suddivisione dei crediti...");
    }

    @Override
    public void update() {
    	ArrayList<Player> playerToPrize = new ArrayList<Player>();
    	int i = 1;
        for(Player p: board.getPlayers()) {
        	p.addCosmicCredit(5-i);
        	graphic.printMessage("complimenti" + p.getName() + "sei arrivato " + i + "otterrati " + (5-1) + " crediti cosmici");
        	
        	 if(p.getShip().getWaresValue() > 0) {
        		 p.addCosmicCredit(p.getShip().getWaresValue());
        		 graphic.printMessage("il valore in crediti cosmici delle tue merci trasportate è "+ p.getShip().getWaresValue() + " bel lavoro!"); 
        	 }
        	
        	 if(p.getShip().getNumLostComponent() > 0) {
        		 p.removeCosmicCredit(p.getShip().getNumLostComponent());
        		 graphic.printMessage("mannaggia ai perso " + p.getShip().getNumLostComponent() + " purtroppo dobbiamo farteli pagare!");	 
        	 }
        	 
        	 if(playerToPrize.isEmpty()) {
        		 playerToPrize.add(p);
        	 }
        	 else if(playerToPrize.getFirst().getShip().getVoidConnectors() > p.getShip().getVoidConnectors()) {
        		 for(int j =  0; j < playerToPrize.size(); j++) {
        			 playerToPrize.remove(j);
        		 }
        		 playerToPrize.addFirst(p);
        	 }
        	 else if(playerToPrize.getFirst().getShip().getVoidConnectors() == p.getShip().getVoidConnectors()) {
        		 playerToPrize.add(p);
        	 }
        	
        	i++;
        	graphic.waitForUser("premi per continuare...");
        }
        
        graphic.printMessage("ora distribuiamo i premi per la nave più bella!");
        String prized = "";
        for(Player p: playerToPrize) {
        	p.addCosmicCredit(2);
        	prized += p.getName() + ", ";
        }
        graphic.printMessage("complimenti le navi migliori sono quelle di " + prized + " riceverete 2 crediti cosmici");
        
    }

    @Override
    public void end() {
        graphic.printAlert("FINE DELLA FASE DI FINE VOLO!");
        Player winner = null;
        for(Player p : board.getPlayers()) {
        	graphic.printMessage(p.getName() + "hai raccimolato ben" +  p.getCosmicCredit() + " crediti cosmici!");
        	
        	if(winner == null || p.getCosmicCredit() > winner.getCosmicCredit()) {
        		winner = p;
        	}
        }
        
        graphic.printAlert("IL VINCITORE DEL VOLO E':" + winner.getName());
        graphic.waitForUser("premi per continuare...");
        
    }
    
}
