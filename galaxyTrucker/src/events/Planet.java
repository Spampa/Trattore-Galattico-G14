package events;

import entities.Player;
import items.Item;
import ui.Graphic;

public class Planet extends Event {
	
	private final String name;
	private final Item[] items;
	private Player player;
	
	public Planet(Graphic graphic, String name, Item[] items) {
		super(graphic);
		this.name = name;
		this.items = items;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Item[] getItems() {
		return this.items;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		if(this.player != null) throw new IllegalStateException("Player is already set and cannot be changed.");
		this.player = player;
	}

	@Override
	public void start() {
		AddItem add = new AddItem(super.graphic, items, player);
		add.start();
	}

}
