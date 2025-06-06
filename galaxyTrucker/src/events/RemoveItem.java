package events;

import entities.GameLevel;
import entities.Player;
import entities.Position;
import entities.ship.Ship;
import items.Item;
import ui.Graphic;

public class RemoveItem extends Event {
	
	private final Item[] items;
	private final Player player;
	private final GameLevel level;
	
	public RemoveItem(Graphic graphic, Item[] items, Player player) {
		super(graphic);
		this.items = items;
		this.player = player;
		this.level = player.getShip().getGameLevel();
	}

	@Override
	public void start() {
		for(Item item: items) {
			Position position;
			boolean loop = true;
			do {
				graphic.printShip(player.getShip());
				graphic.printMessage("Inserisci posizione del contenitore da cui rimuovere: " + item.getName());
				position = graphic.askComponentPosition(level);
				
				loop = !this.isItemRemoved(item, position, player.getShip());
				if(loop) {
					graphic.printAlert("Il componente non possiede " + item.getName() + " da essere rimossi!");
				}
			}while(loop);
		}
	}
	
	private boolean isItemRemoved(Item item, Position position, Ship ship) {
		try {
			return ship.removeItem(position, item);
		}
		catch(Exception e) {
			return false;
		}
	}

}
