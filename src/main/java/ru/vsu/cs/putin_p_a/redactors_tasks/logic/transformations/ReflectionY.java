package ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations;

import ru.vsu.cs.putin_p_a.redactors_tasks.math_utils.matrix_algebra.Matrix;

public class ReflectionY extends AffineTransformation {

    public ReflectionY() {
        super(new Matrix(new double[][]{
                {1, 0, 0},
                {0, -1, 0},
                {0, 0, 1}
        }));
    }
}
