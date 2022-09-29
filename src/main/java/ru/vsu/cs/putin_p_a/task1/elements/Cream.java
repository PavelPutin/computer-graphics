package ru.vsu.cs.putin_p_a.task1.elements;

import ru.vsu.cs.putin_p_a.task1.MathUtils;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Arrays;

public class Cream {
    private final int x;
    private int y;
    private final int pivotY;
    private final int width;
    private final int gap;
    private final int diameter;
    private final Color main;
    private final Color additional;

    private final int min;
    private final int max;
    private int[] curveX, curveY, pivotCurveY;

    public Cream(int x, int y, int width, int height, int diameter, int gap, Color main, Color additional) {
        this.x = x - gap;
        this.y = pivotY = y;
        this.width = width + gap * 2;
        this.diameter = diameter;
        this.gap = gap;
        this.main = main;
        this.additional = additional;

        min = diameter + 10;
        max = height - diameter - 4;

        getCreamParts();
    }

    private void getCreamParts() {
        final int creamParts;
        creamParts = 9;

        int step = width / creamParts;
        curveX = new int[2 * (creamParts + 1) - 1];
        curveY = new int[2 * (creamParts + 1) - 1];

        curveX[0] = 0;
        curveX[curveX.length - 1] = width;
        curveY[0] = MathUtils.randInt(min, max);
        curveY[curveX.length - 1] = MathUtils.randInt(min, max);

        for (int i = 1; i < creamParts; i++) {
            curveX[2 * i] = i * step;
            curveY[2 * i] = MathUtils.randInt(min, max);
        }

        int k = 0;
        for (int i = 1; i < curveX.length; i += 2) {
            curveX[i] = MathUtils.randInt(curveX[i - 1] + 5, curveX[i + 1] - 5);
            curveY[i] = MathUtils.randInt(Math.max(curveY[i - 1], curveY[i + 1]), max);
            if (k++ % 2 != 0) {
                curveY[i] = MathUtils.randInt(min, Math.min(curveY[i - 1], curveY[i + 1]));
            }
        }
        pivotCurveY = Arrays.copyOf(curveY, curveY.length);
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();
        g.setColor(additional);
        GeneralPath creamWave = new GeneralPath();
        creamWave.moveTo(x, y + (double) (diameter / 2));

        creamWave.lineTo(x + curveX[0], pivotY + curveY[0]);
        for (int i = 2; i < curveX.length; i += 2) {
            creamWave.curveTo(
                    x + curveX[i - 2], pivotY + curveY[i - 2],
                    x + curveX[i - 1], pivotY + curveY[i - 1],
                    x + curveX[i], pivotY + curveY[i]
            );
        }
        creamWave.lineTo(x + curveX[curveX.length - 1], pivotY + curveY[curveY.length - 1]);
        creamWave.lineTo(x + width, y + (double) (diameter / 2));

        g.fill(creamWave);
        g.fillRect(x, y + min, gap, pivotCurveY[0] - min);
        g.fillRect(x + width - gap, y + min, gap, pivotCurveY[pivotCurveY.length - 1] - min);

        g.setColor(main);
        g.fillOval(x, y, width, diameter);

        g.setColor(old);
    }

    public void drawVerbose(Graphics2D g) {
        Color old = g.getColor();
        for (int i = 0; i < curveX.length; i++) {
            new Point(x + curveX[i], pivotY + curveY[i], 5, Color.ORANGE).draw(g);
        }

        g.drawRect(x, y + min, gap, pivotCurveY[0] - min);
        g.drawRect(x + width - gap, y + min, gap, pivotCurveY[pivotCurveY.length - 1] - min);

        g.setColor(Color.GREEN);
        g.drawLine(x, y + min, x + width, y + min);
        g.setColor(Color.CYAN);
        g.drawLine(x, y + max, x + width, y + max);
        g.setColor(old);
    }

    public void update(double t) {
        double oscillation = Cake.YMAX * Math.sin(t / Cake.SLOWNESS_COEFFICIENT);
        y = (int) (pivotY + oscillation);
        for (int i = 0; i < curveY.length; i++) {
            curveY[i] = (int) (pivotCurveY[i] + oscillation);
        }
    }

}
