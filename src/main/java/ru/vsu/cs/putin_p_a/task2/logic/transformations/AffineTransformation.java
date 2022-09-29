package ru.vsu.cs.putin_p_a.task2.logic.transformations;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Shape2d;

import java.util.List;

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

    public void applyTo(Shape2d s) {
        AffineTransformation m1 = new AffineTransformation(
                new Matrix(new double[][]{
                        {1, 0, 0},
                        {0, 1, 0},
                        {-s.getTransformOrigin().getX(), -s.getTransformOrigin().getY(), 1}
                })
        );
        AffineTransformation m2 = new AffineTransformation(
                new Matrix(new double[][]{
                        {1, 0, 0},
                        {0, 1, 0},
                        {s.getTransformOrigin().getX(), s.getTransformOrigin().getY(), 1}
                })
        );
        AffineTransformation relativeTransformation = m1.then(this).then(m2);
        Matrix transformedCoordinates = s.getMatrixVertexes().multiply(relativeTransformation.getTransformation());
        int i = 0;
        for (List<Double> row : transformedCoordinates.getValues()) {
            s.getVertexes().set(i++, new HomogeneousCoordinates2d(row));
        }
    }
}
