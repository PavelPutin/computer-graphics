package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.util.List;

public interface ParametersInputController {
    void updateInput(List<String> parametersName);
    List<Parameter> getParameters();
    void notifyParameterUpdatingListeners();
    void addParametersUpdatingListener(ParameterUpdatingListener listener);
}
