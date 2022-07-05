package zvuv.zavakh.obstacle.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ViewportUtils {

    private static final Logger logger = new Logger(ViewportUtils.class.getName(), Logger.DEBUG);
    private static final int DEFAULT_CELL_SIZE = 1;

    private ViewportUtils() {}

    public static void drawGrid(Viewport viewport, ShapeRenderer shapeRenderer) {
        drawGrid(viewport, shapeRenderer, DEFAULT_CELL_SIZE);
    }

    public static void drawGrid(Viewport viewport, ShapeRenderer shapeRenderer, int cellSize) {
        Color oldColor = new Color(shapeRenderer.getColor());
        int worldWidth = (int) viewport.getWorldWidth();
        int worldHeight = (int) viewport.getWorldHeight();
        int doubleWorldWidth = worldWidth * 2;
        int doubleWorldHeight = worldHeight * 2;

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.CORAL);

        for (int x = -doubleWorldWidth; x < doubleWorldWidth; x += cellSize) {
            shapeRenderer.line(x, -doubleWorldHeight, x, doubleWorldHeight);
        }

        for (int y = -doubleWorldHeight; y < doubleWorldWidth; y += cellSize) {
            shapeRenderer.line(-doubleWorldWidth, y, doubleWorldWidth, y);
        }

        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.line(0, -doubleWorldHeight, 0, doubleWorldHeight);
        shapeRenderer.line(-doubleWorldWidth, 0, doubleWorldWidth, 0);

        shapeRenderer.setColor(Color.CHARTREUSE);
        shapeRenderer.line(0, worldHeight, worldWidth, worldHeight);
        shapeRenderer.line(worldWidth, 0, worldWidth, worldHeight);

        shapeRenderer.end();
        shapeRenderer.setColor(oldColor);
    }

    public static void debugPixelPerUnit(Viewport viewport) {
        float screenWidth = viewport.getScreenWidth();
        float screenHeight = viewport.getScreenHeight();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        float xPixelsPerUnit = screenWidth / worldWidth;
        float yPixelsPerUnit = screenHeight / worldHeight;

        logger.info(String.format("xPPU: %f, yPPU: %f", xPixelsPerUnit, yPixelsPerUnit));
    }
}
