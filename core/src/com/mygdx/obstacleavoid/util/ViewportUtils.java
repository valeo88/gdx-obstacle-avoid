package com.mygdx.obstacleavoid.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ViewportUtils {
    private static final Logger logger = new Logger(ViewportUtils.class.getName(), Logger.DEBUG);

    private static final int DEFAULT_CELL_SIZE = 1;

    public static void drawGrid(Viewport viewport, ShapeRenderer renderer) {
        drawGrid(viewport, renderer, DEFAULT_CELL_SIZE);
    }

    public static void drawGrid(Viewport viewport, ShapeRenderer renderer, int cellSize) {
        if (viewport == null) throw new IllegalArgumentException("viewport required");
        if (renderer == null) throw new IllegalArgumentException("renderer required");
        if (cellSize < DEFAULT_CELL_SIZE) cellSize = DEFAULT_CELL_SIZE;

        Color oldColor = new Color(renderer.getColor());

        int worldWidth = (int) viewport.getWorldWidth();
        int worldHeight = (int) viewport.getWorldHeight();
        int doubleWorldWidth = worldWidth * 2;
        int doubleWorldHeight = worldHeight * 2;

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);

        // grid
        for (int x = -doubleWorldWidth; x < doubleWorldWidth; x += cellSize) {
            renderer.line(x, -doubleWorldHeight, x, doubleWorldHeight);
        }
        for (int y = -doubleWorldHeight; y < doubleWorldHeight; y += cellSize) {
            renderer.line(-doubleWorldWidth, y, doubleWorldWidth, y);
        }

        // axis
        renderer.setColor(Color.RED);
        renderer.line(-doubleWorldWidth, 0, doubleWorldWidth, 0);
        renderer.line(0, -doubleWorldHeight, 0, doubleWorldHeight);

        // bounds
        renderer.setColor(Color.GREEN);
        renderer.line(0, worldHeight, worldWidth, worldHeight);
        renderer.line(worldWidth, 0, worldWidth, worldHeight);

        renderer.end();
        renderer.setColor(oldColor);
    }

    public static void debugPixelPerUnit(Viewport viewport) {
        if (viewport == null) throw new IllegalArgumentException("viewport required");

        float xPPU = viewport.getScreenWidth() / viewport.getWorldWidth();
        float yPPU = viewport.getScreenHeight() / viewport.getWorldHeight();

        logger.debug("x PPU=" + xPPU + " y PPU=" + yPPU);
    }

    private ViewportUtils() {}

}
