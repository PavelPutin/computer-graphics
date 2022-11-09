package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.AffineTransformation;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
* Работает в системе монитора*/
public class DrawerByPixel {
    private static final int DEFAULT_STROKE = 1;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private FunctionGraphic image;
    private LineDrawingAlgorithm lineDrawingAlgorithm;
    private int stroke;
    private Color color;

    public DrawerByPixel(FunctionGraphic image, LineDrawingAlgorithm lineDrawingAlgorithm) {
        this.image = image;
        this.lineDrawingAlgorithm = lineDrawingAlgorithm;
        stroke = DEFAULT_STROKE;
        color = DEFAULT_COLOR;
    }

    public void setLineDrawingAlgorithm(LineDrawingAlgorithm lineDrawingAlgorithm) {
        this.lineDrawingAlgorithm = lineDrawingAlgorithm;
    }

    public void clear() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, Color.white.getRGB());
            }
        }
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        lineDrawingAlgorithm.drawLine(image, stroke, x1, y1, x2, y2, color);
    }
}
