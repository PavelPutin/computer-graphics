package Task1;

import Task1.elements.ArcText;
import Task1.elements.Cake;
import Task1.elements.Cloud;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    private ArcText greeting;
    private Cake cake;
    private Cloud cloud;

    private boolean verbose;

    public DrawPanel() {
        verbose = false;

        int greetingX = 100;
        greeting = new ArcText("С днём рождения!", greetingX, 50, Main.WINDOW_WIDTH - 2 * greetingX, 50);

        int width = 400,
                height = 350;
        int x = Main.WINDOW_WIDTH / 2 - width / 2;
        int y = Main.WINDOW_HEIGHT / 2 - height / 2;
        cake = new Cake(x, y, width, height);

        int cloudWidth = width + 400, cloudHeight = height / 2;
        cloud = new Cloud(
                Main.WINDOW_WIDTH / 2 - cloudWidth / 2,
                Main.WINDOW_HEIGHT / 2 - cloudHeight / 2 + height / 3,
                cloudWidth,
                cloudHeight,
                6);
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;

        Font oldFont = g.getFont();
        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 35));
        greeting.draw(g);
        g.setFont(oldFont);

        cloud.draw(g);
        cake.draw(g);

        if (verbose) {
            greeting.drawVerbose(g);
            cloud.drawVerbose(g);
            cake.drawVerbose(g);
        }
    }

    public void toggleVerbose() {
        verbose = !verbose;
    }

    public Cloud getCloud() {
        return cloud;
    }
}
