package Task1.elements;

import java.awt.*;

public class ArcText {
    private String text;
    private int x, y, width, height;
    private double angleGap;
    private double startAngle;
    private double endAngle;
    private int[] txs, tys;
    private double[] thetas;

    public ArcText(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.startAngle = Math.PI / 12;
        this.endAngle = 11 * Math.PI / 12;

        txs = new int[text.length()];
        tys = new int[text.length()];
        thetas = new double[text.length()];

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

        for (int i = 0; i < text.length(); i++) {
            g.translate(txs[i] + x, tys[i] + y);
            g.rotate(thetas[i]);

            Axe axeX = new AxeX(25);
            axeX.draw(g);

            Axe axeY = new AxeY(25);
            axeY.draw(g);

            g.rotate(-thetas[i]);
            g.translate(-txs[i] - x, -tys[i] - y);
        }
        g.setColor(old);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        txs = new int[text.length()];
        tys = new int[text.length()];
        thetas = new double[text.length()];
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
        txs[i] = getTx(i);
        tys[i] = arcFormula(txs[i]);
        thetas[i] = getAngleForCharacter(txs[i]);

        g.translate(txs[i] + x, tys[i] + y);
        g.rotate(thetas[i]);

        g.drawString(String.valueOf(text.charAt(i)), 0, 0);

        g.rotate(-thetas[i]);
        g.translate(-txs[i] - x, -tys[i] - y);
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
