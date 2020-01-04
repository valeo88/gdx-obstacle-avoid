package com.mygdx.obstacleavoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.obstacleavoid.ObstacleAvoidGame;
import com.mygdx.obstacleavoid.config.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) GameConfig.WIDTH;
		config.height = (int) GameConfig.HEIGHT;
		// initial position of window
		config.x = 100;
		config.y = 0;
		new LwjglApplication(new ObstacleAvoidGame(), config);
	}
}
