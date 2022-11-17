package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.util.List;

public interface MathFunctionInputController {

    void notifyCreationCalculatorListeners(BasicExpression mathFunction);
    void notifyParsingErrorListeners(RuntimeException e);
    void addCreationCalculatorListener(CreationCalculatorListener listener);
    void addParsingErrorListener(ParsingErrorListener listener);
}
