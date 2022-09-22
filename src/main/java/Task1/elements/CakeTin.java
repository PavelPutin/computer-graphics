package Task1.elements;

import java.awt.*;

public class CakeTin {
    private int x, y, pivotY, height, width, diameter;
    private final Color color;

    public CakeTin(int x, int y, int width, int height, int diameter, Color color) {
        this.x = x;
        this.y = pivotY = y;
        this.width = width;
        this.height = height;
        this.diameter = diameter;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();
        g.setColor(color);
        g.fillOval(x, y + height - diameter, width, diameter);
        g.fillRect(x, y + diameter / 2, width, height - diameter);
        g.fillOval(x, y, width, diameter);
        g.setColor(old);
    }

    public void drawVerbose(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawOval(x, y + height - diameter, width, diameter);
        g.drawRect(x, y + width / 8, width, height - diameter);
        g.drawOval(x, y, width, diameter);
    }

    public void update(double t) {
        this.y = (int) (pivotY + Cake.YMAX * Math.sin(t / Cake.SLOWNESS_COEFFICIENT));
    }
}
