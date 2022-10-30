package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;

public class Number extends BasicExpression {
    private final BigDecimal value;

    public Number(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal calculationRule(Parameter...parameters) {
        return value;
    }
}
