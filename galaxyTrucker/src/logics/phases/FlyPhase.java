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
        players = game.getPlayers();
        cardsDeck = new Deck();
    }
    
    @Override
    public void start() {
    	graphic.printAlert("Inizio fase di volo!");
        graphic.printMessage("Recap delle navi in volo...");
        for(Player p : players){
            graphic.printMessage(p.getPlayerName() + "la tua nave è:");
            graphic.printShip(p.getPlayerShip());
        }
    }

    @Override
    public void update() {
        EventCard currentCard = this.cardsDeck.drawCard();

        while(currentCard != null){
            graphic.printCard(currentCard);
            //currentCard.executeEvent(players); 
        }

        game.switchPhase();
    }

    @Override
    public void end() {
        graphic.printAlert("Fine fase di volo!");
        graphic.printMessage("Recap delle navi in volo...");
        for(Player p : players){
            graphic.printMessage(p.getPlayerName() + "la tua nave è:");
            graphic.printShip(p.getPlayerShip());
        }
    }
    
}
