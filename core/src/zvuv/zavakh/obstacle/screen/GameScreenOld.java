package zvuv.zavakh.obstacle.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.controller.GameController;
import zvuv.zavakh.obstacle.renderer.GameRenderer;

@Deprecated
public class GameScreenOld implements Screen {

    private final App app;
    private final AssetManager assetManager;
    private GameController gameController;
    private GameRenderer gameRenderer;

    public GameScreenOld(App app) {
        this.app = app;
        this.assetManager = app.getAssetManager();
    }

    @Override
    public void show() {
        gameController = new GameController(app);
        gameRenderer = new GameRenderer(gameController, assetManager, app.getSpriteBatch());
    }

    @Override
    public void render(float delta) {
        gameController.update(delta);
        gameRenderer.render(delta);

        if (gameController.isGameOver()) {
            app.setScreen(new MenuScreen(app));
        }
    }

    @Override
    public void resize(int width, int height) {
        gameRenderer.resize(width, height);
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
        gameRenderer.dispose();
    }
}
