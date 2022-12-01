package ru.vsu.cs.putin_p_a.task4.application.gui;

import ru.vsu.cs.putin_p_a.task4.composer.model.input.abstractions.ErrorHandler;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements ErrorHandler {

    public MainWindow() throws HeadlessException {
        super();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        add(mainPanel);


    }

    @Override
    public void accept(Throwable throwable) {
        JOptionPane.showMessageDialog(this, throwable.getMessage());
    }
}
