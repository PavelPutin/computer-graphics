package ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d;

import java.math.BigDecimal;

public class Point2d extends Shape2d {
    private BigDecimal radius;

    public Point2d(double x, double y, double h, double radius) {
        super();
        addVertex(new HomogeneousCoordinates2d(x, y, h));
        this.radius = new BigDecimal(radius);
    }

    public Point2d(BigDecimal x, BigDecimal y, BigDecimal h, BigDecimal radius) {
        super();
        addVertex(new HomogeneousCoordinates2d(x, y, h));
        this.radius = radius;
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public BigDecimal getX() {
        return getVertexes().get(0).getX();
    }

    public BigDecimal getY() {
        return getVertexes().get(0).getY();
    }
}
