package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.curves;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.CanvasCoordinateSystem;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.PointUpdateListener;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.StartPointTransforms;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.RasterGenerator;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import java.util.ArrayList;
import java.util.List;

abstract public class CurveGenerator extends RasterGenerator implements PointUpdateListener {
    public static final int POINTS_NUMBER = 75;

    private List<Point2d> points = new ArrayList<>();

    public List<Point2d> rasterPoints(StartPointTransforms startPointTransforms) {
        CanvasCoordinateSystem cs = startPointTransforms.canvasCoordinateSystem();
        List<Point2d> pixelPoints = new ArrayList<>();
        if (points.size() < 4) {
            return pixelPoints;
        }

        for (int i = 0; i < points.size() - 3; i += 3) {
            if (i + 3 >= points.size()) {
                break;
            }
            double step = 1.00 / POINTS_NUMBER;
            for (double t = 0; t < 1.00; t += step) {
                addPoint(cs, pixelPoints, i, t);
            }
            addPoint(cs, pixelPoints, i,1);
        }
        return pixelPoints;
    }

    protected void addPoint(CanvasCoordinateSystem cs, List<Point2d> pixelPoints, int i, double t) {
        double x = getX(i) * a(t) + getX(i + 1) * b(t) + getX(i + 2) * c(t) + getX(i + 3) * d(t);
        double y = getY(i) * a(t) + getY(i + 1) * b(t) + getY(i + 2) * c(t) + getY(i + 3) * d(t);
        Point2d pixelPoint = new Point2d(x, y, 1, 0);
        pixelPoint.transform(cs.getToPixel());
        pixelPoints.add(pixelPoint);
    }

    @Override
    public void startPointPositionChanged(StartPointTransforms e) {
        notifyListeners();
    }

    @Override
    public void updatePoints(List<Point2d> points) {
        this.points = new ArrayList<>(points);
        notifyListeners();
    }

    protected List<Point2d> getPoints() {
        return points;
    }

    abstract protected double a(double t);

    abstract protected double b(double t);

    abstract protected double c(double t);

    abstract protected double d(double t);

    private double getX(int index) {
        return points.get(index).getX().doubleValue();
    }

    private double getY(int index) {
        return points.get(index).getY().doubleValue();
    }
}
