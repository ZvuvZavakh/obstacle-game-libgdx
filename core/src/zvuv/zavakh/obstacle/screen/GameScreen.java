package zvuv.zavakh.obstacle.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.common.EntityFactory;
import zvuv.zavakh.obstacle.common.GameManager;
import zvuv.zavakh.obstacle.config.GameConfig;
import zvuv.zavakh.obstacle.system.*;
import zvuv.zavakh.obstacle.system.collision.CollisionListener;
import zvuv.zavakh.obstacle.system.collision.CollisionSystem;
import zvuv.zavakh.obstacle.system.collision.LiveCollisionSystem;
import zvuv.zavakh.obstacle.system.debug.DebugCameraSystem;
import zvuv.zavakh.obstacle.system.debug.DebugRenderSystem;
import zvuv.zavakh.obstacle.system.debug.GridRenderSystem;
import zvuv.zavakh.obstacle.util.GdxUtils;

public class GameScreen implements Screen {

    private final App app;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer shapeRenderer;
    private PooledEngine pooledEngine;
    private Sound hitSound;

    private EntityFactory entityFactory;

    private boolean reset = false;

    public GameScreen(App app) {
        this.app = app;
        this.assetManager = app.getAssetManager();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        pooledEngine = new PooledEngine();
        entityFactory = new EntityFactory(pooledEngine, assetManager);
        hitSound = assetManager.get(AssetDescriptors.SOUND_HIT);

        BitmapFont font = assetManager.get(AssetDescriptors.FONT);

        CollisionListener collisionListener = new CollisionListener() {
            @Override
            public void hitObstacle() {
                hitSound.play();
                GameManager.getInstance().decrementLives();

                if (GameManager.getInstance().isGameOver()) {
                    GameManager.getInstance().updateHighscore();
                } else {
                    pooledEngine.removeAllEntities();
                    reset = true;
                }
            }

            @Override
            public void catchLive() {
                hitSound.play();
                GameManager.getInstance().addLive();
            }
        };

        pooledEngine.addSystem(new DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y));
        pooledEngine.addSystem(new PlayerSystem());
        pooledEngine.addSystem(new ObstacleSpawnSystem(entityFactory));
        pooledEngine.addSystem(new LiveSpawnSystem(entityFactory));
        pooledEngine.addSystem(new MovementSystem());
        pooledEngine.addSystem(new CleanupSystem());
        pooledEngine.addSystem(new WorldWrapSystem());
        pooledEngine.addSystem(new BoundsSystem());
        pooledEngine.addSystem(new CollisionSystem(collisionListener));
        pooledEngine.addSystem(new LiveCollisionSystem(collisionListener));
        pooledEngine.addSystem(new ScoreSystem());

        pooledEngine.addSystem(new RenderSystem(viewport, app.getSpriteBatch()));

        if (GameConfig.DISPLAY_DEBUG) {
            pooledEngine.addSystem(new GridRenderSystem(viewport, shapeRenderer));
            pooledEngine.addSystem(new DebugRenderSystem(viewport, shapeRenderer));
        }

        pooledEngine.addSystem(new HudRenderSystem(hudViewport, app.getSpriteBatch(), font));

        addEntities();
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        pooledEngine.update(delta);

        if (GameManager.getInstance().isGameOver()) {
            GameManager.getInstance().reset();
            app.setScreen(new MenuScreen(app));
        }

        if (reset) {
            addEntities();
            reset = false;
        }
    }

    private void addEntities() {
        entityFactory.getBackground();
        entityFactory.getPlayer();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
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
