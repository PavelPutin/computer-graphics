package ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils.MathConstants;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils.matrix_algebra.Matrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Scale extends AffineTransformation {
    public Scale(BigDecimal riseCoefficient) {
        super(new Matrix(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, BigDecimal.ONE.divide(riseCoefficient, MathConstants.DIVISION_SCALE, RoundingMode.HALF_UP).doubleValue()}
        }));
        if (riseCoefficient.equals(BigDecimal.ZERO)) {
            throw new RuntimeException("Коэффициент увеличения не может равняться 0");
        }
    }
}
