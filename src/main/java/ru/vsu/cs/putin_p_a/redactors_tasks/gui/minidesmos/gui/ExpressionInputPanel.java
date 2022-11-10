package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.MathFunctionParser;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.raster_generator.ExpressionInputUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ExpressionInputPanel extends JPanel implements ExpressionInputUI {

    private JTextField expressionInputField;
    private JLabel warningLabel;

    public ExpressionInputPanel() {
        super();
        expressionInputField = new JTextField();
        warningLabel = new JLabel();

        expressionInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                tryParse();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                tryParse();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                tryParse();
            }

            public void tryParse() {
                String text = expressionInputField.getText();
                try {
                    new MathFunctionParser(text).parse();
                    warningLabel.setText("");
                } catch (RuntimeException e) {
                    warningLabel.setText(e.getMessage());
                }
            }
        });
    }

    @Override
    public Parameter[] getParameters() {
        return new Parameter[0];
    }

    @Override
    public String getSourceExpression() {
        return expressionInputField.getText();
    }
}
