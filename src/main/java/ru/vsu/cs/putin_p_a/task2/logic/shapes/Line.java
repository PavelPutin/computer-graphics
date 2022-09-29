package ru.vsu.cs.putin_p_a.task2.logic.shapes;

public class Line extends Shape2d {
    public Line(
            double x1, double y1, double h1,
            double x2, double y2, double h2
    ) {
        super();
        addVertex(new HomogeneousCoordinates2d(x1, y1, h1));
        addVertex(new HomogeneousCoordinates2d(x2, y2, h2));
    }

    public Line(HomogeneousCoordinates2d point1, HomogeneousCoordinates2d point2) {
        super();
        addVertex(point1);
        addVertex(point2);
    }
}
