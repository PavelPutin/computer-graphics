package Task1.elements;

import Task1.MathUtils;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Arrays;

public class Cream {
    private int x, y, pivotY, width, height, diameter;
    private boolean verbose;
    private Color main, additional;

    private final int creamParts, min, max;
    private int[] curveX, curveY, pivotCurveY;

    public Cream(int x, int y, int width, int height, int diameter, Color main, Color additional) {
        this.x = x;
        this.y = pivotY = y;
        this.width = width;
        this.height = height;
        this.diameter = diameter;
        this.verbose = verbose;
        this.main = main;
        this.additional = additional;

        creamParts = 8;
        min = diameter + 4;
        max = height - diameter - 4;

        int step = width / creamParts;
        curveX = new int[2 * (creamParts + 1) - 1];
        curveY = new int[2 * (creamParts + 1) - 1];

        curveX[0] = x;
        curveX[curveX.length - 1] = x + width;
        curveY[0] = y + MathUtils.randInt(min, max);
        curveY[curveX.length - 1] = y + MathUtils.randInt(min, max);

        for (int i = 1; i < creamParts; i++) {
            curveX[2 * i] = i * step + x;
            curveY[2 * i] = y + MathUtils.randInt(min, max);
        }

        int k = 0;
        for (int i = 1; i < curveX.length; i += 2) {
            curveX[i] = MathUtils.randInt(curveX[i - 1] + 5, curveX[i + 1] - 5);
            curveY[i] = MathUtils.randInt(Math.max(curveY[i - 1], curveY[i + 1]), y + max);
            if (k++ % 2 != 0) {
                curveY[i] = MathUtils.randInt(y + min, Math.min(curveY[i - 1], curveY[i + 1]));
            }
        }
        pivotCurveY = Arrays.copyOf(curveY, curveY.length);
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();
        g.setColor(additional);
        GeneralPath creamWave = new GeneralPath();
        creamWave.moveTo(x, y + diameter / 2);
        


        creamWave.lineTo(curveX[0], curveY[0]);
        for (int i = 2; i < curveX.length; i += 2) {
            creamWave.curveTo(
                    curveX[i - 2], curveY[i - 2],
                    curveX[i - 1], curveY[i - 1],
                    curveX[i], curveY[i]
            );
        }
        creamWave.lineTo(curveX[curveX.length - 1], curveY[curveY.length - 1]);
        creamWave.lineTo(x + width, y + diameter / 2);

        g.fill(creamWave);

        g.setColor(main);
        g.fillOval(x, y, width, diameter);

        g.setColor(old);
    }

    public void drawVerbose(Graphics2D g) {
        Color old = g.getColor();
        for (int i = 0; i < curveX.length; i++) {
            new Point(curveX[i], curveY[i], 5, Color.ORANGE).draw(g);
        }

        g.setColor(Color.GREEN);
        g.drawLine(x, y + min, x + width, y + min);
        g.setColor(Color.CYAN);
        g.drawLine(x, y + max, x + width, y + max);
        g.setColor(old);
    }

    public void update(double t) {
        double oscillation = Cake.YMAX * Math.sin(t / Cake.SLOWNESS_COEFFICIENT);
        this.y = (int) (pivotY + oscillation);
        for (int i = 0; i < curveY.length; i++) {
            curveY[i] = (int) (pivotCurveY[i] + oscillation);
        }
    }

}