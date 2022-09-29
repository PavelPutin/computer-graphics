package ru.vsu.cs.putin_p_a.task2.logic.shapes;

import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;

import java.util.ArrayList;
import java.util.List;

abstract public class Shape2d {
    private final List<HomogeneousCoordinates2d> vertexes;
    private HomogeneousCoordinates2d transformOrigin;

    public Shape2d() {
        vertexes = new ArrayList<>();
        transformOrigin = new HomogeneousCoordinates2d(0, 0, 1);
    }

    public HomogeneousCoordinates2d getTransformOrigin() {
        return transformOrigin;
    }

    public void setTransformOrigin(HomogeneousCoordinates2d transformOrigin) {
        this.transformOrigin = transformOrigin;
    }

    public List<HomogeneousCoordinates2d> getVertexes() {
        return vertexes;
    }

    public Matrix getMatrixVertexes() {
        List<List<Double>> coords = new ArrayList<>();
        for (HomogeneousCoordinates2d vertex : vertexes) {
            coords.add(vertex.getValues());
        }
        return new Matrix(coords);
    }
}
