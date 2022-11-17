package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer;

import java.awt.*;

public class BresenhamLineAlgorithm implements LineDrawingAlgorithm{
    private final PixelSetter image;
    private Color color;

    public BresenhamLineAlgorithm(PixelSetter image) {
        this.image = image;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        this.color = color;

        int deltaX = Math.abs(x2 - x1);
        int deltaY = Math.abs(y2 - y1);
        int error = 0;
        int deltaError = Math.min(deltaY, deltaX) + 1;
        int startX = x1, startY = y1,
                endX = x2, endY = y2;

        if (deltaY <= deltaX && startX >= endX || deltaY > deltaX && startY >= endY) {
            startX = x2;
            startY = y2;
            endX = x1;
            endY = y1;
        }

        if (deltaY <= deltaX) {
            int y = startY;
            int directionY = startY < endY ? 1 : -1;

            for (int x = startX; x < endX; x++) {
                drawPixel(x, y);
                error += deltaError;
                if (error >= deltaX + 1) {
                    y += directionY;
                    error -= deltaX + 1;
                }
            }
        } else {
            int x = startX;
            int directionX = startX < endX ? 1 : -1;

            for (int y = startY; y <= endY; y++) {
                drawPixel(x, y);
                error += deltaError;
                if (error >= deltaY + 1) {
                    x += directionX;
                    error -= deltaY + 1;
                }
            }
        }
    }

    private boolean drawPixel(int x, int y) {
        if (image.containsPixel(x, y)) {
            image.setPixel(x, y, color);
        }
        return image.containsPixel(x, y);
    }
}
