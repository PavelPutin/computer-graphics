package Task1;

import Task1.elements.CloudPart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private final DrawPanel dp;
    private final static int FPS = 24;
    private double t;

    public MainFrame() throws HeadlessException {
        dp = new DrawPanel();
        this.add(dp);

        Timer timer = new Timer(1000 / 24, e -> {
            t = (t + 1000f / 24) % Integer.MAX_VALUE;
            for (CloudPart part : dp.getCloud().getParts()) {
                part.update(t);
            }
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
