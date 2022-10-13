package ru.vsu.cs.putin_p_a.redactors_tasks.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes.Point2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes.Shape2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.AffineTransformation;

import java.awt.Graphics;
import java.math.BigDecimal;

public class Drawer2d {
    private final Graphics g;
    private final AffineTransformation cs;

    public Drawer2d(Graphics g, AffineTransformation cs) {
        this.g = g;
        this.cs = cs;
    }

    public void draw(Shape2d s) {
        s.transform(cs);
        if (s instanceof Point2d p) {
            g.fillOval(
                    p.getX().subtract(p.getRadius()).intValue(),
                    p.getY().subtract(p.getRadius()).intValue(),
                    p.getRadius().multiply(BigDecimal.valueOf(2)).intValue(),
                    p.getRadius().multiply(BigDecimal.valueOf(2)).intValue()
            );
        } else {
            HomogeneousCoordinates2d first = null;
            HomogeneousCoordinates2d previous = null;

            for (HomogeneousCoordinates2d vertex : s.getVertexes()) {
                if (previous == null) {
                    first = vertex;
                } else {
                    g.drawLine(previous.getX().intValue(), previous.getY().intValue(), vertex.getX().intValue(), vertex.getY().intValue());
                }
                previous = vertex;
            }
            g.drawLine(previous.getX().intValue(), previous.getY().intValue(), first.getX().intValue(), first.getY().intValue());
        }
        s.transform(cs.getRedo());
    }
}
