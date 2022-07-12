package zvuv.zavakh.obstacle.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.assets.RegionNames;

public class MenuScreen extends GameScreenBase {

    public MenuScreen(App app) {
        super(app);
    }

    protected void initUI() {
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

    private void playHandler() {
        System.out.println("PLAY");
        app.setScreen(new GameScreen(app));
    }

    private void highScoreHandler() {
        System.out.println("HIGHSCORE");
        app.setScreen(new HighscoreScreen(app));
    }

    private void optionsHandler() {
        System.out.println("OPTIONS");
        app.setScreen(new OptionsScreen(app));
    }
}
