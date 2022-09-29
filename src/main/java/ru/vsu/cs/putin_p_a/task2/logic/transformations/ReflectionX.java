package ru.vsu.cs.putin_p_a.task2.logic.transformations;

import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;

public class ReflectionX extends AffineTransformation {

    public ReflectionX() {
        super(new Matrix(new double[][]{
                {-1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        }));
    }
}
