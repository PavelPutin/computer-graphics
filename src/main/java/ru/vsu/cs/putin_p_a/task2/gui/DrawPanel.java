package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.*;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Rectangle;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int widthHalf = getSize().width / 2,
                heightHalf = getSize().height / 2;
        CoordinateSystem cs = new CoordinateSystem(
                new Rotation(Math.PI)
                        .then(new ReflectionX())
                        .then(new Translation(widthHalf, heightHalf))
        );
        Drawer2d d = new Drawer2d(g, cs);

        Color old = g.getColor();

        g.setColor(Color.blue);
        d.draw(new Line(-widthHalf, 0, 1, widthHalf, 0, 1));
        g.setColor(Color.green);
        d.draw(new Line(0, -heightHalf, 1, 0, heightHalf, 1));

        g.setColor(old);

        Rectangle rect = new Rectangle(0, 0, 1, 100, 200);
        rect.transform(new Rotation(Math.PI / 12));
        d.draw(rect);
    }
}
