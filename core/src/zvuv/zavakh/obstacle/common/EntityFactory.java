package zvuv.zavakh.obstacle.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import zvuv.zavakh.obstacle.component.BoundsComponent;
import zvuv.zavakh.obstacle.component.MovementComponent;
import zvuv.zavakh.obstacle.component.PlayerComponent;
import zvuv.zavakh.obstacle.component.PositionComponent;
import zvuv.zavakh.obstacle.config.GameConfig;

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

        PositionComponent positionComponent = pooledEngine.createComponent(PositionComponent.class);
        positionComponent.setPosition(x, y);

        Entity player = pooledEngine.createEntity();

        player.add(boundsComponent);
        player.add(movementComponent);
        player.add(playerComponent);
        player.add(positionComponent);

        pooledEngine.addEntity(player);
    }
}
