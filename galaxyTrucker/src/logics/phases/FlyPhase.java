package logics.phases;

import eventCards.Deck;
import eventCards.Card;
import flightBoard.Board;
import logics.GameLogic;
import ui.Graphic;

public class FlyPhase extends Phase{

    private Deck cardsDeck;
    private Board board;

    public FlyPhase(GameLogic game, Graphic graphic){
        super(game, graphic);
    }
    
    @Override
    public void start() {
        game.setBoard(board);
        board = new Board(game.getPlayers(), game.getLevel());
        
        cardsDeck = new Deck(board, graphic);
        
        graphic.printAlert("Inizio fase di volo!");
        graphic.printShipsRecap(board.getPlayers());
    }

    @Override
    public void update() {
    	graphic.printBoard(board);
        Card currentCard = this.cardsDeck.drawCard();
        //if(currentCard != null) game.switchPhase();
        
        graphic.printCard(currentCard);
        //currentCard.executeEvent(players); 
        currentCard = this.cardsDeck.drawCard();
    }

    @Override
    public void end() {
        graphic.printAlert("Fine fase di volo!");
    }
    
}
