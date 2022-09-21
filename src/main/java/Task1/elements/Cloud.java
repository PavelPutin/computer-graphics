package Task1.elements;

import Task1.MathUtils;

import java.awt.*;

public class Cloud {
    private int x, y, width, height, layersNumber;
    private int[] radiusWidth, radiusHeight;
    private int randomShiftX, randomShiftY;

    public Cloud(int x, int y, int width, int height, int layersNumber) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layersNumber = layersNumber;

        randomShiftX = randomShiftY = 10;
        setDiameters();
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

        for (int i = 0; i < layersNumber; i++) {
            double step = Math.PI / (12 * (layersNumber - i));
            int initialPartRadius = width / layersNumber + i;

            int grayComponent = 255 - 5 * i;

            for (double angle = 0f; Math.abs(2 * Math.PI - angle) >= 1e-6; angle += step) {
                int x = (int) (radiusWidth[i] * Math.cos(angle) + this.x + width / 2);
                x += MathUtils.randInt(-randomShiftX, randomShiftX);
                int y = (int) (radiusHeight[i] * Math.sin(angle) + this.y + height / 2);
                y += MathUtils.randInt(-randomShiftY, randomShiftY);
                new Point(x, y, 5, Color.BLUE).draw(g);

                int realPartRadius = initialPartRadius + MathUtils.randInt(-5, 5);
                int realGrayComponent = Math.min(grayComponent + MathUtils.randInt(-3, 3), 255);
                g.setColor(new Color(realGrayComponent, realGrayComponent, realGrayComponent));
                g.fillOval(x - realPartRadius / 2, y - realPartRadius / 2, realPartRadius, realPartRadius);

                /*g.setColor(old);
                g.drawOval(x - realPartRadius / 2, y - realPartRadius / 2, realPartRadius, realPartRadius);*/
            }
        }

        g.setColor(old);
    }

    public void drawVerbose(Graphics2D g) {
        Color old = g.getColor();

        g.setColor(old);
    }
}
