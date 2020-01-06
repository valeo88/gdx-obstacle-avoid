package com.mygdx.obstacleavoid.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Obstacle {

    private static final float BOUNDS_RADIUS = 0.3f;
    private static final float SIZE = 2 * BOUNDS_RADIUS;

    private float x;
    private float y;

    private float ySpeed = 0.1f;

    private Circle bounds;

    public Obstacle() {
        bounds = new Circle(x, y, BOUNDS_RADIUS);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void update() {
        setPosition(x, y - ySpeed);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    private void updateBounds() {
        bounds.setPosition(x, y);
    }

    /** In debug mode represent obstacle as circle. */
    public void drawDebug(ShapeRenderer renderer) {
        renderer.circle(bounds.x, bounds.y, bounds.radius, 30);
    }

    public float getWidth() {
        return SIZE;
    }
}
