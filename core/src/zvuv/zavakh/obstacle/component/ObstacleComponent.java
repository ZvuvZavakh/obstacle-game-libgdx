package zvuv.zavakh.obstacle.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ObstacleComponent implements Component, Pool.Poolable {

    private boolean hit = false;

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public void reset() {
        this.hit = false;
    }
}
