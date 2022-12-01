package ru.vsu.cs.putin_p_a.task4.application.gui;

import javax.swing.*;

public class ComposerPanel extends JPanel {
    public ComposerPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageViewer imageViewer = new ImageViewer();

        JScrollPane imageViewScrollPane = new JScrollPane();

    }
}
