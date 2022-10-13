package ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations;

import ru.vsu.cs.putin_p_a.redactors_tasks.math_utils.matrix_algebra.Matrix;

import java.math.BigDecimal;

public class Rotation extends AffineTransformation {
    public Rotation(BigDecimal theta) {
        super(new Matrix(new double[][]{
                {Math.cos(theta.doubleValue()), Math.sin(theta.doubleValue()), 0},
                {-Math.sin(theta.doubleValue()), Math.cos(theta.doubleValue()), 0},
                {0, 0, 1}
        }));
    }
}
