package com.mygdx.obstacleavoid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.mygdx.obstacleavoid.config.DifficultyLevel;
import com.mygdx.obstacleavoid.config.GameConfig;
import com.mygdx.obstacleavoid.entity.Background;
import com.mygdx.obstacleavoid.entity.Obstacle;
import com.mygdx.obstacleavoid.entity.Player;

/** This controller class contains game logic. */
public class GameController {
    private static final Logger logger = new Logger(GameController.class.getName(), Logger.DEBUG);

    // we need use player width cuz of diff bw batch and shape renderer positioning
    private final float startPlayerX = (GameConfig.WORLD_WIDTH - GameConfig.PLAYER_SIZE)/ 2f;
    private final float startPlayerY = 1 - GameConfig.PLAYER_SIZE / 2f;

    private Background background;
    private Player player;
    private Array<Obstacle> obstacles = new Array<>();
    private float obstaclesTimer;
    private float scoreTimer;
    private int lives = GameConfig.LIVES_START;
    private int score;
    private int displayScore;
    private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;
    private Pool<Obstacle> obstaclePool;

    public GameController() {
        init();
    }

    private void init() {
        player = new Player();
        player.setPosition(startPlayerX, startPlayerY);

        obstaclePool = Pools.get(Obstacle.class, 40);

        background = new Background();
        background.setPosition(0,0);
        background.setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
    }

    /** Update game world. */
    public void update(float delta) {
        if (isGameOver()) {
            return;
        }

        updatePlayer();
        updateObstacles(delta);
        updateScore(delta);
        updateDisplayScore(delta);

        if (isPlayerCollidingWithObstacle()) {
            lives--;

            if (isGameOver()) {
                logger.debug("Game over!");
            } else {
                restart();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public Background getBackground() {
        return background;
    }

    public boolean isGameOver() {
        return lives <= 0;
    }

    private void restart() {
        obstaclePool.freeAll(obstacles);
        obstacles.clear();
        player.setPosition(startPlayerX, startPlayerY);
    }

    private void updatePlayer() {
        // moving player on x coordinate
        float xSpeed = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xSpeed = GameConfig.MAX_PLAYER_X_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xSpeed = -GameConfig.MAX_PLAYER_X_SPEED;
        }

        player.setX(player.getX() + xSpeed);

        blockPlayerFromLeavingTheWorld();
    }

    private void blockPlayerFromLeavingTheWorld() {
        float playerX = MathUtils.clamp(player.getX(),
                0,
                GameConfig.WORLD_WIDTH - player.getWidth());
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
        obstaclesTimer += delta;
        if (obstaclesTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            float obstacleX = MathUtils.random(0,
                    GameConfig.WORLD_WIDTH - GameConfig.OBSTACLE_SIZE);
            float obstacleY = GameConfig.WORLD_HEIGHT;

            Obstacle obstacle = obstaclePool.obtain();
            obstacle.setYSpeed(difficultyLevel.getObstacleSpeed());
            // block from cutting by bounds
            obstacleX = MathUtils.clamp(obstacleX,
                    obstacle.getWidth() / 2f,
                    GameConfig.WORLD_WIDTH - obstacle.getWidth() / 2f);
            obstacle.setPosition(obstacleX, obstacleY);

            obstacles.add(obstacle);
            obstaclesTimer = 0;
        }
    }

    private void removePassedObstacles() {
        if (obstacles.size > 0) {
            Obstacle first = obstacles.first();

            float minObstacleY = -GameConfig.OBSTACLE_SIZE;
            if (first.getY() < 0) {
                obstacles.removeValue(first, true);
                obstaclePool.free(first);
            }
        }
    }

    private void updateScore(float delta) {
        scoreTimer += delta;
        if (scoreTimer >= GameConfig.SCORE_MAX_TIME) {
            score += MathUtils.random(1, 5);
            scoreTimer = 0f;
        }
    }

    /* Refresh score smoothly.*/
    private void updateDisplayScore(float delta) {
        if (displayScore < score) {
            displayScore = Math.min(
                    score,
                    displayScore + (int) (60 * delta)
            );
        }
    }

    private boolean isPlayerCollidingWithObstacle() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isNotHit() && obstacle.isPlayerColliding(player)) return true;
        }
        return false;
    }
}
