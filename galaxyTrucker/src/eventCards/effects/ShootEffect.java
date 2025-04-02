package eventCards.effects;

import gameEvents.Actions.Shoot;
import gameEvents.Actions.ProjectileDirection;
import gameEvents.Actions.ProjectileType;

public class ShootEffect extends Shoot {
    public ShootEffect(ProjectileDirection direction, ProjectileType type, int comingTile) {
        super(direction, type, comingTile);
    }
}