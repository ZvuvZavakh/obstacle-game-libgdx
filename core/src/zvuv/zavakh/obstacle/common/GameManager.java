package zvuv.zavakh.obstacle.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import zvuv.zavakh.obstacle.config.DifficultyLevel;
import zvuv.zavakh.obstacle.config.GameConfig;

public class GameManager {

    private static final GameManager INSTANCE = new GameManager();

    private int lives = GameConfig.LIVES_START;
    private int score;

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

    public void updateHighscore() {
        if (score > highscore) {
            preferences.putInteger(HIGHSCORE_KEY, score);
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

    public void reset() {
        lives = GameConfig.LIVES_START;
        score = 0;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void decrementLives() {
        lives--;
    }

    public boolean isGameOver() {
        return lives <= 0;
    }

    public void updateScore(int amount) {
        score += amount;
    }
}
