package zvuv.zavakh.obstacle.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.common.EntityFactory;
import zvuv.zavakh.obstacle.config.GameConfig;
import zvuv.zavakh.obstacle.system.*;
import zvuv.zavakh.obstacle.system.debug.DebugCameraSystem;
import zvuv.zavakh.obstacle.system.debug.DebugRenderSystem;
import zvuv.zavakh.obstacle.system.debug.GridRenderSystem;
import zvuv.zavakh.obstacle.util.GdxUtils;

public class GameScreen implements Screen {

    private final App app;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private PooledEngine pooledEngine;

    private EntityFactory entityFactory;

    public GameScreen(App app) {
        this.app = app;
        this.assetManager = app.getAssetManager();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();
        pooledEngine = new PooledEngine();
        entityFactory = new EntityFactory(pooledEngine);

        pooledEngine.addSystem(new GridRenderSystem(viewport, shapeRenderer));
        pooledEngine.addSystem(new DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y));
        pooledEngine.addSystem(new DebugRenderSystem(viewport, shapeRenderer));
        pooledEngine.addSystem(new PlayerSystem());
        pooledEngine.addSystem(new ObstacleSpawnSystem(entityFactory));
        pooledEngine.addSystem(new MovementSystem());
        pooledEngine.addSystem(new WorldWrapSystem());
        pooledEngine.addSystem(new BoundsSystem());

        entityFactory.getPlayer();
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        pooledEngine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
