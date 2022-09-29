package ru.vsu.cs.putin_p_a.task2.logic;

public class Rectangle extends Shape2d {
    public Rectangle(double x, double y, double h, double width, double height) {
        super();
        this.getVertexes().add(new HomogeneousCoordinates2d(x, y, h));
        this.getVertexes().add(new HomogeneousCoordinates2d(x + width, y, h));
        this.getVertexes().add(new HomogeneousCoordinates2d(x + width, y + height, h));
        this.getVertexes().add(new HomogeneousCoordinates2d(x, y + height, h));
    }
}
