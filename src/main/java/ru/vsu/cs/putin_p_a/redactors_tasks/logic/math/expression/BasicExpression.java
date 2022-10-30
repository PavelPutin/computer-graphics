package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

abstract public class BasicExpression {
    private List<String> parameterNames;

    public BasicExpression(String ... parameterNames) {
        this.parameterNames = new ArrayList<>();
        this.parameterNames.addAll(Arrays.asList(parameterNames));
    }

    protected boolean allParameterReceived(Parameter ... parameters) {
        List<String> names = new ArrayList<>();
        for (Parameter p : parameters) {
            names.add(p.getName());
        }

        return names.containsAll(parameterNames);
    }

    protected BigDecimal calculationRule(Parameter ... parameters) {
        return null;
    }

    public BigDecimal calc(Parameter ... parameters) {
        if (!allParameterReceived(parameters)) {
            throw new RuntimeException("Переданы не все параметры");
        }
        return calculationRule(parameters);
    }

    public List<String> getParameterNames() {
        return parameterNames;
    }
}
