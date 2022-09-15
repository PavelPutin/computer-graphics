package Task1.elements;

import java.awt.*;

public class ArcText {
    private String text;
    private int x, y, width, height;
    private boolean verbose;

    public ArcText(String text, int x, int y, int width, int height, boolean verbose) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.verbose = verbose;
    }

    public ArcText(String text, int x, int y, int width, int height) {
        this(text, x, y, width, height, false);
    }

    public void draw(Graphics2D g) {
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
        double angleGap = Math.PI / (text.length() - 1);

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
