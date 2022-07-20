package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.BoundsComponent;
import zvuv.zavakh.obstacle.component.PositionComponent;

public class BoundsSystem extends IteratingSystem {

    private static final Family boundsFamily = Family.all(
            BoundsComponent.class,
            PositionComponent.class
    ).get();

    public BoundsSystem() {
        super(boundsFamily);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent boundsComponent = Mappers.BOUNDS_MAPPER.get(entity);
        PositionComponent positionComponent = Mappers.POSITION_MAPPER.get(entity);

        boundsComponent.setPosition(positionComponent.getX(), positionComponent.getY());
    }
}
