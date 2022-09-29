package ru.vsu.cs.putin_p_a.task2.logic.shapes;

import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.AffineTransformation;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.Translation;

import java.util.ArrayList;
import java.util.List;

abstract public class Shape2d implements Cloneable{
    private final List<HomogeneousCoordinates2d> vertexes;
    public Shape2d() {
        this.vertexes = new ArrayList<>();
    }

    public Shape2d(List<HomogeneousCoordinates2d> vertexes) {
        this.vertexes = new ArrayList<>(vertexes);
    }
    public List<HomogeneousCoordinates2d> getVertexes() {
        return new ArrayList<>(vertexes);
    }

    public void addVertex(HomogeneousCoordinates2d coordinates2d) {
        vertexes.add(coordinates2d);
    }

    public Matrix getMatrixVertexes() {
        List<List<Double>> coords = new ArrayList<>();
        for (HomogeneousCoordinates2d vertex : vertexes) {
            coords.add(vertex.getValues());
        }
        return new Matrix(coords);
    }

    public void transform(AffineTransformation t) {
        Matrix transformedCoordinates = getMatrixVertexes().multiply(t.getTransformation());
        int i = 0;
        for (List<Double> row : transformedCoordinates.getValues()) {
            vertexes.set(i++, new HomogeneousCoordinates2d(row));
        }
    }

    @Override
    public Shape2d clone() {
        return new Path2d(vertexes);
    }
}
