package ru.vsu.cs.putin_p_a.redactors_tasks.logic.raster_generator;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

public interface ExpressionInputUI {
    String getSourceExpression();
    Parameter[] getParameters();
}
