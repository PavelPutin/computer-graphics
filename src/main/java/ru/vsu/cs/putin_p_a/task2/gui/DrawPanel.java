package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Shape2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Triangle;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Drawer2d d = new Drawer2d(g);
        Shape2d triangle = new Triangle(
                new HomogeneousCoordinates2d(10, 20, 1),
                new HomogeneousCoordinates2d(10, 200, 1),
                new HomogeneousCoordinates2d(300, 20, 1)
        );
        triangle.setTransformOrigin(new HomogeneousCoordinates2d(10, 20, 1));
        d.draw(triangle);
    }
}
