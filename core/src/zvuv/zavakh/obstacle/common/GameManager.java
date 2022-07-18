package zvuv.zavakh.obstacle.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import zvuv.zavakh.obstacle.config.DifficultyLevel;

public class GameManager {

    private static final GameManager INSTANCE = new GameManager();

    private final Preferences preferences;

    private static final String HIGHSCORE_KEY = "highscore";
    private int highscore = 0;

    private static final String DIFFICULTY_KEY = "difficulty";
    private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;

    private GameManager() {
        preferences = Gdx.app.getPreferences("save");

        highscore = preferences.getInteger(HIGHSCORE_KEY, 0);
        String difficultyName = preferences.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name());
        difficultyLevel = DifficultyLevel.valueOf(difficultyName);
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    public String getHighscore() {
        return String.valueOf(highscore);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void updateHighscore(int highscore) {
        if (highscore > this.highscore) {
            this.highscore = highscore;
            preferences.putInteger(HIGHSCORE_KEY, highscore);
            preferences.flush();
        }
    }

    public void updateDifficulty(DifficultyLevel difficulty) {
        if (difficultyLevel != difficulty) {
            this.difficultyLevel = difficulty;
            preferences.putString(DIFFICULTY_KEY, difficulty.name());
            preferences.flush();
        }
    }
}
