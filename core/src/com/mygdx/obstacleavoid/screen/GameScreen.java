package com.mygdx.obstacleavoid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.obstacleavoid.assets.AssetsPaths;
import com.mygdx.obstacleavoid.config.GameConfig;
import com.mygdx.obstacleavoid.entity.Obstacle;
import com.mygdx.obstacleavoid.entity.Player;
import com.mygdx.obstacleavoid.util.GdxUtils;
import com.mygdx.obstacleavoid.util.ViewportUtils;
import com.mygdx.obstacleavoid.util.debug.DebugCameraController;

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private OrthographicCamera hudCamera;
    private Viewport hudViewport;

    private SpriteBatch batch;
    private BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout();

    private Player player;
    private Array<Obstacle> obstacles = new Array<>();
    private float obstaclesTimer;
    private float scoreTimer;

    private int lives = GameConfig.LIVES_START;
    private int score;

    private DebugCameraController debugCameraController;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, hudCamera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal(AssetsPaths.UI_FONT));

        player = new Player();

        // player initial position
        float startPlayerX = GameConfig.WORLD_WIDTH / 2f;
        float startPlayerY = 1;
        player.setPosition(startPlayerX, startPlayerY);

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    @Override
    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        //if (lives > 0) {
            update(delta);
        //}

        GdxUtils.clearScreen();

        renderUi();

        renderDebug();
    }

    /** Update game world. */
    private void update(float delta) {
        updatePlayer();
        updateObstacles(delta);
        updateScore(delta);

        if (isPlayerCollidingWithObstacle()) {
            lives--;
        }
    }

    private void updatePlayer() {
        player.update();
        blockPlayerFromLeavingTheWorld();
    }

    private void blockPlayerFromLeavingTheWorld() {
        float playerX = MathUtils.clamp(player.getX(),
                player.getWidth() / 2f,
                GameConfig.WORLD_WIDTH - player.getWidth() / 2f);
        player.setPosition(playerX, player.getY());
    }

    private void updateObstacles(float delta) {
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }

        createNewObstacle(delta);
    }

    private void createNewObstacle(float delta) {
        obstaclesTimer += delta;
        if (obstaclesTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            float obstacleX = MathUtils.random(0f, GameConfig.WORLD_WIDTH);
            float obstacleY = GameConfig.WORLD_HEIGHT;

            Obstacle obstacle = new Obstacle();
            // block from cutting by bounds
            obstacleX = MathUtils.clamp(obstacleX,
                    obstacle.getWidth() / 2f,
                    GameConfig.WORLD_WIDTH - obstacle.getWidth() / 2f);
            obstacle.setPosition(obstacleX, obstacleY);

            obstacles.add(obstacle);
            obstaclesTimer = 0;
        }
    }

    private void updateScore(float delta) {
        scoreTimer += delta;
        if (scoreTimer >= GameConfig.SCORE_MAX_TIME) {
            score += MathUtils.random(1, 5);
            scoreTimer = 0f;
        }
    }

    private boolean isPlayerCollidingWithObstacle() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isPlayerColliding(player)) return true;
        }
        return false;
    }

    private void renderUi() {
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();

        String livesText = "LIVES: " + lives;
        layout.setText(font, livesText);
        font.draw(batch, livesText,
                20,
                GameConfig.HUD_HEIGHT - layout.height);

        String scoreText = "SCORE: " + score;
        layout.setText(font, scoreText);
        font.draw(batch, scoreText,
                GameConfig.HUD_WIDTH - layout.width - 20,
                GameConfig.HUD_HEIGHT - layout.height);

        batch.end();
    }

    /** Render debug graphics. */
    private void renderDebug() {
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        drawDebug();

        renderer.end();

        ViewportUtils.drawGrid(viewport, renderer);
    }

    private void drawDebug() {
        player.drawDebug(renderer);
        for (Obstacle obstacle : obstacles) {
            obstacle.drawDebug(renderer);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose () {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
