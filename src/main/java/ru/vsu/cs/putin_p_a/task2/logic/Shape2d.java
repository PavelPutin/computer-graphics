package ru.vsu.cs.putin_p_a.task2.logic;

import java.util.ArrayList;
import java.util.List;

abstract public class Shape2d {
    private final List<HomogeneousCoordinates2d> vertexes;

    public Shape2d() {
        vertexes = new ArrayList<>();
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
