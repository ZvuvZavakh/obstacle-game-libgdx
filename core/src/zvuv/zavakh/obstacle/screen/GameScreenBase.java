package zvuv.zavakh.obstacle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.config.GameConfig;
import zvuv.zavakh.obstacle.util.GdxUtils;

public abstract class GameScreenBase extends ScreenAdapter {

    protected final App app;
    protected final AssetManager assetManager;

    protected Viewport viewport;
    protected Stage stage;

    public GameScreenBase(App app) {
        this.app = app;
        assetManager = app.getAssetManager();
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport, app.getSpriteBatch());

        Gdx.input.setInputProcessor(stage);
        initUI();
    }

    protected abstract void initUI();

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
