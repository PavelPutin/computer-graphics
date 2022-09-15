package Task1.elements;

import java.awt.*;

abstract public class Axe {
    private Color color;
    private int length;

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

    public void setLength(int length) {
        this.length = length;
    }

    abstract public void draw(Graphics2D g);
}
