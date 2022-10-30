package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.functions;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.Expression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.Number;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations.Exponential;

import java.math.BigDecimal;

public class Ctg extends Expression {
    public Ctg(BasicExpression operand, String... parameters) {
        super(operand, parameters);
    }

    @Override
    public BigDecimal calculationRule(Parameter...parameters) {
        Number tempResult = new Number(super.calculationRule(parameters));
        Tg tg = new Tg(tempResult);
        Exponential pow = new Exponential(tg, new Number(new BigDecimal(-1)));
        return pow.calc(parameters);
    }
}
