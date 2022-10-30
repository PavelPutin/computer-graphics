package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;

public class Sum extends BinaryExpression {
    public Sum(BasicExpression leftOperand, BasicExpression rightOperand, String... parameters) {
        super(leftOperand, rightOperand, parameters);
    }

    @Override
    public BigDecimal calculationRule(Parameter ... parameters) {
        return getLeftOperand().calc(parameters).add(getRightOperand().calc(parameters));
    }
}
