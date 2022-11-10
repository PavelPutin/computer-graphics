package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils.MathConstants;
import ru.vsu.cs.putin_p_a.task1.MathUtils;

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
        BigDecimal rightOperandResult = getRightOperand().calc(parameters);
        if (Math.abs(rightOperandResult.doubleValue() - 0) < MathConstants.PRECISION) {
            return BigDecimal.valueOf(Integer.MAX_VALUE);
        }
        return getLeftOperand().calc(parameters).divide(rightOperandResult, divisionScale, RoundingMode.HALF_UP);
    }
}
