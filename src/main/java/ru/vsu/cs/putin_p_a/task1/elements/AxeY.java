package ru.vsu.cs.putin_p_a.task1.elements;

import java.awt.*;

public class AxeY extends Axe {
    public AxeY(int length) {
        super(Color.GREEN, length);
    }

    @Override
    public void draw(Graphics2D g) {
        Color old = g.getColor();
        g.setColor(getColor());

        g.drawLine(0, 0, 0, getLength());

        g.setColor(old);
    }
}
