package zvuv.zavakh.obstacle.util.debug;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
import zvuv.zavakh.obstacle.util.DebugCameraConfig;

public class DebugCameraController {

    private final DebugCameraConfig config = new DebugCameraConfig();
    private static final Logger logger = new Logger(DebugCameraController.class.getName(), Logger.DEBUG);

    private static final float DEFAULT_ZOOM_VALUE = 1.0f;

    private Vector2 position = new Vector2();
    private Vector2 startPosition = new Vector2();
    private float zoom = DEFAULT_ZOOM_VALUE;

    public DebugCameraController() {}

    public void setStartPosition(float x, float y) {
        startPosition.set(x, y);
        position.set(x, y);
    }

    public void applyTo(OrthographicCamera camera) {
        camera.position.set(position, 0);
        camera.zoom = zoom;
        camera.update();
    }

    public void handleDebugInput(float delta) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
            return;
        }

        float moveSpeed = config.getMoveSpeed() * delta;
        float zoomSpeed = config.getZoomSpeed() * delta;

        if (config.isLeftKeyPressed()) {
            moveLeft(moveSpeed);
        } else if (config.isRightKeyPressed()) {
            moveRight(moveSpeed);
        } else if (config.isUpKeyPressed()) {
            moveUp(moveSpeed);
        } else if (config.isDownKeyPressed()) {
            moveDown(moveSpeed);
        }

        if (config.isZoomInKeyPressed()) {
            zoomIn(zoomSpeed);
        } else if (config.isZoomOutKeyPressed()) {
            zoomOut(zoomSpeed);
        }

        if (config.isResetKeyPressed()) {
            reset();
        }

        if (config.isLogKeyPressed()) {
            logDebug();
        }
    }

    private void moveCamera(float xSpeed, float ySpeed) {
        setPosition(position.x + xSpeed, position.y + ySpeed);
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    private void moveLeft(float moveSpeed) {
        moveCamera(-moveSpeed, 0);
    }

    private void moveRight(float moveSpeed) {
        moveCamera(moveSpeed, 0);
    }

    private void moveUp(float moveSpeed) {
        moveCamera(0, moveSpeed);
    }

    private void moveDown(float moveSpeed) {
        moveCamera(0, -moveSpeed);
    }

    private void setZoom(float value) {
        zoom = MathUtils.clamp(value, config.getMaxZoomIn(), config.getMaxZoomOut());
    }

    private void zoomIn(float zoomSpeed) {
        setZoom(zoom + zoomSpeed);
    }

    private void zoomOut(float zoomSpeed) {
        setZoom(zoom - zoomSpeed);
    }

    private void reset() {
        position.set(startPosition);
        setZoom(DEFAULT_ZOOM_VALUE);
    }

    private void logDebug() {
        logger.debug("Pos: " + position + ", zoom: " + zoom);
    }
}
