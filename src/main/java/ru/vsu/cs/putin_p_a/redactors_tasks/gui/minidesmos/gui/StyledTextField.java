package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class StyledTextField extends JTextField {
    public static final int WIDTH = 200, HEIGHT = 25;

    {
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    public StyledTextField() {
        super();
    }

    public StyledTextField(String text) {
        super(text);
    }

    public StyledTextField(int columns) {
        super(columns);
    }

    public StyledTextField(String text, int columns) {
        super(text, columns);
    }

    public StyledTextField(Document doc, String text, int columns) {
        super(doc, text, columns);
    }
}
