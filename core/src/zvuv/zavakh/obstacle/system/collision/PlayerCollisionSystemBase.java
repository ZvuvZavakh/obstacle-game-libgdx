package zvuv.zavakh.obstacle.system.collision;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.BoundsComponent;
import zvuv.zavakh.obstacle.component.PlayerComponent;

public abstract class PlayerCollisionSystemBase<T extends Collidable & Component> extends EntitySystem {

    protected final Family playerFamily = Family.all(
            PlayerComponent.class,
            BoundsComponent.class
    ).get();

    protected Family entityFamily;

    protected ComponentMapper<T> mapper;

    protected final CollisionListener collisionListener;

    public PlayerCollisionSystemBase(CollisionListener collisionListener, Class<T> type) {
        this.collisionListener = collisionListener;
        mapper = ComponentMapper.getFor(type);
        entityFamily = Family.all(
                type,
                BoundsComponent.class
        ).get();
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(playerFamily);
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(entityFamily);

        for (Entity player : players) {
            for (Entity entity : entities) {
                T component = mapper.get(entity);

                if (component.isCaught()) {
                    continue;
                }

                if (checkCollision(player, entity)) {
                    component.setCaught(true);
                    processEntity(entity);
                }
            }
        }
    }

    protected abstract void processEntity(Entity entity);

    protected boolean checkCollision(Entity player, Entity entity) {
        BoundsComponent playerBounds = Mappers.BOUNDS_MAPPER.get(player);
        BoundsComponent entityBounds = Mappers.BOUNDS_MAPPER.get(entity);

        return Intersector.overlaps(playerBounds.getBounds(), entityBounds.getBounds());
    }
}
