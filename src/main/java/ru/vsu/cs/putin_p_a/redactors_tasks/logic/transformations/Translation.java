package ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations;

import ru.vsu.cs.putin_p_a.redactors_tasks.math_utils.matrix_algebra.Matrix;

import java.math.BigDecimal;

public class Translation extends AffineTransformation {
    public Translation(BigDecimal deltaX, BigDecimal deltaY) {
        super(new Matrix(new double[][] {
                {1, 0, 0},
                {0, 1, 0},
                {deltaX.doubleValue(), deltaY.doubleValue(), 1}
        }));
    }
}
