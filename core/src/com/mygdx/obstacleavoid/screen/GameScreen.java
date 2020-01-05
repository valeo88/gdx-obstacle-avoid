package com.mygdx.obstacleavoid.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.obstacleavoid.config.GameConfig;
import com.mygdx.obstacleavoid.entity.Player;
import com.mygdx.obstacleavoid.util.GdxUtils;
import com.mygdx.obstacleavoid.util.ViewportUtils;

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private Player player;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        player = new Player();

        // player initial position
        float startPlayerX = GameConfig.WORLD_WIDTH / 2f;
        float startPlayerY = 1;
        player.setPosition(startPlayerX, startPlayerY);
    }

    @Override
    public void render(float delta) {
        update(delta);

        GdxUtils.clearScreen();

        renderDebug();
    }

    /** Update game world. */
    private void update(float delta) {
        updatePlayer();
    }

    private void updatePlayer() {
        player.update();
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
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
    }
}
