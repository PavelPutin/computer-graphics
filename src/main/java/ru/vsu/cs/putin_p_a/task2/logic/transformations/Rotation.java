package ru.vsu.cs.putin_p_a.task2.logic.transformations;

import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;

public class Rotation extends AffineTransformation {
    public Rotation(double theta) {
        super(new Matrix(new double[][]{
                {Math.cos(theta), Math.sin(theta), 0},
                {-Math.sin(theta), Math.cos(theta), 0},
                {0, 0, 1}
        }));
    }
}
