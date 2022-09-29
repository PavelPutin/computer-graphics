package ru.vsu.cs.putin_p_a.task2.logic.shapes;

public class Triangle extends Shape2d {

    public Triangle(
            HomogeneousCoordinates2d point1,
            HomogeneousCoordinates2d point2,
            HomogeneousCoordinates2d point3
    ) {
        super();
        getVertexes().add(point1);
        getVertexes().add(point2);
        getVertexes().add(point3);
    }
}
