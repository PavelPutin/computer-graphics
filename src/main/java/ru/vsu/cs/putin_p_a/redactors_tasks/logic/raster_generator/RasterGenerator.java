package ru.vsu.cs.putin_p_a.redactors_tasks.logic.raster_generator;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class RasterGenerator {
    public static final double REAL_START_X = -10, REAL_FINISH_Y = 10;
    private int translateX, translateY;
    private double scaleValue;
    abstract public int[] getRaster(int width, int height);

    public List<Map<Integer, String>> getGrid(int width, int height) {
        this.scaleValue = 1;

        AffineTransformation scale = new Scale(BigDecimal.valueOf(1 / calcInitialScale(width)).multiply(BigDecimal.valueOf(scaleValue)));

        AffineTransformation coordinateSystem = new Translation(
                        BigDecimal.valueOf(-width / 2 + getTranslateX()),
                        BigDecimal.valueOf(-height / 2 + getTranslateY()))
                .then(new ReflectionY())
                .then(scale);

        AffineTransformation yInterpolation = new ReflectionY()
                .then(scale.getRedo())
                .then(new Translation(
                        BigDecimal.valueOf(width / 2 + getTranslateX()),
                        BigDecimal.valueOf(height / 2 + getTranslateY())
                ));

        List<Map<Integer, String>> pivotDots = new ArrayList<>();

        Point2d center = new Point2d(0, 0, 1, 1);
        center.transform(yInterpolation);

        int gridStep = 40;

        int centerX = center.getX().intValue(), centerY = center.getY().intValue();

        Map<Integer, String> xPivots = new HashMap<>();
        pivotDots.add(xPivots);
        xPivots.put(centerX, "0");
        for (int i = centerX - gridStep; i >= 0; i -= gridStep) {
            Point2d point2d = new Point2d(i, 0, 1, 1);
            point2d.transform(coordinateSystem);
            xPivots.put(i, String.format("%.2f", point2d.getX().doubleValue()));
        }

        for (int i = centerX + gridStep; i < width; i += gridStep) {
            Point2d point2d = new Point2d(i, 0, 1, 1);
            point2d.transform(coordinateSystem);
            xPivots.put(i, String.format("%.2f", point2d.getX().doubleValue()));
        }

        Map<Integer, String> yPivots = new HashMap<>();
        pivotDots.add(yPivots);
        yPivots.put(centerY, "0");
        for (int i = centerY - gridStep; i >= 0; i -= gridStep) {
            Point2d point2d = new Point2d(0, i, 1, 1);
            point2d.transform(coordinateSystem);
            yPivots.put(i, String.format("%.2f", point2d.getY().doubleValue()));
        }

        for (int i = centerY + gridStep; i < height; i += gridStep) {
            Point2d point2d = new Point2d(0, i, 1, 1);
            point2d.transform(coordinateSystem);
            yPivots.put(i, String.format("%.2f", point2d.getY().doubleValue()));
        }

        return pivotDots;
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public double calcInitialScale(int width) {
        return width / Math.abs(REAL_FINISH_Y - REAL_START_X);
    }
    public double getScaleValue() {
        return scaleValue;
    }

    public void updateScaleBy(double dScale) {
        this.scaleValue += dScale;
    }
}
