package Task1;

import Task1.elements.ArcText;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.concurrent.ThreadLocalRandom;

public class DrawPanel extends JPanel {
    private final ArcText greeting;

    public DrawPanel() {
        int greetingX = 100;
        greeting = new ArcText("С днём рождения!", greetingX, 50, Main.WINDOW_WIDTH - 2 * greetingX, 50);
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;

        Font oldFont = g.getFont();
        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 35));
        greeting.draw(g);
        g.setFont(oldFont);

        drawCake(g, 100, 100, 500, 350, true);
    }

    private void drawCake(Graphics2D g, int x, int y, int width, int height, boolean verbose) {
        Color oldColor = g.getColor();

        if (verbose) {
            drawBoundary(g, x, y, width, height);
        }

        int diameter = width / 4;

        Color cakeColor = new Color(42, 11, 8);
        Color creamColor = new Color(218, 209, 210);

        g.setColor(cakeColor);
        g.fillOval(x, y + height - diameter, width, diameter);
        g.fillRect(x, y + width / 8, width, height - diameter);
        g.setColor(creamColor);
        g.fillOval(x, y, width, diameter);

        int n = 10;
        int step = width / n;
        GeneralPath creamWave = new GeneralPath();
        creamWave.moveTo(x, y + width / 8);
        int min = diameter + 4;
        int max = height - diameter - 4;

        int prevX = x;
        int prevY = y + randInt(min, max);

        creamWave.lineTo(prevX, prevY);
        for (int i = 1; i <= n; i++) {

            int tx = i * step + x;
            int px = randInt(prevX + 3, tx - 3);

            int ty = y + randInt(min, max);
            int py = randInt(Math.max(prevY, ty), y + max + 10);
            if (i % 2 != 0) {
                py = randInt(y + min - 10, Math.min(prevY, ty));
            }

            creamWave.curveTo(prevX, prevY, px, py, tx, ty);

            prevX = tx;
            prevY = ty;
        }
        creamWave.lineTo(x + width, y + width / 8);
        g.fill(creamWave);

        if (verbose) {
            g.setColor(Color.GREEN);
            g.drawLine(x, y + min, x + width, y + min);
            g.setColor(Color.CYAN);
            g.drawLine(x, y + max, x + width, y + max);
            g.setColor(creamColor);
        }

        if (verbose) {
            g.setStroke(new BasicStroke(1));
            g.setColor(Color.BLACK);
            g.drawOval(x, y + height - diameter, width, diameter);
            g.drawRect(x, y + width / 8, width, height - diameter);
            g.drawOval(x, y, width, diameter);
        }

        g.setColor(oldColor);
    }

    private void drawBoundary(Graphics2D g, int x, int y, int width, int height) {
        Color oldColor = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(x, y, width, height);
        g.setColor(oldColor);
    }

    private int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
