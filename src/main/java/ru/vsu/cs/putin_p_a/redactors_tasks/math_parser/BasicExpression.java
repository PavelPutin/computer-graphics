package ru.vsu.cs.putin_p_a.redactors_tasks.math_parser;

import java.beans.Expression;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

abstract public class BasicExpression {
    private Map<String, Parameter> parameters;

    public BasicExpression(Parameter ... parameters) {
        this.parameters = Arrays.stream(parameters)
                .collect(Collectors.toMap(Parameter::getName, v -> v));
    }

    public BigDecimal calc() {
        for (Parameter parameter : parameters.values()) {
            if (parameter.getValue().equals(null)) {
                throw new RuntimeException(String.format("Параметр %s не определён", parameter.getName()));
            }
        }
        return null;
    }
}
