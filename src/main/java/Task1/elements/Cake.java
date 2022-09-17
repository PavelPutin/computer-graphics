package Task1.elements;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.TileObserver;
import java.util.concurrent.ThreadLocalRandom;

public class Cake {
    private int x, y, width, height, diameter;
    private boolean verbose;

    private CakeTin tin;

    private final Color cakeColor = new Color(42, 11, 8);
    private final Color creamColor = new Color(218, 209, 210);
    private final Color additionalCreamColor = new Color(223, 214, 215);
    private int creamParts;
    private int min;
    private int max;

    public Cake(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        verbose = false;
        diameter = width / 4;
        tin = new CakeTin(x, y, width, height, diameter, cakeColor);

        creamParts = 8;
        min = diameter + 4;
        max = height - diameter - 4;
    }

    public void draw(Graphics2D g) {
        Color oldColor = g.getColor();

        tin.draw(g); // корж
        drawCream(g);

        if (verbose) {
            DrawUtils.drawBoundary(g, x, y, width, height);

            g.setColor(Color.GREEN);
            g.drawLine(x, y + min, x + width, y + min);
            g.setColor(Color.CYAN);
            g.drawLine(x, y + max, x + width, y + max);
            g.setColor(creamColor);

        }

        g.setColor(oldColor);
    }

    private void drawCream(Graphics2D g) {
        Color oldColor = g.getColor();
        g.setColor(additionalCreamColor);
        int step = width / creamParts;
        GeneralPath creamWave = new GeneralPath();
        creamWave.moveTo(x, y + diameter / 2);

        int prevX = x;
        int prevY = y + randInt(min, max);

        creamWave.lineTo(prevX, prevY);
        for (int i = 1; i <= creamParts; i++) {

            int tx = i == creamParts ? x + width : i * step + x;
            int px = randInt(prevX + 5, tx - 5);

            int ty = y + randInt(min, max);
            int py = randInt(Math.max(prevY, ty), y + max + 10);
            if (i % 2 != 0) {
                py = randInt(y + min - 10, Math.min(prevY, ty));
            }

            creamWave.curveTo(prevX, prevY, px, py, tx, ty);

            prevX = tx;
            prevY = ty;
        }

        creamWave.lineTo(x + width, y + diameter / 2);
        g.fill(creamWave);

        g.setColor(creamColor);
        g.fillOval(x, y, width, diameter);
        g.setColor(oldColor);
    }

    private int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
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

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

}
