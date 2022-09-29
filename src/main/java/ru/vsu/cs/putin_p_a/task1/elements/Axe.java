package ru.vsu.cs.putin_p_a.task1.elements;

import java.awt.*;

abstract public class Axe {
    private Color color;
    private final int length;

    public Axe(Color color, int length) {
        this.color = color;
        this.length = length;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    abstract public void draw(Graphics2D g);
}
