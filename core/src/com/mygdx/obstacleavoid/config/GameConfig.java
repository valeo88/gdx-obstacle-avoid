package com.mygdx.obstacleavoid.config;

/** Constants for game configuration. */
public class GameConfig {
    public static final String TITLE = "Obstacle avoid";

    public static final float WIDTH = 480f; // px
    public static final float HEIGHT = 800f; // px

    public static final float HUD_WIDTH = 480f; // world units
    public static final float HUD_HEIGHT = 800f; // world units

    public static final float WORLD_WIDTH = 6.f; // world units
    public static final float WORLD_HEIGHT = 10.f; // world units

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2.f; // world units
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2.f; // world units

    public static final float MAX_PLAYER_X_SPEED = 0.25f;
    public static final float OBSTACLE_SPAWN_TIME = 0.25f; // spawn obstacle every interval
    public static final float SCORE_MAX_TIME = 1.25f; // add score every interval

    public static final int LIVES_START = 3;

    private GameConfig() {}
}
