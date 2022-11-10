package ru.vsu.cs.putin_p_a.redactors_tasks.gui.redactor2d;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Line;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Shape2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class DrawPanel extends JPanel {
    private Shape2d target;
    private Shape2d targetPreview;
    private Point2d transformOriginPoint;

    public DrawPanel() {
        super();
        target = null;
        targetPreview = null;
        transformOriginPoint = null;
    }

    public void setTransformOriginPoint(Point2d transformOriginPoint) {
        this.transformOriginPoint = transformOriginPoint;
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

        AffineTransformation cs = new CoordinateSystem(new ReflectionY())
                .then(new Translation(BigDecimal.valueOf(widthHalf), BigDecimal.valueOf(heightHalf)));
        Drawer2d d = new Drawer2d(g, cs);

        Color old = g.getColor();

        g.setColor(Color.blue);
        d.draw(new Line(-widthHalf, 0, 1, widthHalf, 0, 1));
        g.setColor(Color.green);
        d.draw(new Line(0, -heightHalf, 1, 0, heightHalf, 1));

        g.setColor(old);

        if (target != null) {
            d.draw(target);
        }

        if (targetPreview != null) {
            old = g.getColor();
            g.setColor(Color.RED);
            d.draw(targetPreview);
            g.setColor(old);
        }

        if (transformOriginPoint != null) {
            old = g.getColor();
            g.setColor(Color.ORANGE);
            d.draw(transformOriginPoint);
            g.setColor(old);
        }
    }
}
