package ru.vsu.cs.putin_p_a.redactors_tasks.math_parser;

import java.math.BigDecimal;
import java.util.Objects;

public class Parameter {
    private final String name;
    private BigDecimal value;

    public Parameter(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public Parameter(String name) {
        this(name, null);
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(name, parameter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
