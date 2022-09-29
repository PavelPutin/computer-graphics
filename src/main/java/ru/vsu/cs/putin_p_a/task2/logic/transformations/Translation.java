package ru.vsu.cs.putin_p_a.task2.logic.transformations;

import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;

public class Translation extends AffineTransformation {
    public Translation(double deltaX, double deltaY) {
        super(new Matrix(new double[][] {
                {1, 0, 0},
                {0, 1, 0},
                {deltaX, deltaY, 1}
        }));
    }
}
