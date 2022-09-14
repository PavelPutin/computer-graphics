package Task1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.concurrent.ThreadLocalRandom;

public class DrawPanel extends JPanel {
    public DrawPanel() {
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;

        String text = "Text";
        drawText(g, text, 50, 40, 400, 200, true);
    }

    private void drawText(Graphics2D g, String text, int x, int y, int width, int height, boolean verbose) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        if (verbose) {
            g.setColor(Color.RED);
            g.drawRect(x, y, width, height);
            g.setColor(oldColor);

            g.setColor(Color.CYAN);
            g.drawArc(x, y - height, width, 2 * height, 180, 180);
            g.setColor(oldColor);
        }

        g.setFont(new Font("Tahoma", Font.PLAIN, 50));
        double angleGap = Math.PI / text.length();

        for (int i = 0; i < text.length(); i++) {
            int tx = (int) (width / 2 * -Math.cos(i * angleGap) + width / 2);
            if (text.length() % 2 == 0 && i >= text.length() / 2) {
                tx = (int) (width / 2 * -Math.cos((i + 1) * angleGap) + width / 2);
            }
            int ty = arcFormula(tx, width, height);
            g.translate(tx + x, ty + y);

            double theta = angle(tx, width, height);
            g.rotate(theta);

            if (verbose) {
                g.setColor(Color.BLUE);
                g.drawLine(0, 0, g.getFont().getSize(), 0);

                g.setColor(Color.GREEN);
                g.drawLine(0, 0, 0, g.getFont().getSize());
                g.setColor(oldColor);
            }

            g.drawString(String.valueOf(text.charAt(i)), 0, 0);
            g.rotate(-theta);

            g.translate(-tx - x, -ty - y);
        }

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private int arcFormula(int x, int width, int height) {
        int widthRadius = width / 2;
        return (int) (height * Math.sqrt(Math.pow(widthRadius, 2) - Math.pow((x - widthRadius), 2)) / widthRadius);
    }

    private double angle(int x, int width, int height) {
        double w = (double) width / 2;
        double h = height;

        double tan = -(h * (x - w)) / (w * Math.sqrt(Math.pow(w, 2) - Math.pow((x - w), 2)));
        return Math.atan(tan);
    }
}
