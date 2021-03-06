package zvuv.zavakh.obstacle.system.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.BoundsComponent;
import zvuv.zavakh.obstacle.component.LiveComponent;
import zvuv.zavakh.obstacle.component.PlayerComponent;

public class LiveCollisionSystem extends PlayerCollisionSystemBase<LiveComponent> {

    public LiveCollisionSystem(CollisionListener collisionListener) {
        super(collisionListener, LiveComponent.class);
    }

    @Override
    protected void processEntity(Entity entity) {
        collisionListener.catchLive();
        getEngine().removeEntity(entity);
    }
}
