package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.MovementComponent;
import zvuv.zavakh.obstacle.component.PositionComponent;

public class MovementSystem extends IteratingSystem {

    private static final Family movementFamily = Family.all(
            PositionComponent.class,
            MovementComponent.class
    ).get();

    public MovementSystem() {
        super(movementFamily);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.POSITION_MAPPER.get(entity);
        MovementComponent movementComponent = Mappers.MOVEMENT_MAPPER.get(entity);

        positionComponent.setPosition(
                positionComponent.getX() + movementComponent.getxSpeed(),
                positionComponent.getY() + movementComponent.getySpeed()
        );
    }
}
