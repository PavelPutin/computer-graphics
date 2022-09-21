package Task1.elements;

import java.awt.*;

public class Cake {
    private int x, y, width, height, diameter;

    private CakeTin tin;
    private Cream cream;

    private final Color cakeColor = new Color(42, 11, 8);
    private final Color creamColor = new Color(218, 209, 210);
    private final Color additionalCreamColor = new Color(223, 214, 215);

    public Cake(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        diameter = width / 4;
        tin = new CakeTin(x, y, width, height, diameter, cakeColor);
        cream = new Cream(x, y, width, height, diameter, creamColor, additionalCreamColor);
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
