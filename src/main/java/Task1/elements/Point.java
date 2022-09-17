package Task1.elements;

import java.awt.*;

public class Point {
    private int x, y, diameter;
    private Color color;

    public Point(int x, int y, int diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
        g.setColor(old);
    }
}
