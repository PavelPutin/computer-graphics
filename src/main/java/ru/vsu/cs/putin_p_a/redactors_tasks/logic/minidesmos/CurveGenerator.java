package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import java.util.ArrayList;
import java.util.List;

public class CurveGenerator extends RasterGenerator implements PointUpdateListener {

    private List<Point2d> points = new ArrayList<>();

    public List<Point2d> rasterPoints(StartPointTransforms startPointTransforms) {
        CanvasCoordinateSystem cs = startPointTransforms.canvasCoordinateSystem();
        List<Point2d> pixelPoints = new ArrayList<>();
        if (points.size() < 4) {
            return pixelPoints;
        }

        for (Point2d point : points) {
            System.out.println(point.getX().intValue() + " " + point.getY().intValue());
        }
        for (int i = 0; i < points.size() - 3; i += 3) {
            if (i + 3 >= points.size()) {
                break;
            }
            for (double t = 0; t <= 1.01; t += 0.01) {
                double x = getX(i) * a(t) + getX(i + 1) * b(t) + getX(i + 2) * c(t) + getX(i + 3) * d(t);
                double y = getY(i) * a(t) + getY(i + 1) * b(t) + getY(i + 2) * c(t) + getY(i + 3) * d(t);
                Point2d pixelPoint = new Point2d(x, y, 1, 0);
                pixelPoint.transform(cs.getToPixel());
                pixelPoints.add(pixelPoint);
            }
        }
        return pixelPoints;
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

    private double a(double t) {
        return -Math.pow(t, 3) + 3 * Math.pow(t, 2) - 3 * t + 1;
    }

    private double b(double t) {
        return 3 * Math.pow(t, 3) - 6 * Math.pow(t, 2) + 3 * t;
    }

    private double c(double t) {
        return -3 * Math.pow(t, 3) + 3 * Math.pow(t, 2);
    }

    private double d(double t) {
        return Math.pow(t, 3);
    }

    private double getX(int index) {
        return points.get(index).getX().doubleValue();
    }

    private double getY(int index) {
        return points.get(index).getY().doubleValue();
    }
}
