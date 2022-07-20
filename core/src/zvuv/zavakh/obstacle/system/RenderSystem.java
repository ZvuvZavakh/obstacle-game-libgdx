package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.DimensionComponent;
import zvuv.zavakh.obstacle.component.PositionComponent;
import zvuv.zavakh.obstacle.component.TextureComponent;

public class RenderSystem extends EntitySystem {

    private final Viewport viewport;
    private final SpriteBatch spriteBatch;

    private Array<Entity> renderQueue = new Array<>();

    public RenderSystem(Viewport viewport, SpriteBatch spriteBatch) {
        this.viewport = viewport;
        this.spriteBatch = spriteBatch;
    }

    private static final Family renderFamily = Family.all(
            TextureComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(renderFamily);
        renderQueue.addAll(entities.toArray());

        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        draw();
        spriteBatch.end();
        renderQueue.clear();
    }

    private void draw() {
        for (Entity entity : renderQueue) {
            PositionComponent positionComponent = Mappers.POSITION_MAPPER.get(entity);
            DimensionComponent dimensionComponent = Mappers.DIMENSION_MAPPER.get(entity);
            TextureComponent textureComponent = Mappers.TEXTURE_MAPPER.get(entity);

            spriteBatch.draw(
                    textureComponent.getTextureRegion(),
                    positionComponent.getX() - dimensionComponent.getWidth() / 2,
                    positionComponent.getY() - dimensionComponent.getHeigth() / 2,
                    dimensionComponent.getWidth(),
                    dimensionComponent.getHeigth()
            );
        }
    }
}
