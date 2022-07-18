package zvuv.zavakh.obstacle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.assets.RegionNames;

public class MenuScreen extends GameScreenBase {

    public MenuScreen(App app) {
        super(app);
    }

    @Override
    protected void initUI() {
        Table table = new Table();

        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);
        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.ATLAS);
        TextureRegion backgroundRegion = gameplayAtlas.findRegion(RegionNames.BACKGROUND);

        TextButton playButton = new TextButton("START", uiSkin);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playHandler();
            }
        });

        TextButton highscoreButton = new TextButton("HIGHSCORE", uiSkin);
        highscoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                highScoreHandler();
            }
        });

        TextButton optionsButton = new TextButton("OPTIONS", uiSkin);
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                optionsHandler();
            }
        });

        TextButton quitButton = new TextButton("QUIT", uiSkin);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quitHandler();
            }
        });

        Table buttonsTable = new Table(uiSkin);
        buttonsTable.defaults().pad(20);
        buttonsTable.setBackground(RegionNames.UI_PANEL);
        buttonsTable.add(playButton).row();
        buttonsTable.add(highscoreButton).row();
        buttonsTable.add(optionsButton).row();
        buttonsTable.add(quitButton).row();
        buttonsTable.center();

        table.add(buttonsTable);
        table.setBackground(new TextureRegionDrawable(backgroundRegion));
        table.center();
        table.setFillParent(true);
        table.pack();

        stage.addActor(table);
    }

    private void quitHandler() {
        Gdx.app.exit();
    }

    private void playHandler() {
        app.setScreen(new GameScreen(app));
    }

    private void highScoreHandler() {
        app.setScreen(new HighscoreScreen(app));
    }

    private void optionsHandler() {
        app.setScreen(new OptionsScreen(app));
    }
}
