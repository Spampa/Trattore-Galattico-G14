package events;

import components.Component;
import components.models.containers.*;
import entities.Player;
import entities.Position;
import entities.ship.Ship;
import items.Battery;
import items.*;
import ui.Graphic;

public class AddItem extends Event {
	
	private final Item[] items;
	private final Player player;
	
	public AddItem(Graphic graphic, Item[] items, Player player) {
		super(graphic);
		this.items = items;
		this.player = player;
	}
	
	@Override
	public void start() {
		for(Item item: items) {
			if(graphic.askUser("Vuoi aggiungere la merce " + item.getName() + " alla tua nave?")) {
				Position position;
				do {
					graphic.printMessage("Inserisci posizione del contenitore di " + item.getName());
					position = graphic.askComponentPosition();
				}while(!this.isItemAdded(item, position, player.getShip()));

			}
		}
	}
	
	private boolean isItemAdded(Item item, Position position, Ship ship) {
		Component component = ship.getComponent(position);
	    if (item instanceof Battery battery && component instanceof BatteryStorage storage) {
	    	return storage.add(battery);
	    }
	    else if(item instanceof Ware ware && component instanceof WareStorage storage) {
	    	return storage.add(ware);
	    }
	    else if(item instanceof Spaceman spaceman && component instanceof HousingUnit storage) {
	    	return storage.add(spaceman);
	    }
		return false;
	}
}
