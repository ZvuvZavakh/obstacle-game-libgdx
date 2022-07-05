package zvuv.zavakh.obstacle.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Logger;
import zvuv.zavakh.obstacle.util.debug.DebugCameraController;

public class DebugCameraConfig {

    private static final Logger logger = new Logger(DebugCameraController.class.getName(), Logger.DEBUG);

    private static final String FILE_PATH = "debug/debugCameraConfig.json";
    private FileHandle fileHandle;

    private static final int DEFAULT_LEFT_KEY = Input.Keys.A;
    private static final int DEFAULT_RIGHT_KEY = Input.Keys.D;
    private static final int DEFAULT_UP_KEY = Input.Keys.W;
    private static final int DEFAULT_DOWN_KEY = Input.Keys.S;
    private static final int DEFAULT_ZOOM_IN_KEY = Input.Keys.Q;
    private static final int DEFAULT_ZOOM_OUT_KEY = Input.Keys.E;
    private static final int DEFAULT_RESET_KEY = Input.Keys.X;
    private static final int DEFAULT_LOG_KEY = Input.Keys.R;

    private static final float DEFAULT_MOVE_SPEED = 20.0f;
    private static final float DEFAULT_ZOOM_SPEED = 2.0f;
    private static final float DEFAULT_MAX_ZOOM_IN = 0.20f;
    private static final float DEFAULT_MAX_ZOOM_OUT = 30f;

    private float maxZoomIn;
    private float maxZoomOut;

    private float moveSpeed ;
    private float zoomSpeed;

    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;

    private int zoomInKey;
    private int zoomOutKey;

    private int resetKey;
    private int logKey;

    public DebugCameraConfig() {
        fileHandle = Gdx.files.internal(FILE_PATH);

        if (fileHandle.exists()) {
            loadConfig();
        } else {
            logger.info(String.format("Default file doesn't exist: %s", FILE_PATH));
            setupDefaults();
        }
    }

    private void loadConfig() {
        try {
            JsonReader reader = new JsonReader();
            JsonValue root = reader.parse(fileHandle);

            maxZoomIn = root.getFloat("maxZoomIn", DEFAULT_MAX_ZOOM_IN);
            maxZoomOut = root.getFloat("maxZoomOut", DEFAULT_MAX_ZOOM_OUT);

            moveSpeed = root.getFloat("moveSpeed", DEFAULT_MOVE_SPEED);
            zoomSpeed = root.getFloat("zoomSpeed", DEFAULT_ZOOM_SPEED);

            upKey = getInputKeyValue(root, "upKey", DEFAULT_UP_KEY);
            downKey = getInputKeyValue(root, "downKey", DEFAULT_DOWN_KEY);
            leftKey = getInputKeyValue(root, "leftKey", DEFAULT_LEFT_KEY);
            rightKey = getInputKeyValue(root, "rightKey", DEFAULT_RIGHT_KEY);

            zoomInKey = getInputKeyValue(root, "zoomInKey", DEFAULT_ZOOM_IN_KEY);
            zoomOutKey = getInputKeyValue(root, "zoomOutKey", DEFAULT_ZOOM_OUT_KEY);

            resetKey = getInputKeyValue(root, "resetKey", DEFAULT_RESET_KEY);
            logKey = getInputKeyValue(root, "logKey", DEFAULT_LOG_KEY);
        } catch (Exception e) {
            logger.error(String.format("Error parsing file: %s", FILE_PATH));
            setupDefaults();
        }
    }

    private static int getInputKeyValue(JsonValue root, String name, int defaultInput) {
        String keyString = root.getString(name, Input.Keys.toString(defaultInput));

        return Input.Keys.valueOf(keyString);
    }

    private void setupDefaults() {
        maxZoomIn = DEFAULT_MAX_ZOOM_IN;
        maxZoomOut = DEFAULT_MAX_ZOOM_OUT;

        moveSpeed = DEFAULT_MOVE_SPEED;
        zoomSpeed = DEFAULT_ZOOM_SPEED;

        upKey = DEFAULT_UP_KEY;
        downKey = DEFAULT_DOWN_KEY;
        leftKey = DEFAULT_LEFT_KEY;
        rightKey = DEFAULT_RIGHT_KEY;

        zoomInKey = DEFAULT_ZOOM_IN_KEY;
        zoomOutKey = DEFAULT_ZOOM_OUT_KEY;

        resetKey = DEFAULT_RESET_KEY;
        logKey = DEFAULT_LOG_KEY;
    }

    public float getMaxZoomIn() {
        return maxZoomIn;
    }

    public float getMaxZoomOut() {
        return maxZoomOut;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getZoomSpeed() {
        return zoomSpeed;
    }

    public boolean isLeftKeyPressed() {
        return Gdx.input.isKeyPressed(leftKey);
    }

    public boolean isRightKeyPressed() {
        return Gdx.input.isKeyPressed(rightKey);
    }

    public boolean isUpKeyPressed() {
        return Gdx.input.isKeyPressed(upKey);
    }

    public boolean isDownKeyPressed() {
        return Gdx.input.isKeyPressed(downKey);
    }

    public boolean isZoomInKeyPressed() {
        return Gdx.input.isKeyPressed(zoomInKey);
    }

    public boolean isZoomOutKeyPressed() {
        return Gdx.input.isKeyPressed(zoomOutKey);
    }

    public boolean isResetKeyPressed() {
        return Gdx.input.isKeyPressed(resetKey);
    }

    public boolean isLogKeyPressed() {
        return Gdx.input.isKeyPressed(logKey);
}
}
