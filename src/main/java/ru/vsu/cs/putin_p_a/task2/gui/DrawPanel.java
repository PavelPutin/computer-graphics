package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Rectangle;
import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Triangle;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.AffineTransformation;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Drawer2d d = new Drawer2d(g);
        Triangle rect = new Triangle(
                new HomogeneousCoordinates2d(10, 20, 1),
                new HomogeneousCoordinates2d(10, 200, 1),
                new HomogeneousCoordinates2d(300, 20, 1)
        );
        rect.setTransformOrigin(new HomogeneousCoordinates2d(10, 20, 1));
        d.draw(rect);
        AffineTransformation t = new AffineTransformation(
                new Matrix(new double[][] {
                        {1, 0, 0},
                        {0, 1, 0},
                        {0, 0, 2}
                })
        );
        AffineTransformation r = new AffineTransformation(
                new Matrix(new double[][] {
                        {0, -1, 0},
                        {1, 0, 0},
                        {0, 0, 1}
                })
        );
        AffineTransformation m = t.then(r);
        m.applyTo(rect);
        d.draw(rect);
    }
}
