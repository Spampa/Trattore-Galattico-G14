package events;

import components.Component;
import components.models.containers.BatteryStorage;
import components.models.containers.HousingUnit;
import components.models.containers.WareStorage;
import entities.Player;
import entities.Position;
import entities.ship.Ship;
import items.Battery;
import items.Item;
import items.Spaceman;
import items.Ware;
import ui.Graphic;

public class RemoveItem extends Event {
	
	private final Item[] items;
	private final Player player;
	
	public RemoveItem(Graphic graphic, Item[] items, Player player) {
		super(graphic);
		this.items = items;
		this.player = player;
	}

	@Override
	public void start() {
		for(Item item: items) {
			Position position;
			do {
				graphic.printMessage("Inserisci posizione del contenitore da cui rimuovere: " + item.getName());
				position = graphic.askComponentPosition();
			}while(!this.isItemRemoved(item, position, player.getShip()));
		}
	}
	
	private boolean isItemRemoved(Item item, Position position, Ship ship) {
		Component component = ship.getComponent(position);
	    if (item instanceof Battery && component instanceof BatteryStorage storage) {
	    	return storage.remove() != null;
	    }
	    else if(item instanceof Ware && component instanceof WareStorage storage) {
	    	return storage.remove() != null;
	    }
	    else if(item instanceof Spaceman && component instanceof HousingUnit storage) {
	    	return storage.remove() != null;
	    }
		return false;
	}

}
