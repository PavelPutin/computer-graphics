package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.AffineTransformation;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.Scale;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.Translation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StartPointTransformsController {
    private final List<StartPointTransformsUpdatingListener> listeners = new ArrayList<>();
    private StartPointTransforms current;

    public StartPointTransformsController() {
        current = new StartPointTransforms(
                new Translation(BigDecimal.ZERO, BigDecimal.ZERO),
                new Scale(BigDecimal.ONE),
                new CanvasCoordinateSystem(-1, 1, 1, 1));
    }

    public StartPointTransforms getCurrent() {
        return current;
    }

    public void updateTranslation(int fromX, int fromY, int toX, int toY) {
        Point2d fromPoint = new Point2d(fromX, fromY, 1, 0),
            toPoint = new Point2d(toX, toY, 1, 0);

        fromPoint.transform(current.canvasCoordinateSystem().getToReal());
        toPoint.transform(current.canvasCoordinateSystem().getToReal());

        double fromRealX = fromPoint.getX().doubleValue(),
                fromRealY = fromPoint.getY().doubleValue(),
                toRealX = toPoint.getX().doubleValue(),
                toRealY = toPoint.getY().doubleValue();
        double deltaRealX = toRealX - fromRealX,
                deltaRealY = toRealY - fromRealY;

        CanvasCoordinateSystem cs = current.canvasCoordinateSystem();
        double startX = cs.getStartX() - deltaRealX;
        double endX = cs.getEndX() - deltaRealX;
        double startY = cs.getStartY() - deltaRealY;
        double endY = cs.getEndY() - deltaRealY;

        updateCanvasScale(startX, endX, cs.getWidth(), startY, endY, cs.getHeight());
    }

    public void updateUserScale(int sourceX, int sourceY, double scaleValue) {
        CanvasCoordinateSystem cs = current.canvasCoordinateSystem();

        Point2d source = new Point2d(sourceX, sourceY, 1, 0);
        source.transform(cs.getToReal());

        BigDecimal minus = BigDecimal.valueOf(-1);
        BigDecimal minusRealSourceX = source.getX().multiply(minus),
                minusRealSourceY = source.getY().multiply(minus),
                realSourceX = source.getX(),
                realSourceY = source.getY();

        AffineTransformation userScale = new Translation(minusRealSourceX, minusRealSourceY)
                .then(new Scale(BigDecimal.valueOf(scaleValue)))
                .then(new Translation(realSourceX, realSourceY));

        List<Point2d> points = List.of(new Point2d(cs.getStartX(), 0, 1, 0),
                new Point2d(cs.getEndX(), 0, 1, 0),
                new Point2d(0, cs.getStartY(), 1, 0),
                new Point2d(0, cs.getEndY(), 1, 0));
        for (Point2d point : points) {
            point.transform(userScale);
        }

        double startX = points.get(0).getX().doubleValue(),
                endX = points.get(1).getX().doubleValue(),
                startY = points.get(2).getY().doubleValue(),
                endY = points.get(3).getY().doubleValue();
        updateCanvasScale(startX, endX, cs.getWidth(), startY, endY, cs.getHeight());
    }

    public void updateCanvasScale(
            double startX, double endX, int width,
            double startY, double endY, int height
    ) {
        CanvasCoordinateSystem cs = new CanvasCoordinateSystem(startX, endX, width, startY, endY, height);
        current = new StartPointTransforms(current.translationInPixels(), current.userScale(), cs);
        notifyListeners();
    }

    public void updateCanvasScale(
            double startX, double endX, int width, int height
    ) {
        CanvasCoordinateSystem cs = new CanvasCoordinateSystem(startX, endX, width, height);
        current = new StartPointTransforms(current.translationInPixels(), current.userScale(), cs);
        notifyListeners();
    }

    public void addStartPointPositionUpdatingListener(StartPointTransformsUpdatingListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        for (StartPointTransformsUpdatingListener listener : listeners) {
            listener.startPointPositionChanged(current);
        }
    }
}
