package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

public abstract class BinaryExpression extends BasicExpression {
    private final BasicExpression leftOperand, rightOperand;

    public BinaryExpression(BasicExpression leftOperand, BasicExpression rightOperand, String... parameterNames) {
        super(parameterNames);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.getParameterNames().addAll(leftOperand.getParameterNames());
        this.getParameterNames().addAll(rightOperand.getParameterNames());
    }

    public BasicExpression getLeftOperand() {
        return leftOperand;
    }

    public BasicExpression getRightOperand() {
        return rightOperand;
    }
}
