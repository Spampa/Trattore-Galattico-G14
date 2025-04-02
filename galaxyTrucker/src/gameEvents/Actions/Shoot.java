package gameEvents.Actions;

public class Shoot extends Action {

    private final ProjectileDirection direction;
    private final ProjectileType type;
    private final int comingTile;

    public Shoot(ProjectileDirection direction, ProjectileType type, int comingTile){
        super("Shoot");
        this.direction = direction;
        this.type = type;
        this.comingTile = comingTile;
    }

    public ProjectileDirection getDirection() {
        return direction;
    }

    public ProjectileType getType() {
        return type;
    }

    public int getComingTile() {
        return comingTile;
    }

}
