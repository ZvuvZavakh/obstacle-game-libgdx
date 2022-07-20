package zvuv.zavakh.obstacle.component;

import com.badlogic.ashley.core.Component;

public class DimensionComponent implements Component {

    private float width;
    private float heigth;

    public float getHeigth() {
        return heigth;
    }

    public void setHeigth(float heigth) {
        this.heigth = heigth;
    }

    public void setDimensions(float width, float heigth) {
        this.width = width;
        this.heigth = heigth;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
