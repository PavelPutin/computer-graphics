package Task1.elements;

import java.awt.*;

public class DrawUtils {
    public static void drawBoundary(Graphics2D g, int x, int y, int width, int height) {
        Color old = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(x, y, width, height);
        g.setColor(old);
    }
}
