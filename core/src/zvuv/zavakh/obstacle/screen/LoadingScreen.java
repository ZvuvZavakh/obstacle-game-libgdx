package zvuv.zavakh.obstacle.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.config.GameConfig;
import zvuv.zavakh.obstacle.util.GdxUtils;

public class LoadingScreen extends ScreenAdapter {

    private static final float PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH / 2f;
    private static final float PROGRESS_BAR_HEIGHT = 60f;

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;

    private float progress;
    private float waitTime = 0.75f;
    private boolean ready = false;

    private final App app;
    private final AssetManager assetManager;

    public LoadingScreen(App app) {
        this.app = app;
        assetManager = app.getAssetManager();
    }

    @Override
    public void render(float delta) {
        update(delta);

        GdxUtils.clearScreen();
        viewport.apply();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        draw();
        shapeRenderer.end();

        if (ready) {
            app.setScreen(new MenuScreen(app));
        }
    }

    private void update(float delta) {
        //waitMillis(400);
        progress = assetManager.getProgress();

        if (assetManager.update()) {
            waitTime -= delta;

            if (waitTime <= 0) {
                ready = true;
            }
        }
    }

    private void draw() {
        float progressBarX = (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f;
        float progressBarY = (GameConfig.HUD_HEIGHT - PROGRESS_BAR_HEIGHT) / 2f;

        shapeRenderer.rect(
                progressBarX,
                progressBarY,
                progress * PROGRESS_BAR_WIDTH,
                PROGRESS_BAR_HEIGHT
        );
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();

        assetManager.load(AssetDescriptors.FONT);
        assetManager.load(AssetDescriptors.ATLAS);
        assetManager.load(AssetDescriptors.UI_SKIN);
        assetManager.load(AssetDescriptors.SOUND_HIT);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
