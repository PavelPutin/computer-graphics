package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;

public class Pi extends BasicExpression{
    @Override
    protected BigDecimal calculationRule(Parameter... parameters) {
        return BigDecimal.valueOf(Math.PI);
    }
}
