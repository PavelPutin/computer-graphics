package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.BresenhamLineAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.DrawerByPixel;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Path2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.ReflectionY;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.Scale;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.Translation;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class DrawPanel extends JPanel {
    private int DEFAULT_CANVAS_WIDTH = 800;
    private int DEFAULT_CANVAS_HEIGHT = 800;
    private int IMAGE_TYPE = 1;
    private JLabel imageContainer;
    private DrawerByPixel drawer;
    public DrawPanel() {
        super();
//        imageContainer = new JLabel();
//        image = new FunctionGraphic(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, IMAGE_TYPE);
//        graphicDrawer = new FunctionGraphicDrawer(
//                new Scale(BigDecimal.valueOf(40))
//                        .then(new ReflectionY())
//                        .then(new Translation(BigDecimal.valueOf(image.getWidth() / 2), BigDecimal.valueOf(image.getHeight() / 2)))
//        );
//        drawer = new DrawerByPixel(image, new BresenhamLineAlgorithm());
//        imageContainer.setIcon(new ImageIcon(image));
//        add(imageContainer);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        drawer.clear();
//        List<HomogeneousCoordinates2d> points = new ArrayList<>();
//        for (double x = 0; x <= 100; x += 0.5) {
//            points.add(new HomogeneousCoordinates2d(x, Math.sin(x), 1));
//        }
//
//        Path2d path = new Path2d(points);
//
//        drawer.setStroke(3);
//        graphicDrawer.drawGraph(drawer, path);
    }
}
