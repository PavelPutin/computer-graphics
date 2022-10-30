package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;

public class Exponential extends BinaryExpression {
    public Exponential(BasicExpression leftOperand, BasicExpression rightOperand, String... parameters) {
        super(leftOperand, rightOperand, parameters);
    }

    @Override
    public BigDecimal calculationRule(Parameter...parameters) {
        double leftResult = getLeftOperand().calc(parameters).doubleValue();
        double rightResult = getRightOperand().calc(parameters).doubleValue();
        return BigDecimal.valueOf(Math.pow(leftResult, rightResult));
    }
}
