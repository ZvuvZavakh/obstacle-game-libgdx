package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import zvuv.zavakh.obstacle.common.EntityFactory;
import zvuv.zavakh.obstacle.config.GameConfig;

public class ObstacleSpawnSystem extends IntervalSystem {

    private final EntityFactory entityFactory;

    public ObstacleSpawnSystem(EntityFactory entityFactory) {
        super(GameConfig.OBSTACLE_SPAWN_TIME);
        this.entityFactory = entityFactory;
    }

    @Override
    protected void updateInterval() {
        float min = GameConfig.OBSTACLE_BOUNDS_RADIUS;
        float max = GameConfig.WORLD_WIDTH - GameConfig.PLAYER_BOUNDS_RADIUS;
        float x = MathUtils.random(min, max);
        float y = GameConfig.WORLD_HEIGHT;

        entityFactory.getObstacle(x, y);
    }
}
