package ru.vsu.cs.putin_p_a.task1.elements;

import ru.vsu.cs.putin_p_a.task1.MathUtils;

import java.awt.*;

public class CloudPart {
    private final int x, pivotY, width, height;
    private int y;
    private final Color color;
    private final int ymax, fasa0;

    public CloudPart(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = this.pivotY = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.ymax = MathUtils.randInt(5, 15);
        this.fasa0 = MathUtils.randInt(0, 20);
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(old);
    }

    public void update(double t) {
        this.y = (int) (pivotY + ymax * Math.sin(t / 1000 + fasa0));
    }
}
