package logics.phases;

import entities.*;
import eventCards.Deck;
import eventCards.EventCard;
import logics.GameLogic;
import ui.Graphic;

public class FlyPhase extends Phase{

    private Player[] players;
    private Deck cardsDeck;

    public FlyPhase(GameLogic game, Graphic graphic){
        super(game, graphic);
        cardsDeck = new Deck();
    }
    
    @Override
    public void start() {
        players = game.getPlayers();
        graphic.printAlert("Inizio fase di volo!");
        graphic.printShipsRecap(players);
    }

    @Override
    public void update() {
        EventCard currentCard = this.cardsDeck.drawCard();

        while(currentCard != null){
            graphic.printCard(currentCard);
            //currentCard.executeEvent(players); 
            currentCard = this.cardsDeck.drawCard();
        }

        game.switchPhase();
    }

    @Override
    public void end() {
        graphic.printAlert("Fine fase di volo!");
        graphic.printShipsRecap(players);
    }
    
}
