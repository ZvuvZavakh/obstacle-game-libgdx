package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.CleanupComponent;
import zvuv.zavakh.obstacle.component.PositionComponent;
import zvuv.zavakh.obstacle.config.GameConfig;

public class CleanupSystem extends IteratingSystem {

    private static final Family cleanupFamily = Family.all(
            CleanupComponent.class,
            PositionComponent.class
    ).get();

    public CleanupSystem() {
        super(cleanupFamily);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.POSITION_MAPPER.get(entity);

        if (positionComponent.getY() < -GameConfig.OBSTACLE_BOUNDS_RADIUS) {
            getEngine().removeEntity(entity);
        }
    }
}
