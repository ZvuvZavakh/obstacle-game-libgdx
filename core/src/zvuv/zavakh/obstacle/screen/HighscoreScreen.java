package zvuv.zavakh.obstacle.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.assets.AssetDescriptors;
import zvuv.zavakh.obstacle.assets.RegionNames;

public class HighscoreScreen extends GameScreenBase {

    public HighscoreScreen(App app) {
        super(app);
    }

    protected void initUI() {
        Table table = new Table();

        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.ATLAS);
        TextureAtlas UIAtlas = assetManager.get(AssetDescriptors.UI_ATLAS);
        BitmapFont font = assetManager.get(AssetDescriptors.FONT);

        TextureRegion backgroundRegion = gameplayAtlas.findRegion(RegionNames.BACKGROUND);
        TextureRegion panelRegion = UIAtlas.findRegion(RegionNames.UI_PANEL);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label highscoreText = new Label("HIGHSCORE", labelStyle);
        Label highscoreLabel = new Label("100", labelStyle);

        ImageButton backButton = createButton(UIAtlas, RegionNames.UI_BACK, RegionNames.UI_BACK_PRESSED);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });

        Table contentTable = new Table();
        contentTable.defaults().pad(20f);
        contentTable.setBackground(new TextureRegionDrawable(panelRegion));
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
        System.out.println("BACK");
        app.setScreen(new MenuScreen(app));
    }
}
