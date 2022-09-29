package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Point2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Shape2d;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.AffineTransformation;

import java.awt.Graphics;

public class Drawer2d {
    private final Graphics g;
    private final AffineTransformation cs;

    public Drawer2d(Graphics g, AffineTransformation cs) {
        this.g = g;
        this.cs = cs;
    }

    public void draw(Shape2d s) {
        s.transform(cs);
        if (s instanceof Point2d) {
            Point2d p = (Point2d) s;
            g.fillOval((int) (p.getX() - p.getRadius()), (int) (p.getY() - p.getRadius()), 2 * p.getRadius(), 2 * p.getRadius());
        } else {
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
        s.transform(cs.getRedo());
    }
}
