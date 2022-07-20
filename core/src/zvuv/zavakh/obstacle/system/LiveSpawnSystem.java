package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import zvuv.zavakh.obstacle.common.EntityFactory;
import zvuv.zavakh.obstacle.config.GameConfig;

public class LiveSpawnSystem extends IntervalSystem {

    private final EntityFactory entityFactory;

    public LiveSpawnSystem(EntityFactory entityFactory) {
        super(GameConfig.LIVE_SPAWN_TIME);
        this.entityFactory = entityFactory;
    }

    @Override
    protected void updateInterval() {
        float min = GameConfig.LIVE_BOUNDS_RADIUS;
        float max = GameConfig.WORLD_WIDTH - GameConfig.LIVE_BOUNDS_RADIUS;
        float x = MathUtils.random(min, max);
        float y = GameConfig.WORLD_HEIGHT;

        entityFactory.getLive(x, y);
    }
}
