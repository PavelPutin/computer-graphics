package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Shape2d;

import java.awt.Graphics;

public class Drawer2d {
    private final Graphics g;

    public Drawer2d(Graphics g) {
        this.g = g;
    }

    public void draw(Shape2d s) {
        System.out.println("Матрица фигуры перед рисованием");
        System.out.println(s.getMatrixVertexes());
        HomogeneousCoordinates2d first = null;
        HomogeneousCoordinates2d previous = null;
        for (HomogeneousCoordinates2d vertex : s.getVertexes()) {
            if (previous == null) {
                first = vertex;
            } else {
                System.out.printf("Рисую линию из (%d, %d) в (%d, %d)%n",
                        (int) previous.getX(), (int) previous.getY(), (int) vertex.getX(), (int) vertex.getY());
                g.drawLine((int) previous.getX(), (int) previous.getY(), (int) vertex.getX(), (int) vertex.getY());
            }
            previous = vertex;
        }
        System.out.printf("Рисую линию из (%d, %d) в (%d, %d)%n",
                (int) previous.getX(), (int) previous.getY(), (int) first.getX(), (int) first.getY());
        g.drawLine((int) previous.getX(), (int) previous.getY(), (int) first.getX(), (int) first.getY());
        System.out.println("Finish drawing");
    }
}
