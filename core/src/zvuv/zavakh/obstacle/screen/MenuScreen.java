package zvuv.zavakh.obstacle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.assets.RegionNames;
import zvuv.zavakh.obstacle.config.GameConfig;
import zvuv.zavakh.obstacle.util.GdxUtils;

public class MenuScreen extends ScreenAdapter {

    private final App app;
    private final AssetManager assetManager;

    private Viewport viewport;
    private Stage stage;

    public MenuScreen(App app) {
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

    private void initUI() {
        Table table = new Table();

        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.ATLAS);
        TextureAtlas UIAtlas = assetManager.get(AssetDescriptors.UI_ATLAS);

        TextureRegion backgroundRegion = gameplayAtlas.findRegion(RegionNames.BACKGROUND);
        TextureRegion panelRegion = UIAtlas.findRegion(RegionNames.UI_PANEL);

        ImageButton playButton = createButton(UIAtlas, RegionNames.UI_PLAY, RegionNames.UI_PLAY_PRESSED);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playHandler();
            }
        });

        ImageButton highscoreButton = createButton(UIAtlas, RegionNames.UI_HIGHSCORE, RegionNames.UI_HIGHSCORE_PRESSED);
        highscoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                highScoreHandler();
            }
        });

        ImageButton optionsButton = createButton(UIAtlas, RegionNames.UI_OPTIONS, RegionNames.UI_OPTIONS_PRESSED);
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                optionsHandler();
            }
        });

        Table buttonsTable = new Table();
        buttonsTable.defaults().pad(20);
        buttonsTable.setBackground(new TextureRegionDrawable(panelRegion));
        buttonsTable.add(playButton).row();
        buttonsTable.add(highscoreButton).row();
        buttonsTable.add(optionsButton).row();
        buttonsTable.center();

        table.add(buttonsTable);
        table.setBackground(new TextureRegionDrawable(backgroundRegion));
        table.center();
        table.setFillParent(true);
        table.pack();

        stage.addActor(table);
    }

    private ImageButton createButton(TextureAtlas atlas, String upRegionName, String downRegionName) {
        TextureRegion upRegion = atlas.findRegion(upRegionName);
        TextureRegion downRegion = atlas.findRegion(downRegionName);

        return new ImageButton(
                new TextureRegionDrawable(upRegion),
                new TextureRegionDrawable(downRegion)
        );
    }

    private void playHandler() {
        System.out.println("PLAY");
    }

    private void highScoreHandler() {
        System.out.println("HIGHSCORE");
    }

    private void optionsHandler() {
        System.out.println("OPTIONS");
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
