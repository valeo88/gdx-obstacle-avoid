package com.mygdx.obstacleavoid.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public abstract class BaseGameObject {

    private float x;
    private float y;
    private Circle bounds;

    public BaseGameObject(float boundsRaduis) {
        bounds = new Circle(x, y, boundsRaduis);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void setX(float x) {
        this.x = x;
        updateBounds();
    }

    public void setY(float y) {
        this.y = y;
        updateBounds();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void updateBounds() {
        bounds.setPosition(x, y);
    }

    public void drawDebug(ShapeRenderer renderer) {
        renderer.circle(bounds.x, bounds.y, bounds.radius, 30);
    }

    public Circle getBounds() {
        return bounds;
    }
}
