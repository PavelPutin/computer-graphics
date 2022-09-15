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

        g.setFont(new Font("Tahoma", Font.BOLD, 45));
        ArcText at = new ArcText("Happy birthday!", 50, 40, 400, 200);
        at.draw(g);

        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 35));
        ArcText at1 = new ArcText("С днём рождения!", 50, 200, 600, 50);
        at1.draw(g);
    }

}
