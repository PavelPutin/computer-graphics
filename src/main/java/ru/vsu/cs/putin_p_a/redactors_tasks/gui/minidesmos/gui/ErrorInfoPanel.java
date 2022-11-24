package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.ParsingErrorListener;

import javax.swing.*;
import java.awt.*;

public class ErrorInfoPanel extends JPanel implements ParsingErrorListener {

    private JLabel errorMessage;

    {
        errorMessage = new JLabel();
        errorMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(errorMessage);
    }

    public ErrorInfoPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public ErrorInfoPanel(LayoutManager layout) {
        super(layout);
    }

    public ErrorInfoPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public ErrorInfoPanel() {
        super();
    }

    @Override
    public void showErrorMessage(RuntimeException e) {
        errorMessage.setText(e.getMessage());
    }

    @Override
    public void clearErrorMessage() {
        errorMessage.setText("");
    }
}
