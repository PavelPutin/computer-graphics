package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface LineDrawingAlgorithm {
    void drawLine(Canvas image, int stroke, int x1, int y1, int x2, int y2, Color color);
}
