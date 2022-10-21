package ru.vsu.cs.putin_p_a.redactors_tasks.math_parser;

import java.math.BigDecimal;

public class Number extends BasicExpression {
    private final BigDecimal value;

    public Number(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal calc() {
        return value;
    }
}
