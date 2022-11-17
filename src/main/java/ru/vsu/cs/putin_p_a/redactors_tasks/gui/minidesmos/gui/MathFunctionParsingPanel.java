package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.MathFunctionParser;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MathFunctionParsingPanel extends JPanel implements MathFunctionInputController {
    private List<CreationCalculatorListener> creationCalculatorListeners = new ArrayList<>();
    private List<ParsingErrorListener> parsingErrorListeners = new ArrayList<>();
    private ParametersInputController parametersInputController;

    private JTextField mathFunctionField;

    public MathFunctionParsingPanel(ParametersInputController parametersInputController) {
        super();
        this.parametersInputController = parametersInputController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Введите функцию:"));
        mathFunctionField = new JTextField();
        add(mathFunctionField);
        JButton parseButton = new JButton("Ввести функцию");
        add(parseButton);
        parseButton.addActionListener(e -> {
            MathFunctionParser parser = new MathFunctionParser(mathFunctionField.getText());
            try {
                BasicExpression mathFunction = parser.parse();
                List<String> parametersNames = new ArrayList<>(mathFunction.getParameterNames());
                parametersNames.remove("x");
                parametersInputController.updateInput(parametersNames);
                notifyCreationCalculatorListeners(mathFunction);
            } catch (RuntimeException exception) {
                notifyParsingErrorListeners(exception);
            }
        });
    }

    @Override
    public void notifyCreationCalculatorListeners(BasicExpression mathFunction) {
        for (CreationCalculatorListener listener : creationCalculatorListeners) {
            listener.calculatorCreated(new Calculator(mathFunction));
        }
    }

    @Override
    public void notifyParsingErrorListeners(RuntimeException e) {
        for (ParsingErrorListener listener : parsingErrorListeners) {
            listener.showErrorMessage(e);
        }
    }

    @Override
    public void addCreationCalculatorListener(CreationCalculatorListener listener) {
        creationCalculatorListeners.add(listener);
    }

    @Override
    public void addParsingErrorListener(ParsingErrorListener listener) {
        parsingErrorListeners.add(listener);
    }
}
