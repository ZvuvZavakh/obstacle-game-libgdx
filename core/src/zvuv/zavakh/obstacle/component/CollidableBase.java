package zvuv.zavakh.obstacle.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import zvuv.zavakh.obstacle.system.collision.Collidable;

public abstract class CollidableBase implements Collidable, Component, Pool.Poolable {

    private boolean isCaugth = false;

    public void reset() {
        isCaugth = false;
    }

    public boolean isCaught() {
        return isCaugth;
    }

    public void setCaught(boolean caught) {
        isCaugth = caught;
    }
}
