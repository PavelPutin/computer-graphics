package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Shape2d;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.CoordinateSystem;

import java.awt.*;

public class Drawer2d {
    private final Graphics g;
    private final CoordinateSystem cs;

    public Drawer2d(Graphics g, CoordinateSystem cs) {
        this.g = g;
        this.cs = cs;
    }

    public void draw(Shape2d s) {
        HomogeneousCoordinates2d first = null;
        HomogeneousCoordinates2d previous = null;
        s.transform(cs);
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
