package ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations;

import ru.vsu.cs.putin_p_a.redactors_tasks.math_utils.matrix_algebra.Matrix;

import java.math.BigDecimal;

public class ScaleX extends AffineTransformation {
    public ScaleX(BigDecimal riseCoefficient) {
        super(new Matrix(new double[][]{
                {riseCoefficient.doubleValue(), 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        }));
        if (riseCoefficient.equals(BigDecimal.ZERO)) {
            throw new RuntimeException("Коэффициент увеличения не может равняться 0");
        }
    }
}
