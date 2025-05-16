package eventCards;

import entities.*;
import ui.Graphic;
import flightBoard.Board;

public abstract class EventCard {
    protected final Board board;
    protected final Graphic graphic;
    protected final String name;
    protected final String description;

    public EventCard(String name, String description, Board board, Graphic graphic) {
        this.name = name;
        this.description = description;
        this.board = board;
        this.graphic = graphic;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    public abstract void executeEvent(Ship ship, Player player);
}