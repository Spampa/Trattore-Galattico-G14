package logics.phases;

import java.util.ArrayList;

import entities.*;
import eventCards.Deck;
import eventCards.EventCard;
import flightBoard.Board;
import logics.GameLogic;
import ui.Graphic;

public class FlyPhase extends Phase{

    private ArrayList<Player> players;
    private Deck cardsDeck;
    private Board board;

    public FlyPhase(GameLogic game, Graphic graphic){
        super(game, graphic);
        cardsDeck = new Deck();
    }
    
    @Override
    public void start() {
        players = game.getPlayers();
        game.setBoard(board);
        board = new Board(game.getPlayers(), game.getLevel());
        
        graphic.printAlert("Inizio fase di volo!");
        graphic.printShipsRecap(players);
    }

    @Override
    public void update() {
    	graphic.printBoard(board);
        EventCard currentCard = this.cardsDeck.drawCard();
        //if(currentCard != null) game.switchPhase();
        
        graphic.printCard(currentCard);
        //currentCard.executeEvent(players); 
        currentCard = this.cardsDeck.drawCard();
    }

    @Override
    public void end() {
        graphic.printAlert("Fine fase di volo!");
        graphic.printShipsRecap(players);
    }
    
}
