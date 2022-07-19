package zvuv.zavakh.obstacle.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.BoundsComponent;

public class DebugRenderSystem extends IteratingSystem {

    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;
    private final static Family boundsFamily = Family.all(BoundsComponent.class).get();

    public DebugRenderSystem(Viewport viewport, ShapeRenderer shapeRenderer) {
        super(boundsFamily);
        this.viewport = viewport;
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent boundsComponent = Mappers.BOUNDS_MAPPER.get(entity);

        shapeRenderer.circle(
                boundsComponent.getBounds().x,
                boundsComponent.getBounds().y,
                boundsComponent.getBounds().radius,
                30
        );
    }

    @Override
    public void update(float deltaTime) {
        Color oldColor = shapeRenderer.getColor().cpy();

        viewport.apply();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.PINK);

        super.update(deltaTime);

        shapeRenderer.end();
        shapeRenderer.setColor(oldColor);
    }
}
