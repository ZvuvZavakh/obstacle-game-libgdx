package zvuv.zavakh.obstacle.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.assets.RegionNames;
import zvuv.zavakh.obstacle.component.*;
import zvuv.zavakh.obstacle.config.GameConfig;

public class EntityFactory {

    private final PooledEngine pooledEngine;
    private final AssetManager assetManager;
    private final TextureAtlas textureAtlas;

    public EntityFactory(PooledEngine pooledEngine, AssetManager assetManager) {
        this.pooledEngine = pooledEngine;
        this.assetManager = assetManager;
        textureAtlas = assetManager.get(AssetDescriptors.ATLAS);
    }

    public void getPlayer() {
        float x = GameConfig.WORLD_WIDTH / 2f;
        float y = GameConfig.PLAYER_SIZE;

        BoundsComponent boundsComponent = pooledEngine.createComponent(BoundsComponent.class);
        boundsComponent.getBounds().set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);

        MovementComponent movementComponent = pooledEngine.createComponent(MovementComponent.class);

        PlayerComponent playerComponent = pooledEngine.createComponent(PlayerComponent.class);
        WorldWrapComponent worldWrapComponent = pooledEngine.createComponent(WorldWrapComponent.class);

        PositionComponent positionComponent = pooledEngine.createComponent(PositionComponent.class);
        positionComponent.setPosition(x, y);

        TextureComponent textureComponent = pooledEngine.createComponent(TextureComponent.class);
        textureComponent.setTextureRegion(textureAtlas.findRegion(RegionNames.PLAYER));

        DimensionComponent dimensionComponent = pooledEngine.createComponent(DimensionComponent.class);
        dimensionComponent.setDimensions(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);

        Entity player = pooledEngine.createEntity();

        player.add(boundsComponent);
        player.add(movementComponent);
        player.add(playerComponent);
        player.add(worldWrapComponent);
        player.add(positionComponent);
        player.add(textureComponent);
        player.add(dimensionComponent);

        pooledEngine.addEntity(player);
    }

    public void getObstacle(float x, float y) {
        BoundsComponent boundsComponent = pooledEngine.createComponent(BoundsComponent.class);
        boundsComponent.getBounds().set(x, y, GameConfig.OBSTACLE_BOUNDS_RADIUS);

        MovementComponent movementComponent = pooledEngine.createComponent(MovementComponent.class);
        movementComponent.setySpeed(-GameManager.getInstance().getDifficultyLevel().getObstacleSpeed());

        ObstacleComponent obstacleComponent = pooledEngine.createComponent(ObstacleComponent.class);
        CleanupComponent cleanupComponent = pooledEngine.createComponent(CleanupComponent.class);

        PositionComponent positionComponent = pooledEngine.createComponent(PositionComponent.class);
        positionComponent.setPosition(x, y);

        TextureComponent textureComponent = pooledEngine.createComponent(TextureComponent.class);
        textureComponent.setTextureRegion(textureAtlas.findRegion(RegionNames.OBSTACLE));

        DimensionComponent dimensionComponent = pooledEngine.createComponent(DimensionComponent.class);
        dimensionComponent.setDimensions(GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);

        Entity obstacle = pooledEngine.createEntity();

        obstacle.add(boundsComponent);
        obstacle.add(movementComponent);
        obstacle.add(obstacleComponent);
        obstacle.add(cleanupComponent);
        obstacle.add(positionComponent);
        obstacle.add(textureComponent);
        obstacle.add(dimensionComponent);

        pooledEngine.addEntity(obstacle);
    }

    public void getLive(float x, float y) {
        BoundsComponent boundsComponent = pooledEngine.createComponent(BoundsComponent.class);
        boundsComponent.getBounds().set(x, y, GameConfig.LIVE_BOUNDS_RADIUS);

        MovementComponent movementComponent = pooledEngine.createComponent(MovementComponent.class);
        movementComponent.setySpeed(-GameConfig.LIVE_SPEED);

        LiveComponent liveComponent = pooledEngine.createComponent(LiveComponent.class);
        CleanupComponent cleanupComponent = pooledEngine.createComponent(CleanupComponent.class);

        PositionComponent positionComponent = pooledEngine.createComponent(PositionComponent.class);
        positionComponent.setPosition(x, y);

        TextureComponent textureComponent = pooledEngine.createComponent(TextureComponent.class);
        textureComponent.setTextureRegion(textureAtlas.findRegion(RegionNames.PLAYER));

        DimensionComponent dimensionComponent = pooledEngine.createComponent(DimensionComponent.class);
        dimensionComponent.setDimensions(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);

        Entity live = pooledEngine.createEntity();

        live.add(boundsComponent);
        live.add(movementComponent);
        live.add(liveComponent);
        live.add(cleanupComponent);
        live.add(positionComponent);
        live.add(textureComponent);
        live.add(dimensionComponent);

        pooledEngine.addEntity(live);
    }

    public void getBackground() {
        PositionComponent positionComponent = pooledEngine.createComponent(PositionComponent.class);
        positionComponent.setPosition(GameConfig.WORLD_WIDTH / 2, GameConfig.WORLD_HEIGHT / 2);

        TextureComponent textureComponent = pooledEngine.createComponent(TextureComponent.class);
        textureComponent.setTextureRegion(textureAtlas.findRegion(RegionNames.BACKGROUND));

        DimensionComponent dimensionComponent = pooledEngine.createComponent(DimensionComponent.class);
        dimensionComponent.setDimensions(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);

        Entity background = pooledEngine.createEntity();

        background.add(positionComponent);
        background.add(textureComponent);
        background.add(dimensionComponent);

        pooledEngine.addEntity(background);
    }
}
