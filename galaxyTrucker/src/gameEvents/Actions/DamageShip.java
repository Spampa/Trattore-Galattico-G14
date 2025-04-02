package gameEvents.Actions;

public class DamageShip extends Action {
    private final int damage;

    public DamageShip(int damage) {
        super("DamageShip");
        this.damage = damage;
    }

    public int getDamage() { return damage; }
}