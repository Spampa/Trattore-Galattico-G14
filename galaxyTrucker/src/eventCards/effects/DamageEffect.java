package eventCards.effects;

import gameEvents.Action;

public class DamageEffect extends Action {
    private final int damage;

    public DamageEffect(int damage) {
        super("DamageEffect");
        this.damage = damage;
    }

    public int getDamage() { return damage; }
}