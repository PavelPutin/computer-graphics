package Task1.elements;

import java.awt.*;

public class ArcText {
    private String text;
    private int x, y, width, height;
    private double angleGap;
    private double startAngle;
    private double endAngle;

    public ArcText(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.startAngle = Math.PI / 12;
        this.endAngle = 11 * Math.PI / 12;

        getAngleGap();
    }

    private void getAngleGap() {
        angleGap = (endAngle - startAngle) / (text.length() - 1);
    }

    public void draw(Graphics2D g) {
        Color old = g.getColor();

        for (int i = 0; i < text.length(); i++) {
            drawCharAt(g, i);
        }

        g.setColor(old);
    }

    public void drawVerbose(Graphics2D g) {
        Color old = g.getColor();
        DrawUtils.drawBoundary(g, x, y, width, height);
        drawSupportiveArc(g);

        Axe axeX = new AxeX(g.getFont().getSize());
        axeX.draw(g);

        Axe axeY = new AxeY(g.getFont().getSize());
        axeY.draw(g);
        g.setColor(old);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        getAngleGap();
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        if (startAngle <= 0 || startAngle >= Math.PI) {
            throw new IllegalArgumentException("Недопустимое значение угла для текста-арки");
        }
        this.startAngle = startAngle;
    }

    public double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(double endAngle) {
        if (endAngle <= 0 || endAngle >= Math.PI) {
            throw new IllegalArgumentException("Недопустимое значение угла для текста-арки");
        }
        this.endAngle = endAngle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void drawCharAt(Graphics2D g, int i) {
        int tx = getTx(i);
        int ty = arcFormula(tx);
        double theta = getAngleForCharacter(tx);

        g.translate(tx + x, ty + y);
        g.rotate(theta);

        g.drawString(String.valueOf(text.charAt(i)), 0, 0);

        g.rotate(-theta);
        g.translate(-tx - x, -ty - y);
    }

    private int getTx(int i) {
        return (int) (width / 2 * -Math.cos(i * angleGap + startAngle) + width / 2);
    }

    private void drawSupportiveArc(Graphics2D g) {
        Color old = g.getColor();
        g.setColor(Color.CYAN);
        g.drawArc(x, y - height, width, 2 * height, 180, 180);
        g.setColor(old);
    }

    private int arcFormula(int x) {
        int w = width / 2;
        return (int) (height * Math.sqrt(Math.pow(w, 2) - Math.pow((x - w), 2)) / w);
    }

    private double getAngleForCharacter(int x) {
        double w = (double) width / 2;
        double h = height;

        double tan = -(h * (x - w)) / (w * Math.sqrt(Math.pow(w, 2) - Math.pow((x - w), 2)));
        return Math.atan(tan);
    }
}
