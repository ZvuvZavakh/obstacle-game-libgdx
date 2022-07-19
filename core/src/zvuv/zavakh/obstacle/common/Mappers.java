package zvuv.zavakh.obstacle.common;

import com.badlogic.ashley.core.ComponentMapper;
import zvuv.zavakh.obstacle.component.BoundsComponent;
import zvuv.zavakh.obstacle.component.MovementComponent;
import zvuv.zavakh.obstacle.component.PositionComponent;

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS_MAPPER =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT_MAPPER =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION_MAPPER =
            ComponentMapper.getFor(PositionComponent.class);

    private Mappers() {}
}
