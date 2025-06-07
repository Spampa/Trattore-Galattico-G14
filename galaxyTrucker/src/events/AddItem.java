package events;

import entities.GameLevel;
import entities.Player;
import entities.Position;
import entities.ship.Ship;
import randomizer.ItemsRandomizer;
import items.*;
import ui.Graphic;

public class AddItem extends Event {
	
	private final Item[] items;
	private final Player player;
	private final GameLevel level;
	
	public AddItem(Graphic graphic, Item[] items, Player player) {
		super(graphic);
		this.items = items;
		this.player = player;
		this.level = player.getShip().getGameLevel();
	}
	
	
	@Override
	public void start() {
		for(Item item: items) {
			boolean loop = false;
			do {
				if(graphic.askBooleanUser("Vuoi aggiungere la merce " + item.getName() + " alla tua nave?")) {
					Position position;
					graphic.printShip(player.getShip());
					graphic.printMessage("Inserisci posizione del contenitore di " + item.getName());
					position = graphic.askComponentPosition(level);
					
					loop = !this.isItemAdded(item, position, player.getShip());
					if(loop) {
						graphic.printAlert("Il componente non puo' contenere " + item.getName());
					}
				}
				else {
					loop = false;
				}
			}while(loop);
		}
		graphic.printMessage("ottimo! " + player.getName() + " abbiamo finito di caricare gli oggetti");
	}

	private boolean isItemAdded(Item item, Position position, Ship ship) {
		try {
			return ship.addItem(position, item);
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
