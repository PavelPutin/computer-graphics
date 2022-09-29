package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.Line;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Shape2d;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.*;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    private Shape2d target;
    private Shape2d targetPreview;

    public DrawPanel() {
        super();
        target = null;
        targetPreview = null;
    }

    public void setTarget(Shape2d target) {
        this.target = target;
    }

    public void setTargetPreview(Shape2d targetPreview) {
        this.targetPreview = targetPreview;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int widthHalf = getSize().width / 2,
                heightHalf = getSize().height / 2;

        AffineTransformation cs = new CoordinateSystem(new Rotation(Math.PI)
                .then(new ReflectionX())
                .then(new Translation(widthHalf, heightHalf)));
        Drawer2d d = new Drawer2d(g, cs);

        Color old = g.getColor();

        g.setColor(Color.blue);
        d.draw(new Line(-widthHalf, 0, 1, widthHalf, 0, 1));
        g.setColor(Color.green);
        d.draw(new Line(0, -heightHalf, 1, 0, heightHalf, 1));

        g.setColor(old);

        if (target != null) {
            d.draw(target);
            System.out.println(target.getMatrixVertexes());
        }

        if (targetPreview != null) {
            d.draw(targetPreview);
        }
    }
}
