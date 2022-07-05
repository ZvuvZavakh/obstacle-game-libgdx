package zvuv.zavakh.obstacle.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public abstract class GameObjectBase {

    private float x;
    private float y;

    private Circle bounds;

    public GameObjectBase(float boundsRadius) {
        bounds = new Circle(x, y, boundsRadius);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Circle getBounds() {
        return bounds;
    }

    public void setX(float x) {
        this.x = x;
        updateBounds();
    }

    public void setY(float y) {
        this.y = y;
        updateBounds();
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;

        updateBounds();
    }

    protected void updateBounds() {
        bounds.setPosition(x, y);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.x(bounds.x, bounds.y, 0.1f);
        shapeRenderer.circle(bounds.x, bounds.y, bounds.radius, 30);
    }
}
