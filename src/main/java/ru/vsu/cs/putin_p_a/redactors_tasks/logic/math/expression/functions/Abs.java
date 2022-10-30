package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.functions;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.Expression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;

public class Abs extends Expression {
    public Abs(BasicExpression operand, String... parameters) {
        super(operand, parameters);
    }

    @Override
    public BigDecimal calculationRule(Parameter...parameters) {
        double tempResult = super.calculationRule(parameters).doubleValue();
        tempResult = Math.abs(tempResult);
        return new BigDecimal(tempResult);
    }
}
