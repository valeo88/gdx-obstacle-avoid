package com.mygdx.obstacleavoid.entity;


public class Obstacle extends  BaseGameObject{

    private static final float BOUNDS_RADIUS = 0.3f;
    private static final float SIZE = 2 * BOUNDS_RADIUS;

    private float ySpeed = 0.1f;

    public Obstacle() {
        super(BOUNDS_RADIUS);
    }


    public void update() {
        setY(getY() - ySpeed);
    }

    public float getWidth() {
        return SIZE;
    }
}
