package zvuv.zavakh.obstacle.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.assets.RegionNames;
import zvuv.zavakh.obstacle.common.GameManager;

public class HighscoreScreen extends GameScreenBase {

    public HighscoreScreen(App app) {
        super(app);
    }

    @Override
    protected void initUI() {
        Table table = new Table();

        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);
        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.ATLAS);

        TextureRegion backgroundRegion = gameplayAtlas.findRegion(RegionNames.BACKGROUND);

        Label highscoreText = new Label("HIGHSCORE", uiSkin);
        Label highscoreLabel = new Label(GameManager.getInstance().getHighscore(), uiSkin);

        TextButton backButton = new TextButton("BACK", uiSkin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });

        Table contentTable = new Table(uiSkin);
        contentTable.defaults().pad(20f);
        contentTable.setBackground(RegionNames.UI_PANEL);
        contentTable.add(highscoreText).row();
        contentTable.add(highscoreLabel).row();
        contentTable.add(backButton).row();
        contentTable.center();

        table.add(contentTable);
        table.setBackground(new TextureRegionDrawable(backgroundRegion));
        table.center();
        table.setFillParent(true);
        table.pack();

        stage.addActor(table);
    }

    private void back() {
        app.setScreen(new MenuScreen(app));
    }
}
