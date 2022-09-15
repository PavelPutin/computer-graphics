package Task1;

import Task1.elements.ArcText;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.concurrent.ThreadLocalRandom;

public class DrawPanel extends JPanel {
    public DrawPanel() {
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;

        ArcText at = new ArcText("Happy birthday!", 50, 40, 400, 200, true);
        at.draw(g);
    }

}
