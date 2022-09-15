package Task1.elements;

import java.awt.*;

public class AxeX extends Axe {
    public AxeX(int length) {
        super(Color.BLUE, length);
    }

    @Override
    public void draw(Graphics2D g) {
        Color oldColor = g.getColor();
        g.setColor(getColor());

        g.drawLine(0, 0, getLength(), 0);

        g.setColor(oldColor);
    }
}
