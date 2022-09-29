package ru.vsu.cs.putin_p_a.task2.logic.shapes;

public class Rectangle extends Shape2d {
    public Rectangle(double x, double y, double h, double width, double height) {
        super();
        addVertex(new HomogeneousCoordinates2d(x, y, h));
        addVertex(new HomogeneousCoordinates2d(x + width, y, h));
        addVertex(new HomogeneousCoordinates2d(x + width, y + height, h));
        addVertex(new HomogeneousCoordinates2d(x, y + height, h));
    }


}
