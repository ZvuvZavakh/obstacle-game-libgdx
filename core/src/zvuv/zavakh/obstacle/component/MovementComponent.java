package zvuv.zavakh.obstacle.component;

import com.badlogic.ashley.core.Component;

public class MovementComponent implements Component {

    private float xSpeed;
    private float ySpeed;

    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }
}
