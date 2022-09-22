package Task1.elements;

import Task1.Main;

import java.awt.*;

public class Background {
    private int x, y, width, height;

    public Background() {
        x = 0;
        y = 0;
        width = Main.WINDOW_WIDTH;
        height = Main.WINDOW_HEIGHT;
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();

        g.setPaint(new LinearGradientPaint(x, y, width, height,
                new float[] {0.33f, 0.66f, 1.0f},
                new Color[] {new Color(0x7F7FD5), new Color(0x86A8E7), new Color(0x91EAE4)}));
        g.fillRect(x, y, width, height);

        g.setColor(old);
    }
}
