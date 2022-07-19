package zvuv.zavakh.obstacle.component;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {

    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
