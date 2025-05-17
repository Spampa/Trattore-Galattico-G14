package eventCards;

import entities.*;
import ui.Graphic;
import flightBoard.Board;

public abstract class Card {
    protected final Board board;
    protected final Graphic graphic;
    protected final String name;
    protected final String description;

    public Card(String name, String description, Board board, Graphic graphic) {
        this.name = name;
        this.description = description;
        this.board = board;
        this.graphic = graphic;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    public abstract void executeEvent(Ship ship, Player player);
}