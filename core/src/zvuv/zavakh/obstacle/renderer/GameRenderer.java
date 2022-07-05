package zvuv.zavakh.obstacle.renderer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.assets.RegionNames;
import zvuv.zavakh.obstacle.config.GameConfig;
import zvuv.zavakh.obstacle.controller.GameController;
import zvuv.zavakh.obstacle.entity.Background;
import zvuv.zavakh.obstacle.entity.Obstacle;
import zvuv.zavakh.obstacle.entity.Player;
import zvuv.zavakh.obstacle.util.GdxUtils;
import zvuv.zavakh.obstacle.util.ViewportUtils;
import zvuv.zavakh.obstacle.util.debug.DebugCameraController;

public class GameRenderer implements Disposable {

    private final GameController gameController;
    private final AssetManager assetManager;
    private final DebugCameraController debugCameraController;

    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;

    private final OrthographicCamera hudCamera;
    private final Viewport hudViewport;

    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;
    private final GlyphLayout glyphLayout = new GlyphLayout();

    private final TextureRegion playerTexture;
    private final TextureRegion obstacleTexture;
    private final TextureRegion backgroundTexture;

    public GameRenderer(GameController gameController, AssetManager assetManager) {
        this.gameController = gameController;
        this.assetManager = assetManager;

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, hudCamera);
        spriteBatch = new SpriteBatch();
        bitmapFont = assetManager.get(AssetDescriptors.FONT);

        TextureAtlas atlas = assetManager.get(AssetDescriptors.ATLAS);
        playerTexture = atlas.findRegion(RegionNames.PLAYER);
        obstacleTexture = atlas.findRegion(RegionNames.OBSTACLE);
        backgroundTexture = atlas.findRegion(RegionNames.BACKGROUND);
    }

    public void render (float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        GdxUtils.clearScreen();

        renderGameplay();

        renderUI();
        //renderDebug();
    }

    private void renderGameplay() {
        viewport.apply();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        drawBackground();
        drawPlayer();
        drawObstacles();

        spriteBatch.end();
    }

    private void drawPlayer() {
        Player player = gameController.getPlayer();

        spriteBatch.draw(
                playerTexture,
                player.getX() - Player.BOUNDS_RADIUS,
                player.getY() - Player.BOUNDS_RADIUS,
                player.getWidth(),
                player.getHeight()
        );
    }

    private void drawObstacles() {
        for (Obstacle obstacle : gameController.getObstacles()) {
            spriteBatch.draw(
                    obstacleTexture,
                    obstacle.getX() - Obstacle.BOUNDS_RADIUS,
                    obstacle.getY() - Obstacle.BOUNDS_RADIUS,
                    Obstacle.SIZE,
                    Obstacle.SIZE
            );
        }
    }

    private void drawBackground() {
        Background background = gameController.getBackground();

        spriteBatch.draw(
                backgroundTexture,
                background.getX(),
                background.getY(),
                background.getWidth(),
                background.getHeight()
        );
    }

    private void renderDebug() {
        viewport.apply();

        ViewportUtils.drawGrid(viewport, shapeRenderer);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawPlayerDebug();
        drawObstaclesDebug();
        shapeRenderer.end();
    }

    private void drawPlayerDebug() {
        gameController
                .getPlayer()
                .drawDebug(shapeRenderer);
    }

    private void drawObstaclesDebug() {
        for (Obstacle obstacle : gameController.getObstacles()) {
            obstacle.drawDebug(shapeRenderer);
        }
    }

    private void renderUI() {
        hudViewport.apply();

        spriteBatch.setProjectionMatrix(hudCamera.combined);
        spriteBatch.begin();

        String livesText = "LIVES: " + gameController.getLives();
        String scoreText = "SCORE: " + gameController.getDisplayScore();

        glyphLayout.setText(bitmapFont, livesText);
        bitmapFont.draw(
                spriteBatch,
                livesText,
                20,
                GameConfig.HUD_HEIGHT - glyphLayout.height
        );

        glyphLayout.setText(bitmapFont, scoreText);
        bitmapFont.draw(
                spriteBatch,
                scoreText,
                GameConfig.HUD_WIDTH - glyphLayout.width - 20,
                GameConfig.HUD_HEIGHT - glyphLayout.height
        );

        spriteBatch.end();
    }

    @Override
    public void dispose () {
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }
}
