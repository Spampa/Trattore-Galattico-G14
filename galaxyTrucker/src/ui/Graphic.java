package ui;

import components.Component;
import entities.*;
import java.util.List;

public interface Graphic {
	
	//global function
	public void printAlert(String message);
	public void printMessage(String message);
	public void printShip(Ship s);
	public String getComponentIcon(Component c);
	public Position setComponentPosition();
	
	//initialize phase function
	public GameLevel setGameLevel();
	public int setPlayerCount();
	public Player[] setPlayers(int playerNumber, GameLevel level);
	
	//build phase function
	public int drawOrPeekComponent(int poolSize);
	public void printComponent(Component component);
	public boolean acceptComponentDraw();
	public void printDiscardedComponents(List<Component> discardedComponents);
	public int getDiscardComponentIndex(int size);
	public boolean isBuildFinish();
	public boolean getRotate();
	
}
