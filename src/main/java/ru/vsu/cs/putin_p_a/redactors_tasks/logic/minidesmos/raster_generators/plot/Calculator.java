package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.plot;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    private final BasicExpression mathFunction;

    public Calculator(BasicExpression mathFunction) {
        this.mathFunction = mathFunction;
    }

    public BigDecimal calc(BigDecimal x, List<Parameter> parameters) {
        Parameter xParam = new Parameter("x", x);
        Parameter[] params = new Parameter[parameters.size() + 1];
        int i = 0;
        params[i++] = xParam;
        for (Parameter p : parameters) {
            params[i++] = p;
        }
        return mathFunction.calc(params);
    }
}
