package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;

public class Multiplication extends BinaryExpression {
    public Multiplication(BasicExpression leftOperand, BasicExpression rightOperand, String... parameters) {
        super(leftOperand, rightOperand, parameters);
    }

    @Override
    public BigDecimal calculationRule(Parameter...parameters) {
        return getLeftOperand().calc(parameters).multiply(getRightOperand().calc(parameters));
    }
}
