package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.curves;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.CanvasCoordinateSystem;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.StartPointTransforms;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import java.util.ArrayList;
import java.util.List;

public class BsplainCurveGenerator extends CurveGenerator{

    public List<Point2d> rasterPoints(StartPointTransforms startPointTransforms) {
        CanvasCoordinateSystem cs = startPointTransforms.canvasCoordinateSystem();
        List<Point2d> pixelPoints = new ArrayList<>();
        if (getPoints().size() < 4) {
            return pixelPoints;
        }

        for (int i = 0; i < getPoints().size() - 3; i++) {
            double step = 1.00 / POINTS_NUMBER;
            for (double t = 0; t < 1.00; t += step) {
                addPoint(cs, pixelPoints, i, t);
            }
            addPoint(cs, pixelPoints, i,1);
        }
        return pixelPoints;
    }

    @Override
    protected double a(double t) {
        return 1.0/6.0 * (-Math.pow(t, 3) + 3 * Math.pow(t, 2) - 3 * t + 1);
    }

    @Override
    protected double b(double t) {
        return 1.0/6.0 * (3 * Math.pow(t, 3) - 6 * Math.pow(t, 2) + 4);
    }

    @Override
    protected double c(double t) {
        return 1.0/6.0 * (-3 * Math.pow(t, 3) + 3 * Math.pow(t, 2) + 3 * t + 1);
    }

    @Override
    protected double d(double t) {
        return 1.0/6.0 * Math.pow(t, 3);
    }
}
