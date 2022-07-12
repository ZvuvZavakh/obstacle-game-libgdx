package zvuv.zavakh.obstacle.controller;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import zvuv.zavakh.obstacle.common.GameManager;
import zvuv.zavakh.obstacle.config.DifficultyLevel;
import zvuv.zavakh.obstacle.config.GameConfig;
import zvuv.zavakh.obstacle.entity.Background;
import zvuv.zavakh.obstacle.entity.Obstacle;
import zvuv.zavakh.obstacle.entity.Player;
import zvuv.zavakh.obstacle.screen.GameScreen;

public class GameController {

    private static final Logger logger = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    private float scoreTimer;
    private int score;
    private int displayScore;

    private Player player;
    private float startPlayerX = GameConfig.WORLD_WIDTH / 2f;
    private float startPlayerY = 1;
    private int lives = GameConfig.LIVES_START;
    private DifficultyLevel difficultyLevel = DifficultyLevel.EASY;

    private Array<Obstacle> obstacles = new Array<>();
    private Pool<Obstacle> obstaclePool;
    private float obstacleTimer;

    private Background background;

    public GameController() {
        player = new Player();
        player.setPosition(startPlayerX, startPlayerY);

        obstaclePool = Pools.get(Obstacle.class, 40);

        background = new Background();
        background.setPosition(0f, 0f);
        background.setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
    }

    public Player getPlayer() {
        return player;
    }

    public int getLives() {
        return lives;
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public Background getBackground() {
        return background;
    }

    public void update(float delta) {
        if (isGameOver()) {
            return;
        }

        updatePlayer(delta);
        updateObstacles(delta);
        updateScore(delta);
        updateDisplayScore(delta);

        if (isPlayerCollidedWithObstacle()) {
            logger.debug("COLLISION!!!");
            lives--;

            if (isGameOver()) {
                logger.debug("GAME OVER!!!");
                GameManager.getInstance().updateHighscore(score);
            } else {
                restart();
            }
        }
    }

    private boolean isGameOver() {
        return lives <= 0;
    }

    private void restart() {
        player.setPosition(startPlayerX, startPlayerY);

        obstaclePool.freeAll(obstacles);
        obstacles.clear();
    }

    private boolean isPlayerCollidedWithObstacle() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isNotTouchingPlayer() && obstacle.isCollidingWithPlayer(player)) {
                return true;
            }
        }

        return false;
    }

    private void updatePlayer(float delta) {
        player.update();
        blockPlayerFromLeavingWorld();
    }

    private void blockPlayerFromLeavingWorld() {
        float playerHalfWidth = player.getWidth() / 2;
        float playerX = MathUtils.clamp(player.getX(), playerHalfWidth, GameConfig.WORLD_WIDTH - playerHalfWidth);

        player.setPosition(playerX, player.getY());
    }

    private void updateObstacles(float delta) {
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }

        createNewObstacle(delta);
        removePassedObstacles();
    }

    private void createNewObstacle(float delta) {
        obstacleTimer += delta;

        if (obstacleTimer > GameConfig.OBSTACLE_SPAWN_TIME) {
            float min = Obstacle.BOUNDS_RADIUS;
            float max = GameConfig.WORLD_WIDTH - Obstacle.BOUNDS_RADIUS;
            float obstacleX = MathUtils.random(min, max);
            float obstacleY = GameConfig.WORLD_HEIGHT;

            Obstacle obstacle = obstaclePool.obtain();
            obstacle.setPosition(obstacleX, obstacleY);
            obstacle.setYSpeed(difficultyLevel.getObstacleSpeed());

            obstacles.add(obstacle);
            obstacleTimer = 0f;
        }
    }

    private void removePassedObstacles() {
        if (obstacles.size > 0) {
            Obstacle firstObstacle = obstacles.first();
            float minY = -Obstacle.SIZE;

            if (firstObstacle.getY() < minY) {
                obstacles.removeValue(firstObstacle, true);
                obstaclePool.free(firstObstacle);
            }
        }
    }

    private void updateScore(float delta) {
        scoreTimer += delta;

        if (scoreTimer >= GameConfig.SCORE_MAX_TIME) {
            score += 5;
            scoreTimer = 0f;
        }
    }

    private void updateDisplayScore(float delta) {
        if (displayScore < score) {
            displayScore = Math.min(
                    score,
                    displayScore + (int) (60 * delta)
            );
        }
    }
}
