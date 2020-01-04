package com.mygdx.obstacleavoid.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.obstacleavoid.util.GdxUtils;

public class GameScreen implements Screen {
    SpriteBatch batch;
    Texture img;

    @Override
    public void show() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen(new Color(1,0,0,1));

        batch.begin();

        batch.draw(img, 0, 0);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        img.dispose();
    }
}
