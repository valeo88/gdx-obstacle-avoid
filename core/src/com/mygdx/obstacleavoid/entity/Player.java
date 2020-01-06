package com.mygdx.obstacleavoid.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.obstacleavoid.config.GameConfig;

public class Player extends BaseGameObject {

    private static final float BOUNDS_RADIUS = 0.4f;
    private static final float SIZE = 2 * BOUNDS_RADIUS;

    public Player() {
        super(BOUNDS_RADIUS);
    }

    public void update() {
        // moving player on x coordinate
        float xSpeed = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xSpeed = GameConfig.MAX_PLAYER_X_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xSpeed = -GameConfig.MAX_PLAYER_X_SPEED;
        }

        setX(getX() + xSpeed);
        updateBounds();
    }

    public float getWidth() {
        return SIZE;
    }
}
