package zvuv.zavakh.obstacle.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.assets.RegionNames;
import zvuv.zavakh.obstacle.config.GameConfig;

public class OptionsScreen extends GameScreenBase {
    private Image checkMark;

    public OptionsScreen(App app) {
        super(app);
    }

    @Override
    protected void initUI() {
        Table table = new Table();

        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.ATLAS);
        TextureAtlas UIAtlas = assetManager.get(AssetDescriptors.UI_ATLAS);
        BitmapFont font = assetManager.get(AssetDescriptors.FONT);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label label = new Label("DIFFICULTY", labelStyle);
        label.setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2 + 180, Align.center);

        TextureRegion backgroundRegion = gameplayAtlas.findRegion(RegionNames.BACKGROUND);
        Image background = new Image(new TextureRegionDrawable(backgroundRegion));

        final ImageButton easyButton = createButton(UIAtlas, RegionNames.UI_EASY);
        easyButton.setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2 + 90, Align.center);
        easyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                checkMark.setY(easyButton.getY() + 25);
            }
        });

        final ImageButton mediumButton = createButton(UIAtlas, RegionNames.UI_MEDIUM);
        mediumButton.setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2, Align.center);
        mediumButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                checkMark.setY(mediumButton.getY() + 25);
            }
        });

        final ImageButton hardButton = createButton(UIAtlas, RegionNames.UI_HARD);
        hardButton.setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2 - 90, Align.center);
        hardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                checkMark.setY(hardButton.getY() + 25);
            }
        });

        TextureRegion checkMarkRegion = UIAtlas.findRegion(RegionNames.UI_CHECK_MARK);
        checkMark = new Image(new TextureRegionDrawable(checkMarkRegion));
        checkMark.setPosition(mediumButton.getX() + 50, mediumButton.getY() + 40, Align.center);

        ImageButton backButton = createButton(UIAtlas, RegionNames.UI_BACK, RegionNames.UI_BACK_PRESSED);
        backButton.setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2 - 180, Align.center);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });

        stage.addActor(background);
        stage.addActor(label);
        stage.addActor(easyButton);
        stage.addActor(mediumButton);
        stage.addActor(hardButton);
        stage.addActor(checkMark);
        stage.addActor(backButton);

        table.defaults().pad(15);
        table.center();
        table.setFillParent(true);
        table.pack();
    }

    private void back() {
        app.setScreen(new MenuScreen(app));
    }
}
