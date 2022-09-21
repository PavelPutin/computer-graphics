package Task1.elements;

import java.awt.*;

public class CloudPart {
    private int x, y, width, height;
    private Color color;

    public CloudPart(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(old);
    }
}
