package com.mygdx.obstacleavoid.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Player {

    private static final float BOUNDS_RADIUS = 0.4f;
    private static final float SIZE = 2 * BOUNDS_RADIUS;

    private float x;
    private float y;

    private Circle bounds;

    public Player() {
        bounds = new Circle(x, y, BOUNDS_RADIUS);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    private void updateBounds() {
        bounds.setPosition(x, y);
    }

    /** In debug mode represent player as circle. */
    public void drawDebug(ShapeRenderer renderer) {
        renderer.circle(bounds.x, bounds.y, bounds.radius, 30);
    }
}
