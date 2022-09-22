package Task1;

import Task1.elements.Cloud;
import Task1.elements.CloudPart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private final DrawPanel dp;
    private final static int FPS = 24;
    private double t;

    public MainFrame() throws HeadlessException {
        dp = new DrawPanel();
        this.add(dp);
        int delay = 1000 / FPS;
        Timer timer = new Timer(delay, e -> {
            t = (t + delay) % Integer.MAX_VALUE;
            for (Cloud cloud : dp.getClouds()) {
                for (CloudPart part : cloud.getParts()) {
                    part.update(t);
                }
            }
            dp.getCake().update(t);
            dp.repaint();
        });
        timer.start();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_V) {
                    dp.toggleVerbose();
                    dp.repaint();
                }
            }
        });
    }
}
