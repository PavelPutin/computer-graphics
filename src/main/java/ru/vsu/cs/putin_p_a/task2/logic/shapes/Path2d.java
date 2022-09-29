package ru.vsu.cs.putin_p_a.task2.logic.shapes;

import java.util.List;

public class Path2d extends Shape2d {
    public Path2d() {
        super();
    }

    public Path2d(List<HomogeneousCoordinates2d> vertexes) {
        super(vertexes);
    }
}
