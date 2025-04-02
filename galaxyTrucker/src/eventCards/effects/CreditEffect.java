package eventCards.effects;

import gameEvents.Action;

public class CreditEffect extends Action {
    private final int amount;

    public CreditEffect(int amount) {
        super("CreditEffect");
        this.amount = amount;
    }

    public int getAmount() { return amount; }
}