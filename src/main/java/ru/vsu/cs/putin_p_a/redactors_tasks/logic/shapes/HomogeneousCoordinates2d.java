package ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes;

import ru.vsu.cs.putin_p_a.redactors_tasks.math_utils.MathConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class HomogeneousCoordinates2d implements Cloneable {
    private final List<BigDecimal> values;

    public HomogeneousCoordinates2d(BigDecimal x, BigDecimal y, BigDecimal h) {
        values = new ArrayList<>();
        values.add(x);
        values.add(y);
        values.add(h);
    }

    public HomogeneousCoordinates2d(double x, double y, double h) {
        this(BigDecimal.valueOf(x), BigDecimal.valueOf(y), BigDecimal.valueOf(h));
    }

    public HomogeneousCoordinates2d(List<BigDecimal> values) {
        this.values = new ArrayList<>();
        this.values.addAll(values);
    }

    public List<BigDecimal> getValues() {
        return new ArrayList<>(this.values);
    }

    public BigDecimal getHomogeneousX() {
        return values.get(0);
    }

    public BigDecimal getX() {
        return values.get(0).divide(values.get(2), MathConstants.DIVISION_SCALE, RoundingMode.HALF_UP);
    }

    public void setHomogeneousX(double x) {
        values.set(0, BigDecimal.valueOf(x));
    }

    public BigDecimal getHomogeneousY() {
        return values.get(1);
    }

    public BigDecimal getY() {
        return values.get(1).divide(values.get(2), MathConstants.DIVISION_SCALE, RoundingMode.HALF_UP);
    }

    public void setHomogeneousY(double y) {
        values.set(1, BigDecimal.valueOf(y));
    }

    public BigDecimal getH() {
        return values.get(2);
    }

    public void setH(BigDecimal h) {
        values.set(2, h);
    }

    @Override
    public HomogeneousCoordinates2d clone() {
        return new HomogeneousCoordinates2d(this.values);
    }
}
