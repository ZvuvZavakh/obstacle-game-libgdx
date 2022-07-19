package zvuv.zavakh.obstacle.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;

public class BoundsComponent implements Component {

    private Circle bounds = new Circle();

    public Circle getBounds() {
        return bounds;
    }

    public void setPositon(float x, float y) {
        bounds.x = x;
        bounds.y = y;
    }
}
