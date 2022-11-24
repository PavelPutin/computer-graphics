package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.ParametersInputController;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParameterInputUnit extends JPanel {
    public static final double DEFAULT_VALUE = 1;
    private List<ParametersInputController> parametersInputControllers = new ArrayList<>();
    private Parameter parameter;

    public ParameterInputUnit(String parametersName) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);

        add(new JLabel(parametersName + ":"));
        parameter = new Parameter(parametersName, BigDecimal.valueOf(DEFAULT_VALUE));
        JTextField parameterInputField = new StyledTextField(String.valueOf(DEFAULT_VALUE));
        add(parameterInputField);
        parameterInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setParameterValue();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setParameterValue();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setParameterValue();
            }

            private void setParameterValue() {
                String content = parameterInputField.getText();
                double value;
                if (content.equalsIgnoreCase("pi")) {
                    value = Math.PI;
                } else if (content.equalsIgnoreCase("e")) {
                    value = Math.E;
                } else {
                    try {
                        Double.parseDouble(content);
                    } catch (NumberFormatException e) {
                        content = "1";
                    }

                    value = Double.parseDouble(content);
                }
                BigDecimal decimalValue = BigDecimal.valueOf(value);
                parameter.setValue(decimalValue);
                notifyParameterInputController();
            }
        });
    }

    public void addParameterInputController(ParametersInputController listener) {
        parametersInputControllers.add(listener);
    }

    public void notifyParameterInputController() {
        for (ParametersInputController controller : parametersInputControllers) {
            controller.notifyParameterUpdatingListeners();
        }
    }

    public Parameter getParameter() {
        return parameter;
    }
}
