package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.BresenhamLineAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.DrawerByPixel;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.EmptyTransformation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public final class MiniDesmos extends JFrame {
    public MiniDesmos() throws HeadlessException {
        super();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        add(mainPanel);

        JPanel control = new JPanel();
        control.add(new JLabel("Hello"));
        mainPanel.add(control);

        JPanel dp = new DrawPanel();
        dp.setBorder(new LineBorder(Color.CYAN));
        dp.setPreferredSize(new Dimension(1200, 700));
        mainPanel.add(dp);
        pack();
    }
}
