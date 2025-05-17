package ui;

import components.Component;
import entities.*;
import entities.board.Board;
import entities.ship.Ship;
import eventCards.Card;

import java.util.ArrayList;
import java.util.List;

public interface Graphic {
	
	//global functions
	public void printAlert(String message);
	public void printMessage(String message);
	public void printShip(Ship s);
	public String getComponentIcon(Component c);
	public Position askComponentPosition();
	public void waitForUser(String message);
	public boolean askUser(String message);
	
	//initialize phase functions
	public GameLevel setGameLevel();
	public int setPlayerCount();
	public ArrayList<Player> setPlayers(int playerNumber, GameLevel level);
	
	//build phase functions
	public int drawOrPeekComponent(int poolSize);
	public void printComponent(Component component);
	public void printDiscardedComponents(List<Component> discardedComponents);
	public int getDiscardComponentIndex(int size);
	

	//fly phase functions
	public void printCard(Card c);
	public void printShipsRecap(ArrayList<Player> players);
	public void printBoard(Board board);
}
