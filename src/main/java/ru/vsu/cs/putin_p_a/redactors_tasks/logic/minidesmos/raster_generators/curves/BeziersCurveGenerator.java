package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.curves;

public class BeziersCurveGenerator extends CurveGenerator {
    @Override
    protected double a(double t) {
        return -Math.pow(t, 3) + 3 * Math.pow(t, 2) - 3 * t + 1;
    }
    @Override
    protected double b(double t) {
        return 3 * Math.pow(t, 3) - 6 * Math.pow(t, 2) + 3 * t;
    }
    @Override
    protected double c(double t) {
        return -3 * Math.pow(t, 3) + 3 * Math.pow(t, 2);
    }
    @Override
    protected double d(double t) {
        return Math.pow(t, 3);
    }
}
