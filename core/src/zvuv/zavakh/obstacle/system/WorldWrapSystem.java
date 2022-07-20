package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.PositionComponent;
import zvuv.zavakh.obstacle.component.WorldWrapComponent;
import zvuv.zavakh.obstacle.config.GameConfig;

public class WorldWrapSystem extends IteratingSystem {

    private static final Family worldWrapFamily = Family.all(
            WorldWrapComponent.class,
            PositionComponent.class
    ).get();

    public WorldWrapSystem() {
        super(worldWrapFamily);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.POSITION_MAPPER.get(entity);
        float positionX = MathUtils.clamp(
                positionComponent.getX(),
                GameConfig.PLAYER_BOUNDS_RADIUS,
                GameConfig.WORLD_WIDTH - GameConfig.PLAYER_BOUNDS_RADIUS
        );


        positionComponent.setX(positionX);
    }
}
