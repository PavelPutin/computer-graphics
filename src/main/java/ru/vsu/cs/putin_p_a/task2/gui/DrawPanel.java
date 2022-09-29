package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.Rectangle;
import ru.vsu.cs.putin_p_a.task2.logic.Triangle;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Drawer2d d = new Drawer2d(g);
        d.draw(new Rectangle(100, 20, 1, 200, 350));
        Triangle t = new Triangle(
                new HomogeneousCoordinates2d(10, 20, 1),
                new HomogeneousCoordinates2d(150, 40, 1),
                new HomogeneousCoordinates2d(20, 200, 1)
        );
        d.draw(t);
    }
}
