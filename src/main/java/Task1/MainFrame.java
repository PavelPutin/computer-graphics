package Task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private final DrawPanel dp;

    public MainFrame() throws HeadlessException {
        dp = new DrawPanel();
        this.add(dp);

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
