package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.plot.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Derivative {
    public static final BigDecimal DELTA = BigDecimal.valueOf(1e-9);

    public static BigDecimal calcDerivative(Calculator calculator, BigDecimal xValue, BigDecimal fResult, List<Parameter> parameterList) {
        BigDecimal fPlusDelta = calculator.calc(xValue.add(DELTA), parameterList);
        BigDecimal subtraction = fPlusDelta.subtract(fResult);
        return subtraction.divide(DELTA, MathConstants.DIVISION_SCALE, RoundingMode.HALF_UP);
    }
}
