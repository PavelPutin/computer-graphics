package ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils.matrix_algebra.Matrix;

import java.math.BigDecimal;

public class ScaleY extends AffineTransformation {
    public ScaleY(BigDecimal riseCoefficient) {
        super(new Matrix(new double[][]{
                {1, 0, 0},
                {0, riseCoefficient.doubleValue(), 0},
                {0, 0, 1}
        }));
        if (riseCoefficient.equals(BigDecimal.ZERO)) {
            throw new RuntimeException("Коэффициент увеличения не может равняться 0");
        }
    }
}
