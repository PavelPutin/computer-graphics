package Task1.elements;

import java.awt.*;

public class AxeY extends Axe {
    public AxeY(int length) {
        super(Color.GREEN, length);
    }

    @Override
    public void draw(Graphics2D g) {
        Color oldColor = g.getColor();
        g.setColor(getColor());

        g.drawLine(0, 0, 0, getLength());

        g.setColor(oldColor);
    }
}
