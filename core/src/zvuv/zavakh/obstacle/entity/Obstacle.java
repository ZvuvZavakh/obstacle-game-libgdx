package zvuv.zavakh.obstacle.entity;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Pool;

public class Obstacle extends GameObjectBase implements Pool.Poolable {

    public static final float BOUNDS_RADIUS = 0.3f;
    public static final float SIZE = 2 * BOUNDS_RADIUS;

    private float ySpeed = 0.1f;
    private boolean touchingPlayer;

    public Obstacle() {
        super(BOUNDS_RADIUS);
    }

    public void update() {
        setY(getY() - ySpeed);
    }

    public boolean isCollidingWithPlayer(Player player) {
        boolean overlaps = Intersector.overlaps(player.getBounds(), getBounds());

        touchingPlayer = overlaps;

        return overlaps;
    }

    public boolean isNotTouchingPlayer() {
        return !touchingPlayer;
    }

    public void setYSpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public void reset() {
        touchingPlayer = false;
    }
}
