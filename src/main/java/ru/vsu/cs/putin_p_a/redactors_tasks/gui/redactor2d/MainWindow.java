package ru.vsu.cs.putin_p_a.redactors_tasks.gui.redactor2d;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JPanel mainPanel;
    public MainWindow() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        mainPanel = new JPanel();
        this.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel redactor = new Redactor2d();
        mainPanel.add(redactor);
        this.pack();
    }
}
