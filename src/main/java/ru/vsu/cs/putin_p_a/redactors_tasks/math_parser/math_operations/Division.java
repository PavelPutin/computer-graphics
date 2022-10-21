package ru.vsu.cs.putin_p_a.redactors_tasks.math_parser.math_operations;

import ru.vsu.cs.putin_p_a.redactors_tasks.math_parser.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.math_parser.Parameter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division extends BinaryExpression {
    private int divisionScale = 9;

    public Division(BasicExpression leftOperand, BasicExpression rightOperand, Parameter... parameters) {
        super(leftOperand, rightOperand, parameters);
    }

    public int getDivisionScale() {
        return divisionScale;
    }

    public void setDivisionScale(int divisionScale) {
        this.divisionScale = divisionScale;
    }

    @Override
    public BigDecimal calc() {
        super.calc();
        return getLeftOperand().calc().divide(getRightOperand().calc(), divisionScale, RoundingMode.HALF_UP);
    }
}
