package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer;

import java.awt.*;



public class WuLineAlgorithm implements LineDrawingAlgorithm{
    private final PixelSetter image;
    private Color color;

    public WuLineAlgorithm(PixelSetter image) {
        this.image = image;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        this.color = color;
        if (x2 < x1) {
            x1 += x2;
            x2 = x1 - x2;
            x1 -= x2;
            y1 += y2;
            y2 = y1 - y2;
            y1 -= y2;
        }
        int dx = x2 - x1;
        int dy = y2 - y1;
        //Горизонтальные и вертикальные линии не нуждаются в сглаживании
        if (dx == 0) {
            for (int y = y1; y < y2; y++) {
                drawPixel(x1, y);
            }
        } else if (dy == 0) {
            for (int x = x1; x < x2; x++) {
                drawPixel(x, y1);
            }
        }
        float gradient = 0;
        if (dx > dy) {
            gradient = (float) dy / dx;
            float intery = y1 + gradient;
            drawPixel(x1, y1);
            for (int x = x1; x < x2; ++x) {
                drawPixel(x, (int) intery, (float) (1 - fractionalPart(intery)));
                drawPixel(x, (int) intery + 1, (float) (fractionalPart(intery)));
                intery += gradient;
            }
            drawPixel(x2, y2);
        }
        else {
            gradient = (float) dx / dy;
            float interx = x1 + gradient;
            drawPixel(x1, y1);
            for (int y = y1; y < y2; ++y) {
                drawPixel((int)interx, y, (float) (1 - fractionalPart(interx)));
                drawPixel((int)interx + 1, y, (float) (fractionalPart(interx)));
                interx += gradient;
            }
            drawPixel(x2, y2);
        }


    }

    private boolean drawPixel(int x, int y, float intensity) {
        float[] initialHsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), initialHsb);

        float[] targetHsb = {initialHsb[0], initialHsb[1], intensity};
        if (image.containsPixel(x, y)) {
            image.setPixel(x, y, Color.getHSBColor(targetHsb[0], targetHsb[1], targetHsb[2]));
        }
        return image.containsPixel(x, y);
    }

    private boolean drawPixel(int x, int y) {
        if (image.containsPixel(x, y)) {
            image.setPixel(x, y, color);
        }
        return image.containsPixel(x, y);
    }

    private double fractionalPart(double value) {
        int tmp = (int) value;
        return value - tmp;
    }
}
