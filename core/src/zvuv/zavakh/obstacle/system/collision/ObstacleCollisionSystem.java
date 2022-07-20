package zvuv.zavakh.obstacle.system.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.BoundsComponent;
import zvuv.zavakh.obstacle.component.CollidableBase;
import zvuv.zavakh.obstacle.component.ObstacleComponent;
import zvuv.zavakh.obstacle.component.PlayerComponent;

public class ObstacleCollisionSystem extends PlayerCollisionSystemBase<ObstacleComponent> {

    public ObstacleCollisionSystem(CollisionListener collisionListener) {
        super(collisionListener, ObstacleComponent.class);
    }

    @Override
    protected void processEntity(Entity entity) {
        collisionListener.hitObstacle();
    }
}
