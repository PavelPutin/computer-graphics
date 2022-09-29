package ru.vsu.cs.putin_p_a.task1.elements;

import java.awt.*;

public class Cake {
    public static final int YMAX = 10;
    public static final int SLOWNESS_COEFFICIENT = 500;
    public static final int CREAM_GAP = 5;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private final CakeTin tin;
    private final Cream cream;

    public Cake(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        int diameter = width / 4;
        Color cakeColor = new Color(42, 11, 8);
        tin = new CakeTin(x, y, width, height, diameter, cakeColor);
        Color creamColor = new Color(218, 209, 210);
        Color additionalCreamColor = new Color(223, 214, 215);
        cream = new Cream(x, y, width, height, diameter, CREAM_GAP, creamColor, additionalCreamColor);
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();

        tin.draw(g); // корж
        cream.draw(g);

        g.setColor(old);
    }

    public void drawVerbose(Graphics2D g) {
        Color old = g.getColor();
        DrawUtils.drawBoundary(g, x, y, width, height);
        tin.drawVerbose(g);
        cream.drawVerbose(g);
        g.setColor(old);
    }

    public void update(double t) {
        tin.update(t);
        cream.update(t);
    }
}
