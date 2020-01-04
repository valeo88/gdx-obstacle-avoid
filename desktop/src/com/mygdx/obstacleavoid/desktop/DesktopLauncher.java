package com.mygdx.obstacleavoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.obstacleavoid.ObstacleAvoidGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		setInitialPosition(config);
		new LwjglApplication(new ObstacleAvoidGame(), config);
	}

	private static void setInitialPosition(LwjglApplicationConfiguration config) {
		config.x = 100;
		config.y = 100;
	}
}
