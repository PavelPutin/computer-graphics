package ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations;

public class CoordinateSystem extends AffineTransformation {
    public CoordinateSystem(AffineTransformation transformation) {
        super(transformation.getTransformation());

    }
}
