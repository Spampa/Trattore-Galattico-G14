package eventCards;

import entities.GameLevel;
import entities.Player;
import entities.board.Board;
import ui.Graphic;

public abstract class Card {
    protected final Graphic graphic;
    protected final String name;
    protected final String description;
    protected final int flyDays;

	protected final GameLevel level;
    
	//usato se il level mi serve per generare informazioni (ricompense o penalità) e non fa perdere giorni di volo fissi (es AsteroidSwarm)
    public Card(Graphic graphic, String name, String description, GameLevel level) {
    	this.graphic = graphic;
        this.name = name;
        this.description = description;
        this.level = level;
        this.flyDays = 0;
    }
    
    //usato se la carta non genera informazione casuali e non fa perdere giorni di volo fissi (OpenSpace, Sabotage, Stardust)
    public Card(Graphic graphic, String name, String description) {
    	this.graphic = graphic;
        this.name = name;
        this.description = description;
        this.level = GameLevel.I;
        this.flyDays = 0;
    }
    
    //usato se il level NON genera informazioni ma devo generare giorni di volo casuali (nel flyDays farò un Deck.getRandom()) (qui mai)
    public Card(Graphic graphic, String name, String description, int flyDays) {
    	this.graphic = graphic;
        this.name = name;
        this.description = description;
        this.level = GameLevel.I;
        this.flyDays = flyDays;
    }
    
    //usato se il level mi serve per generare informazioni e devo generare pure giorni di volo casuali (nel flyDays farò un Deck.getRandom()) (le altre carte)
    public Card(Graphic graphic, String name, String description, GameLevel level, int flyDays) {
    	this.graphic = graphic;
        this.name = name;
        this.description = description;
        this.level = level;
        this.flyDays = flyDays;
    }

    public String getName() { 
    	return name; 
    }
    
    public String getDescription() { 
    	return description; 
    }
    
    public int getFlyDays() {
		return flyDays;
	}

	public GameLevel getLevel() {
		return level;
	}

    public abstract void execute(Board board);
    
    public void lostFlyDays(Board board, Player player) {
    	graphic.printMessage(player.getName() + " ha perso " + flyDays + " giorni di volo!");
    	board.moveBack(flyDays, player);
    }
}