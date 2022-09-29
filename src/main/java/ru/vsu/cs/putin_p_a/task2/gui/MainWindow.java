package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.Redactor;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static final Dimension INITIAL = new Dimension(800, 600);
    private final Redactor redactor;

    public MainWindow() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(INITIAL);
        this.setVisible(true);

        this.redactor = new Redactor();

        DrawPanel drawPanel = new DrawPanel();
        JScrollPane drawScrollPane = new JScrollPane();
        drawScrollPane.setViewportView(drawPanel);

        JPanel transformSettingsPanel = new JPanel();
        JScrollPane settingsScrollPane = new JScrollPane();
        settingsScrollPane.setViewportView(transformSettingsPanel);

        String[] availableShapes = redactor.getAvailableShapes().keySet().toArray(new String[0]);
        JComboBox availableShapesList = new JComboBox(availableShapes);
        availableShapesList.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            String selected = (String) cb.getSelectedItem();
            redactor.setCurrent(selected);
            drawPanel.setTarget(redactor.getCurrent());
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });
        transformSettingsPanel.add(availableShapesList);

        Dimension minimumSize = new Dimension(200, 350);
        drawScrollPane.setMinimumSize(minimumSize);
        settingsScrollPane.setMinimumSize(minimumSize);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, drawScrollPane, settingsScrollPane);
        splitPane.setDividerLocation(INITIAL.width - 200);
        this.add(splitPane);

        this.pack();
    }
}
