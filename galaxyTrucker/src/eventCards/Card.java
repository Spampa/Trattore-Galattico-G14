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
    
    public Card(Graphic graphic, String name, String description, GameLevel level) {
    	this.graphic = graphic;
        this.name = name;
        this.description = description;
        this.level = level;
        this.flyDays = 0;
    }
    
    public Card(Graphic graphic, String name, String description) {
    	this.graphic = graphic;
        this.name = name;
        this.description = description;
        this.level = GameLevel.I;
        this.flyDays = 0;
    }

    public Card(Graphic graphic, String name, String description, int flyDays) {
    	this.graphic = graphic;
        this.name = name;
        this.description = description;
        this.level = GameLevel.I;
        this.flyDays = flyDays;
    }
    
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