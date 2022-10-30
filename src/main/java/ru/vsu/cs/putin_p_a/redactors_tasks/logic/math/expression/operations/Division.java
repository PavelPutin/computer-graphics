package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division extends BinaryExpression {
    private int divisionScale = 9;

    public Division(BasicExpression leftOperand, BasicExpression rightOperand, String... parameters) {
        super(leftOperand, rightOperand, parameters);
    }

    public int getDivisionScale() {
        return divisionScale;
    }

    public void setDivisionScale(int divisionScale) {
        this.divisionScale = divisionScale;
    }

    @Override
    public BigDecimal calculationRule(Parameter ... parameters) {
        return getLeftOperand().calc(parameters).divide(getRightOperand().calc(parameters), divisionScale, RoundingMode.HALF_UP);
    }
}
