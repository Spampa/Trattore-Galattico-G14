package eventCards;

import java.util.Collections;
import java.util.Stack;
import gameEvents.Actions.*;

public class Deck {
    private Stack<EventCard> cards;

    public Deck() {
        this.cards = new Stack<>();
        initializeBaseDeck(); 
        shuffle();
    }

    private void initializeBaseDeck() {

        cards.add(new SimpleEventCard(
            "Asteroid Field", 
            "La nave subisce 2 danni",
            new DamageEffect(2)
        ));

        cards.add(new SimpleEventCard(
            "Pirate Attack", 
            "2 proiettili a sinistra (1 grosso, 1 piccolo)",
            new ShootEffect(ProjectileDirection.LEFT, ProjectileType.BIG_CANNON, 3),
            new ShootEffect(ProjectileDirection.LEFT, ProjectileType.SMALL_CANNON, 1)
        ));

        cards.add(new SimpleEventCard(
            "Abandoned Station", 
            "Se hai alieni a bordo, guadagni 3 crediti",
            new ConditionalEffect(
                new CreditEffect(3),
                gameState -> gameState.getCurrentPlayer().getAliensCounter() > 0
            )
        ));

        //ho fatto alcune carte, anche perchè non so se vanno bene fatte così.
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public EventCard drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }
}