package zvuv.zavakh.obstacle.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import zvuv.zavakh.obstacle.component.*;
import zvuv.zavakh.obstacle.config.GameConfig;
import zvuv.zavakh.obstacle.entity.Obstacle;

public class EntityFactory {

    private final PooledEngine pooledEngine;

    public EntityFactory(PooledEngine pooledEngine) {
        this.pooledEngine = pooledEngine;
    }

    public void getPlayer() {
        float x = GameConfig.WORLD_WIDTH / 2f;
        float y = GameConfig.PLAYER_SIZE;

        BoundsComponent boundsComponent = pooledEngine.createComponent(BoundsComponent.class);
        boundsComponent.getBounds().set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);

        MovementComponent movementComponent = pooledEngine.createComponent(MovementComponent.class);

        PlayerComponent playerComponent = pooledEngine.createComponent(PlayerComponent.class);
        WorldWrapComponent worldWrapComponent = pooledEngine.createComponent(WorldWrapComponent.class);

        PositionComponent positionComponent = pooledEngine.createComponent(PositionComponent.class);
        positionComponent.setPosition(x, y);

        Entity player = pooledEngine.createEntity();

        player.add(boundsComponent);
        player.add(movementComponent);
        player.add(playerComponent);
        player.add(worldWrapComponent);
        player.add(positionComponent);

        pooledEngine.addEntity(player);
    }

    public void getObstacle(float x, float y) {
        BoundsComponent boundsComponent = pooledEngine.createComponent(BoundsComponent.class);
        boundsComponent.getBounds().set(x, y, GameConfig.OBSTACLE_BOUNDS_RADIUS);

        MovementComponent movementComponent = pooledEngine.createComponent(MovementComponent.class);
        movementComponent.setySpeed(-GameManager.getInstance().getDifficultyLevel().getObstacleSpeed());
        ObstacleComponent obstacleComponent = pooledEngine.createComponent(ObstacleComponent.class);

        PositionComponent positionComponent = pooledEngine.createComponent(PositionComponent.class);
        positionComponent.setPosition(x, y);

        Entity obstacle = pooledEngine.createEntity();

        obstacle.add(boundsComponent);
        obstacle.add(movementComponent);
        obstacle.add(obstacleComponent);
        obstacle.add(positionComponent);

        pooledEngine.addEntity(obstacle);
    }
}
