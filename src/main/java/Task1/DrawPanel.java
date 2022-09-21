package Task1;

import Task1.elements.ArcText;
import Task1.elements.Cake;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    private final ArcText greeting;
    private final Cake cake;

    private boolean verbose;

    public DrawPanel() {
        int greetingX = 100;
        verbose = false;
        greeting = new ArcText("С днём рождения!", greetingX, 50, Main.WINDOW_WIDTH - 2 * greetingX, 50);
        cake = new Cake(Main.WINDOW_WIDTH / 4, 150, 400, 350);
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;

        Font oldFont = g.getFont();
        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 35));
        greeting.draw(g);
        g.setFont(oldFont);

        cake.draw(g);
        if (verbose) {
            greeting.drawVerbose(g);
            cake.drawVerbose(g);
        }
    }

    public void toggleVerbose() {
        verbose = !verbose;
    }
}
