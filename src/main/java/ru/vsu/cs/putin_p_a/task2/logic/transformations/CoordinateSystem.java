package ru.vsu.cs.putin_p_a.task2.logic.transformations;

import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;

public class CoordinateSystem extends AffineTransformation {
    public CoordinateSystem(AffineTransformation transformation) {
        super(transformation.getTransformation());

    }
}
