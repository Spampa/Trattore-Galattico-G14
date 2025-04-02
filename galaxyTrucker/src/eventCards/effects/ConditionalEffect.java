package eventCards.effects;

import gameEvents.Action;
import java.util.function.Predicate;

public class ConditionalEffect extends Action {
    private final Action effect;
    private final Predicate<GameState> condition;

    public ConditionalEffect(Action effect, Predicate<GameState> condition) {
        super("ConditionalEffect");
        this.effect = effect;
        this.condition = condition;
    }

    public Action getEffect() { return effect; }
    public Predicate<GameState> getCondition() { return condition; }
}