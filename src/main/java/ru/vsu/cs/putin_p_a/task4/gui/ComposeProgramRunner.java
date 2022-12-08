package ru.vsu.cs.putin_p_a.task4.gui;

import ru.vsu.cs.putin_p_a.task4.model.Composer;

import javax.swing.*;
import java.io.IOException;

public class ComposeProgramRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComposeProgramRunner::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        try {
            JFrame frame = new JFrame();
            frame.setTitle("Сжатие изображений");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.add(new ComposerMain(new Composer()));

            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
