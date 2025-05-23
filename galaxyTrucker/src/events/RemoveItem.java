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
			boolean loop = true;
			do {
				graphic.printMessage("Inserisci posizione del contenitore da cui rimuovere: " + item.getName());
				position = graphic.askComponentPosition();
				
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
