package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Rectangle;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Shape2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Triangle;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Drawer2d d = new Drawer2d(g);
    }
}
