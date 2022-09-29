package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Shape2d;

import java.awt.*;

public class Drawer2d {
    private final Graphics g;

    public Drawer2d(Graphics g) {
        this.g = g;
    }

    public void draw(Shape2d s) {
        HomogeneousCoordinates2d first = null;
        HomogeneousCoordinates2d previous = null;
        for (HomogeneousCoordinates2d vertex : s.getVertexes()) {
            if (previous == null) {
                first = vertex;
            } else {
                g.drawLine((int) previous.getX(), (int) previous.getY(), (int) vertex.getX(), (int) vertex.getY());
            }
            previous = vertex;
        }
        g.drawLine((int) previous.getX(), (int) previous.getY(), (int) first.getX(), (int) first.getY());
    }
}
