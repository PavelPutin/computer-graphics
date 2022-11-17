package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.ParameterUpdatingListener;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.ParametersInputController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ParametersInputPanel extends JPanel implements ParametersInputController, ChangeListener {

    private List<ParameterInputUnit> parameterInputUnits = new ArrayList<>();
    private List<ParameterUpdatingListener> listeners = new ArrayList<>();

    public ParametersInputPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Задайте значение параметров"));
    }

    @Override
    public void updateInput(List<String> parametersName) {
        for (ParameterInputUnit parameterInputUnit : parameterInputUnits) {
            remove(parameterInputUnit);
        }
        parameterInputUnits.clear();
        for (String name : parametersName) {
            ParameterInputUnit parameterInputUnit = new ParameterInputUnit(name);
            parameterInputUnit.addParameterInputController(this);
            add(parameterInputUnit);
            parameterInputUnits.add(parameterInputUnit);
        }
        notifyParameterUpdatingListeners();
    }

    @Override
    public List<Parameter> getParameters() {
        List<Parameter> parameters = new ArrayList<>();
        for (ParameterInputUnit inputUnit : parameterInputUnits) {
            parameters.add(inputUnit.getParameter());
        }
        return parameters;
    }

    @Override
    public void notifyParameterUpdatingListeners() {
        for (ParameterUpdatingListener listener : listeners) {
            listener.updateParameters(getParameters());
        }
    }

    @Override
    public void addParametersUpdatingListener(ParameterUpdatingListener listener) {
        listeners.add(listener);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        notifyParameterUpdatingListeners();
    }
}
