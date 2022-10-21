package ru.vsu.cs.putin_p_a.redactors_tasks.math_parser.math_operations;

import ru.vsu.cs.putin_p_a.redactors_tasks.math_parser.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.math_parser.Parameter;

import java.math.BigDecimal;

public class BinaryExpression extends BasicExpression {
    private final BasicExpression leftOperand, rightOperand;

    public BinaryExpression(BasicExpression leftOperand, BasicExpression rightOperand, Parameter... parameters) {
        super(parameters);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public BasicExpression getLeftOperand() {
        return leftOperand;
    }

    public BasicExpression getRightOperand() {
        return rightOperand;
    }
}
