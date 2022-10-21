package ru.vsu.cs.putin_p_a.redactors_tasks.math_parser;

import java.math.BigDecimal;

public class Expression extends BasicExpression {
    private BasicExpression operand;

    public Expression(BasicExpression operand, Parameter ... parameters) {
        super(parameters);
        this.operand = operand;
    }

    @Override
    public BigDecimal calc() {
        return operand.calc();
    }
}
