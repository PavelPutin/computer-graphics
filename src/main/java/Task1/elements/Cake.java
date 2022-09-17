package Task1.elements;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.concurrent.ThreadLocalRandom;

public class Cake {
    private int x, y, width, height;
    private boolean verbose;
    private int diameter;

    private final Color cakeColor = new Color(42, 11, 8);
    private final Color creamColor = new Color(218, 209, 210);
    private final Color additionalCreamColor = new Color(223, 214, 215);
    private int creamParts;
    private int min;
    private int max;

    public Cake(int x, int y, int width, int height, boolean verbose) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.verbose = verbose;

        diameter = width / 4;
        creamParts = 8;
        min = diameter + 4;
        max = height - diameter - 4;
    }

    public void draw(Graphics2D g) {
        Color oldColor = g.getColor();

        drawTins(g); // корж
        drawCream(g);

        if (verbose) {
            DrawUtils.drawBoundary(g, x, y, width, height);

            g.setColor(Color.GREEN);
            g.drawLine(x, y + min, x + width, y + min);
            g.setColor(Color.CYAN);
            g.drawLine(x, y + max, x + width, y + max);
            g.setColor(creamColor);

            g.setStroke(new BasicStroke(1));
            g.setColor(Color.BLACK);
            g.drawOval(x, y + height - diameter, width, diameter);
            g.drawRect(x, y + width / 8, width, height - diameter);
            g.drawOval(x, y, width, diameter);
        }

        g.setColor(oldColor);
    }

    private void drawTins(Graphics2D g) {
        Color oldColor = g.getColor();
        g.setColor(cakeColor);
        g.fillOval(x, y + height - diameter, width, diameter);
        g.fillRect(x, y + diameter / 2, width, height - diameter);
        g.fillOval(x, y, width, diameter);
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

}
