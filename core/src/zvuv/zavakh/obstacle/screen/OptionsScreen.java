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
import zvuv.zavakh.obstacle.config.DifficultyLevel;

public class OptionsScreen extends GameScreenBase {

    private DifficultyLevel difficultyLevel;
    private ButtonGroup<CheckBox> checkboxGroup;
    private CheckBox easy;
    private CheckBox medium;
    private CheckBox hard;

    public OptionsScreen(App app) {
        super(app);
    }

    @Override
    public void show() {
        difficultyLevel = GameManager.getInstance().getDifficultyLevel();
        super.show();
    }

    @Override
    protected void initUI() {
        Table table = new Table();
        table.defaults().pad(15);

        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.ATLAS);
        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);

        TextureRegion backgroundRegion = gameplayAtlas.findRegion(RegionNames.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(backgroundRegion));

        Label label = new Label("DIFFICULTY", uiSkin);

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeDifficultyHandler();
            }
        };

        easy = createCheckbox(DifficultyLevel.EASY.name(), uiSkin);
        easy.addListener(changeListener);

        medium = createCheckbox(DifficultyLevel.MEDIUM.name(), uiSkin);
        medium.addListener(changeListener);

        hard = createCheckbox(DifficultyLevel.HARD.name(), uiSkin);
        hard.addListener(changeListener);

        checkboxGroup = new ButtonGroup<>(easy, medium, hard);
        checkboxGroup.setChecked(difficultyLevel.name());

        TextButton backButton = new TextButton("BACK", uiSkin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backHandler();
            }
        });

        Table contentTable = new Table(uiSkin);

        contentTable.defaults().pad(10);
        contentTable.setBackground(RegionNames.UI_PANEL);

        contentTable.add(label).row();
        contentTable.add(easy).row();
        contentTable.add(medium).row();
        contentTable.add(hard).row();
        contentTable.add(backButton).row();

        table.add(contentTable);
        table.center();
        table.setFillParent(true);
        table.pack();

        stage.addActor(table);
    }

    private void changeDifficultyHandler() {
        CheckBox checked = checkboxGroup.getChecked();

        if (checked == easy) {
            GameManager.getInstance().updateDifficulty(DifficultyLevel.EASY);
        } else if (checked == medium) {
            GameManager.getInstance().updateDifficulty(DifficultyLevel.MEDIUM);
        } else if (checked == hard) {
            GameManager.getInstance().updateDifficulty(DifficultyLevel.HARD);
        }
    }

    private void backHandler() {
        app.setScreen(new MenuScreen(app));
    }

    private CheckBox createCheckbox(String text, Skin skin) {
        CheckBox checkBox = new CheckBox(text, skin);
        checkBox.left().pad(10);
        checkBox.getLabelCell().pad(10);

        return checkBox;
    }
}
