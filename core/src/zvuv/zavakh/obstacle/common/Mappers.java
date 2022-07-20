package zvuv.zavakh.obstacle.common;

import com.badlogic.ashley.core.ComponentMapper;
import zvuv.zavakh.obstacle.component.*;

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS_MAPPER =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT_MAPPER =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION_MAPPER =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<ObstacleComponent> OBSTACLE_MAPPER =
            ComponentMapper.getFor(ObstacleComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE_MAPPER =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION_MAPPER =
            ComponentMapper.getFor(DimensionComponent.class);

    private Mappers() {}
}
