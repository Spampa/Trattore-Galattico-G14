package eventCards;


import components.enums.Side;
import entities.*;
import entities.board.Board;
import events.Shoot;
import gameEvents.enums.ProjectileType;
import ui.Graphic;

public class AsteroidSwarm extends Card {
	private final Shoot[] asteroids;
    
    public AsteroidSwarm(Graphic graphic, GameLevel level) {
        super(graphic, "Campo Asteroidi", 
              "2 danni casuali alla nave (1 se motori >= 3", level);
        this.asteroids = getAsteroids();
    }
    
    private Shoot[] getAsteroids() {
    	Shoot[] s = new Shoot[Deck.getRandom(level.toInt(), 3)];
    	
    	for(int i = 0; i < s.length; i++) {
    		s[i] = new Shoot(graphic, Side.intToSide(Deck.getRandom(4, 0)),ProjectileType.intToPType(Deck.getRandom(2, 2)));
    	}
    	return s;
    }

    @Override
    public void execute(Board b) {
        
        for(Player player: b.getPlayers()) {
        	for(Shoot asteroid: asteroids) {
        		asteroid.setPlayer(player);
        		asteroid.start();
        	}
        }
        
        graphic.printAlert("Evento" + super.getName() + " terminato!...");
        graphic.waitForUser("premi per continuare..."); 
    }
}