package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import zvuv.zavakh.obstacle.common.GameManager;
import zvuv.zavakh.obstacle.config.GameConfig;

public class ScoreSystem extends IntervalSystem {

    public ScoreSystem() {
        super(GameConfig.SCORE_MAX_TIME);
    }

    @Override
    protected void updateInterval() {
        GameManager.getInstance()
                .updateScore(MathUtils.random(1, 5));
    }
}
