package zvuv.zavakh.obstacle.system.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.BoundsComponent;
import zvuv.zavakh.obstacle.component.ObstacleComponent;
import zvuv.zavakh.obstacle.component.PlayerComponent;

public class CollisionSystem extends EntitySystem {

    private static final Family playerFamily = Family.all(
            PlayerComponent.class,
            BoundsComponent.class
    ).get();

    private static final Family obstacleFamily = Family.all(
            ObstacleComponent.class,
            BoundsComponent.class
    ).get();

    private final CollisionListener collisionListener;

    public CollisionSystem(CollisionListener collisionListener) {
        this.collisionListener = collisionListener;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(playerFamily);
        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(obstacleFamily);

        for (Entity player : players) {
            for (Entity obstacle : obstacles) {
                ObstacleComponent obstacleComponent = Mappers.OBSTACLE_MAPPER.get(obstacle);

                if (obstacleComponent.isHit()) {
                    continue;
                }

                if (checkCollision(player, obstacle)) {
                    obstacleComponent.setHit(true);
                    collisionListener.hitObstacle();
                    System.out.println("HIT!");
                }
            }
        }
    }

    private boolean checkCollision(Entity player, Entity obstacle) {
        BoundsComponent playerBounds = Mappers.BOUNDS_MAPPER.get(player);
        BoundsComponent obstacleBounds = Mappers.BOUNDS_MAPPER.get(obstacle);

        return Intersector.overlaps(playerBounds.getBounds(), obstacleBounds.getBounds());
    }
}
