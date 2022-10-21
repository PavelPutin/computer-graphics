package ru.vsu.cs.putin_p_a.redactors_tasks.math_parser.math_operations;

import ru.vsu.cs.putin_p_a.redactors_tasks.math_parser.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.math_parser.Parameter;

import java.math.BigDecimal;

public class Exponential extends BinaryExpression {
    public Exponential(BasicExpression leftOperand, BasicExpression rightOperand, Parameter... parameters) {
        super(leftOperand, rightOperand, parameters);
    }

    @Override
    public BigDecimal calc() {
        super.calc();
        double leftResult = getLeftOperand().calc().doubleValue();
        double rightResult = getRightOperand().calc().doubleValue();
        return BigDecimal.valueOf(Math.pow(leftResult, rightResult));
    }
}
