package ru.vsu.cs.putin_p_a.task2.logic.shapes;

import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.AffineTransformation;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.Translation;

import java.util.ArrayList;
import java.util.List;

abstract public class Shape2d implements Cloneable{
    private final List<HomogeneousCoordinates2d> vertexes;
    private HomogeneousCoordinates2d transformOrigin;

    public Shape2d() {
        this.vertexes = new ArrayList<>();
        transformOrigin = new HomogeneousCoordinates2d(0, 0, 1);
    }

    public Shape2d(List<HomogeneousCoordinates2d> vertexes) {
        this.vertexes = new ArrayList<>(vertexes);
        transformOrigin = new HomogeneousCoordinates2d(0, 0, 1);
    }

    public HomogeneousCoordinates2d getTransformOrigin() {
        return transformOrigin;
    }

    public void setTransformOrigin(HomogeneousCoordinates2d transformOrigin) {
        this.transformOrigin = transformOrigin;
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
        Translation t1 = new Translation(-transformOrigin.getX(), -transformOrigin.getY()),
                t2 = new Translation(transformOrigin.getX(), transformOrigin.getY());

        AffineTransformation relativeTransformation = t1.then(t).then(t2);
        Matrix transformedCoordinates = getMatrixVertexes().multiply(relativeTransformation.getTransformation());
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
