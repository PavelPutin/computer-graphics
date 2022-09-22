package Task1.elements;

import java.awt.*;

public class Cake {
    public static final int YMAX = 10;
    public static final int SLOWNESS_COEFFICIENT = 500;
    public static final int CREAM_GAP = 5;
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
