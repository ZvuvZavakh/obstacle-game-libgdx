package zvuv.zavakh.obstacle.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameManager {

    private static final GameManager INSTANCE = new GameManager();

    private final Preferences preferences;

    private static final String HIGHSCORE_KEY = "highscore";
    private int highscore = 0;

    private GameManager() {
        preferences = Gdx.app.getPreferences("save");

        highscore = preferences.getInteger(HIGHSCORE_KEY, 0);
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    public String getHighscore() {
        return String.valueOf(highscore);
    }

    public void updateHighscore(int highscore) {
        if (highscore > this.highscore) {
            preferences.putInteger(HIGHSCORE_KEY, highscore);
            preferences.flush();
        }
    }
}
