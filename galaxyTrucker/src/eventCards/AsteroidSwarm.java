package eventCards;

import java.util.Random;

import components.enums.Side;
import entities.*;
import entities.board.Board;
import events.Shoot;
import gameEvents.enums.ProjectileType;
import ui.Graphic;

public class AsteroidSwarm extends Card {
	private final Shoot[] shoots;
    
    public AsteroidSwarm(Graphic graphic) {
        super(graphic, "Campo Asteroidi", 
              "2 danni casuali alla nave (1 se motori >= 3");
        this.shoots = getShoots();
    }
    
    private Shoot[] getShoots() {
    	Random r = new Random();
    	Shoot[] s = new Shoot[r.nextInt(4)+1];
    	
    	for(int i = 0; i < s.length; i++) {
    		s[i] = new Shoot(graphic, Side.intToSide(r.nextInt(4)),ProjectileType.intToPType(r.nextInt(4)));
    	}
    	return s;
    }

    @Override
    public void execute(Board b) {
        
        for(Player player: b.getPlayers()) {
        	for(Shoot shoot: shoots) {
        		shoot.setPlayer(player);
        		shoot.start();
        	}
        }
    }
}