package ru.vsu.cs.putin_p_a.task2.gui;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, 100, 100);
    }
}
