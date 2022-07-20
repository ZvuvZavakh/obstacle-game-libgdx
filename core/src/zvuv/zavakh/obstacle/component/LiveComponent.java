package zvuv.zavakh.obstacle.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class LiveComponent implements Component, Pool.Poolable {

    private boolean isCatched = false;

    @Override
    public void reset() {
        isCatched = false;
    }

    public boolean isCatched() {
        return isCatched;
    }

    public void setCatched(boolean catched) {
        isCatched = catched;
    }
}
