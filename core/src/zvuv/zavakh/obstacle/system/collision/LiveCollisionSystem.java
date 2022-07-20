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

public class LiveCollisionSystem extends EntitySystem {

    private static final Family playerFamily = Family.all(
            PlayerComponent.class,
            BoundsComponent.class
    ).get();

    private static final Family liveFamily = Family.all(
            LiveComponent.class,
            BoundsComponent.class
    ).get();

    private final CollisionListener collisionListener;

    public LiveCollisionSystem(CollisionListener collisionListener) {
        this.collisionListener = collisionListener;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(playerFamily);
        ImmutableArray<Entity> lives = getEngine().getEntitiesFor(liveFamily);

        for (Entity player : players) {
            for (Entity live : lives) {
                LiveComponent liveComponent = Mappers.LIVE_MAPPER.get(live);

                if (liveComponent.isCatched()) {
                    continue;
                }

                if (checkCollision(player, live)) {
                    liveComponent.setCatched(true);
                    collisionListener.catchLive();
                    getEngine().removeEntity(live);
                }
            }
        }
    }

    private boolean checkCollision(Entity player, Entity live) {
        BoundsComponent playerBounds = Mappers.BOUNDS_MAPPER.get(player);
        BoundsComponent liveBounds = Mappers.BOUNDS_MAPPER.get(live);

        return Intersector.overlaps(playerBounds.getBounds(), liveBounds.getBounds());
    }
}
