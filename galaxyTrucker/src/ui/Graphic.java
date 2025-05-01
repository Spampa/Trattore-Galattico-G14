package ui;

import java.util.List;
import components.Component;
import entities.GameLevel;
import entities.Player;

public interface Graphic {
	
	//global function
	public void printAlert(String message);
	public void printMessage(String message);
	
	//initialize phase function
	public GameLevel setGameLevel();
	public int setPlayerCount();
	public Player[] setPlayers(int playerNumber, GameLevel level);
	
	//build phase function
	public int drawOrPeekComponent(int poolSize);
	public void printComponent(Component component);
	public boolean acceptComponentDraw();
	public boolean insertComponent(Player player, Component component);
	public Component selectItemFromDiscards(List<Component> discardedComponents);

}
