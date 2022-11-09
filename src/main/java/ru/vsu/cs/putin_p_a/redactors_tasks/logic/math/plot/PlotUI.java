package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.plot;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;

public interface PlotUI {
    double getStartX();
    double getFinishX();
    double getStepX();
    BasicExpression getExpression();
}
