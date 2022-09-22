package Task1;

import Task1.elements.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    private Background background;
    private ArcText greeting;
    private Cake cake;
    private java.util.List<Cloud> clouds;

    private boolean verbose;

    public DrawPanel() {
        verbose = false;

        background = new Background();

        int greetingX = 100;
        greeting = new ArcText("С днём рождения!", greetingX, 50, Main.WINDOW_WIDTH - 2 * greetingX, 50);

        int width = 400,
                height = 350;
        int x = Main.WINDOW_WIDTH / 2 - width / 2;
        int y = Main.WINDOW_HEIGHT / 2 - height / 2;
        cake = new Cake(x, y, width, height);

        clouds = new ArrayList<>();
        int bigCloudWidth = width + 400, bigCloudHeight = height / 2;
        clouds.add(new Cloud(
                Main.WINDOW_WIDTH / 2 - bigCloudWidth / 2,
                Main.WINDOW_HEIGHT / 2 - bigCloudHeight / 2 + height / 3,
                bigCloudWidth,
                bigCloudHeight,
                6));

        int smallCloudWidth = width + 50, smallCloudHeight = height / 4;
        clouds.add(new Cloud(
                Main.WINDOW_WIDTH / 2 - smallCloudWidth / 2,
                Main.WINDOW_HEIGHT / 2 - smallCloudHeight / 2 + height / 3 + 25,
                smallCloudWidth,
                smallCloudHeight,
                10));
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;

        background.draw(g);
        Color old = g.getColor();
        Font oldFont = g.getFont();
        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 35));
        g.setPaint(new LinearGradientPaint(0, 0, 35, 20,
                new float[] {0.5f, 1.0f},
                new Color[] {new Color(0xFDC830), new Color(0xF37335)}
        ));
        greeting.draw(g);
        g.setColor(old);
        g.setFont(oldFont);

        clouds.get(0).draw(g);
        cake.draw(g);
        clouds.get(1).draw(g);

        if (verbose) {
            greeting.drawVerbose(g);
            for (Cloud cloud : clouds) {
                cloud.drawVerbose(g);
            }
            cake.drawVerbose(g);
        }
    }

    public void toggleVerbose() {
        verbose = !verbose;
    }

    public List<Cloud> getClouds() {
        return clouds;
    }

    public Cake getCake() {
        return cake;
    }
}
