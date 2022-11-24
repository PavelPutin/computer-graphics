package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.coordinate_system;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils.Rounding;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.CanvasCoordinateSystem;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.StartPointTransforms;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.StartPointTransformsUpdatingListener;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.RasterGenerator;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.AffineTransformation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CoordinateSystemGridGenerator extends RasterGenerator implements StartPointTransformsUpdatingListener {
    public static final int SEGMENTS_QUANTITY = 10;

    public CoordinateSystemGrid getCoordinateSystemGrid(StartPointTransforms startPointTransforms) {
        CanvasCoordinateSystem cs = startPointTransforms.canvasCoordinateSystem();

        double realWidth = Math.abs(cs.getStartX() - cs.getEndX());
        double decadeX = realWidth / SEGMENTS_QUANTITY;
        double stepX = decadeX >= 1 ? Rounding.roundToFirstDigit(decadeX) : decadeX;

        double realHeight = Math.abs(cs.getStartY() - cs.getEndY());
        double decadeY = realHeight / SEGMENTS_QUANTITY;
        double stepY = decadeY >= 1 ? Rounding.roundToFirstDigit(decadeY) : decadeY;

        AffineTransformation toReal = cs.getToReal();
        AffineTransformation toPixels = cs.getToPixel();

        Map<Integer, Double> verticalPivotsX = new LinkedHashMap<>();
        int startI = startIndex(cs.getStartX(), stepX),
            endI = endIndex(cs.getEndX(), stepX);
        for (int i = startI; i <= endI; i++) {
            double realValue = stepX * i;
            Point2d verticalPivot = new Point2d(realValue, 0, 1, 1);
            verticalPivot.transform(toPixels);
            int pixelValue = verticalPivot.getX().intValue();
            verticalPivotsX.put(pixelValue, realValue);
        }

        Map<Integer, Double> verticalPivotsY = new LinkedHashMap<>();
        startI = startIndex(cs.getStartY(), stepY);
        endI =endIndex(cs.getEndY(), stepY);
        for (int i = startI; i <= endI; i++) {
            double realValue = stepY * i;
            Point2d verticalPivot = new Point2d(0, realValue, 1, 1);
            verticalPivot.transform(toPixels);
            int pixelValue = verticalPivot.getY().intValue();
            verticalPivotsY.put(pixelValue, realValue);
        }

        Point2d center = new Point2d(0, 0, 1, 1);
        center.transform(toPixels);
        int centerX = center.getX().intValue();
        int centerY = center.getY().intValue();

        return new CoordinateSystemGrid(
                centerX, centerY,
                verticalPivotsX, verticalPivotsY
        );
    }

    @Override
    public void startPointPositionChanged(StartPointTransforms e) {
        notifyListeners();
    }

    private int startIndex(double startValue, double step) {
        return (int) Math.ceil(startValue / step);
    }

    private int endIndex(double endValue, double step) {
        return (int) Math.floor(endValue / step);
    }
}
