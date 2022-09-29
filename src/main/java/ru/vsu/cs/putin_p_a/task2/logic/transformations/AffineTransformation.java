package ru.vsu.cs.putin_p_a.task2.logic.transformations;

import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;

public class AffineTransformation {
    private final Matrix transformation;


    public AffineTransformation(Matrix transformation) {
        this.transformation = transformation;
    }

    public Matrix getTransformation() {
        return transformation;
    }

    public AffineTransformation then(AffineTransformation t) {
        return new AffineTransformation(transformation.multiply(t.getTransformation()));
    }

    public AffineTransformation getRedo() {
        Matrix inverse = transformation.getInverse();
        return new AffineTransformation(inverse);
    }
}
