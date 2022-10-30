package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;
import java.util.Arrays;

public class Variable extends BasicExpression {

    private final String name;

    public Variable(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public BigDecimal calculationRule(Parameter...parameters) {
        for (Parameter p : parameters) {
            if (p.getName().equals(name)) {
                return p.getValue();
            }
        }
        return null;
    }
}
