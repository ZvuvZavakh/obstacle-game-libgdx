package zvuv.zavakh.obstacle.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import zvuv.zavakh.obstacle.util.debug.DebugCameraController;

public class DebugCameraSystem extends EntitySystem {

    private final DebugCameraController debugCameraController = new DebugCameraController();
    private final OrthographicCamera camera;

    public DebugCameraSystem(OrthographicCamera camera, float startX, float startY) {
        this.camera = camera;
        debugCameraController.setStartPosition(startX, startY);
    }

    @Override
    public void update(float deltaTime) {
        debugCameraController.handleDebugInput(deltaTime);
        debugCameraController.applyTo(camera);
    }
}
