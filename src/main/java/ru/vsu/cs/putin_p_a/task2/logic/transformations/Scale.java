package ru.vsu.cs.putin_p_a.task2.logic.transformations;

import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;

public class Scale extends AffineTransformation {
    public Scale(double riseCoefficient) {
        super(new Matrix(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1 / riseCoefficient}
        }));
        if (riseCoefficient == 0) {
            throw new RuntimeException("Коэффициент увеличения не может равняться 0");
        }
    }
}
