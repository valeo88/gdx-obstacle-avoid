package com.mygdx.obstacleavoid.entity;

import com.mygdx.obstacleavoid.config.GameConfig;

public class Player extends BaseGameObject {

    public Player() {
        super(GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }
}
