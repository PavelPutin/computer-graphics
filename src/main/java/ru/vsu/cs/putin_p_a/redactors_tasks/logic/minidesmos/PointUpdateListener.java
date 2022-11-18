package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import java.util.List;

public interface PointUpdateListener {
    void updatePoints(List<Point2d> points);
}
