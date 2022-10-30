package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;

public class Expression extends BasicExpression {
    private final BasicExpression operand;

    public Expression(BasicExpression operand, String... parameterNames) {
        super(parameterNames);
        this.operand = operand;
        this.getParameterNames().addAll(operand.getParameterNames());
    }

    @Override
    public BigDecimal calculationRule(Parameter...parameters) {
        return operand.calc(parameters);
    }
}
