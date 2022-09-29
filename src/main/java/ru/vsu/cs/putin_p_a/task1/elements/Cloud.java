package ru.vsu.cs.putin_p_a.task1.elements;

import ru.vsu.cs.putin_p_a.task1.MathUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cloud {
    private final int x, y, width, height, layersNumber;
    private int[] radiusWidth, radiusHeight;
    private final java.util.List<CloudPart> parts;

    public Cloud(int x, int y, int width, int height, int layersNumber) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layersNumber = layersNumber;

        parts = new ArrayList<>();
        int randomShiftY;
        int randomShiftX = randomShiftY = 15;
        setDiameters();

        for (int i = 0; i < layersNumber; i++) {
            double step = Math.PI / (12 * (layersNumber - i));
            int initialPartRadius = width / layersNumber + i;

            int grayComponent = 255;

            for (double angle = 0f; Math.abs(2 * Math.PI - angle) >= 1e-6; angle += step) {
                int partX = (int) (radiusWidth[i] * Math.cos(angle) + this.x + width / 2);
                partX += MathUtils.randInt(-randomShiftX, randomShiftX);
                int partY = (int) (radiusHeight[i] * Math.sin(angle) + this.y + height / 2);
                partY += MathUtils.randInt(-randomShiftY, randomShiftY);

                int realPartRadius = initialPartRadius + MathUtils.randInt(-5, 5);
                if (Math.PI <= angle && angle <= 2 * Math.PI) {
                    realPartRadius = initialPartRadius - 10 + MathUtils.randInt(-5, 5);
                }
                int realGrayComponent = Math.min(grayComponent + MathUtils.randInt(-5, 0), 255);

                Color partColor = new Color(realGrayComponent, realGrayComponent, realGrayComponent);
                parts.add(new CloudPart(partX - realPartRadius / 2, partY - realPartRadius / 2, realPartRadius, realPartRadius, partColor));
            }
        }
    }

    private void setDiameters() {
        radiusWidth = new int[layersNumber];
        radiusHeight = new int[layersNumber];
        for (int i = 0; i < layersNumber; i++) {
            radiusWidth[i] = width / 2 - width * i / 2 / layersNumber;
            radiusHeight[i] = height / 2 - height * i / 2 / layersNumber;
        }
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();

        for (CloudPart cp : parts) {
            cp.draw(g);
        }

        g.setColor(old);
    }

    public void drawVerbose(Graphics2D g) {
        Color old = g.getColor();
        for (int i = 0; i < layersNumber; i++) {
            g.drawOval(this.x + width / 2 - radiusWidth[i], this.y + height / 2 - radiusHeight[i], 2 * radiusWidth[i], 2 * radiusHeight[i]);
        }
        g.setColor(old);
    }

    public List<CloudPart> getParts() {
        return parts;
    }
}
