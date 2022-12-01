package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.plot;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils.Derivative;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils.MathConstants;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.RasterGenerator;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.coordinate_system.CoordinateSystemGrid;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.curves.BeziersCurveGenerator;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.curves.CurveGenerator;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PlotGenerator extends RasterGenerator implements CreationCalculatorListener, ParameterUpdatingListener {
    private Calculator calculator;
    private List<Parameter> parameterList;

    public PlotGenerator() {
        this(null);
    }

    public PlotGenerator(Calculator calculator) {
        this.calculator = calculator;
    }

    public List<Point2d> plot(StartPointTransforms startPointTransforms, CoordinateSystemGrid coordinateSystemGrid) {
        CanvasCoordinateSystem cs = startPointTransforms.canvasCoordinateSystem();
        int width = cs.getWidth();

        if (calculator == null) {
            return new ArrayList<>();
        }

        List<Point2d> points = new ArrayList<>(width * CurveGenerator.POINTS_NUMBER);


        double step = Math.abs(cs.getEndX() - cs.getStartX()) / 100;
        for (double x = cs.getStartX(); x <= cs.getEndX() + step; x += step) {
            BigDecimal xValue = BigDecimal.valueOf(x);
            System.out.println(x);
            BigDecimal calculationResult = calculator.calc(xValue, parameterList);
            Point2d resultPoint = new Point2d(xValue, calculationResult, BigDecimal.ONE, BigDecimal.ZERO);

            if (points.size() > 1) {
                Point2d previousPoint = points.get(points.size() - 1);

                BigDecimal x1 = previousPoint.getX(),
                        y1 = previousPoint.getY(),
                        x2 = resultPoint.getX(),
                        y2 = resultPoint.getY();

                BigDecimal derivativeX1 = Derivative.calcDerivative(calculator, x1, y1, parameterList);
                BigDecimal derivativeX2 = Derivative.calcDerivative(calculator, x2, y2, parameterList);
                BigDecimal d = derivativeX2.subtract(derivativeX1);

                BigDecimal px = x2.add(x1).divide(BigDecimal.valueOf(2), MathConstants.DIVISION_SCALE, RoundingMode.HALF_UP);
                BigDecimal py = y2.add(y1).divide(BigDecimal.valueOf(2), MathConstants.DIVISION_SCALE, RoundingMode.HALF_UP);

                if (!(d.doubleValue() < MathConstants.PRECISION)) {
                    BigDecimal dx = x2.multiply(derivativeX2)
                            .subtract(y2)
                            .subtract(x1.multiply(derivativeX1))
                            .add(y1);
                    px = dx.divide(d, MathConstants.DIVISION_SCALE, RoundingMode.HALF_UP);

                    BigDecimal dy = derivativeX1.multiply(x2.multiply(derivativeX2).subtract(y2))
                            .subtract(derivativeX2.multiply(x1.multiply(derivativeX1).subtract(y1)));
                    py = dy.divide(d, MathConstants.DIVISION_SCALE, RoundingMode.HALF_UP);
                }

                Point2d pivot = new Point2d(px, py, BigDecimal.ONE, BigDecimal.ZERO);
                for (int times = 0; times < 2; times++) {
                    points.add(pivot);
                }
            }
            points.add(resultPoint);
        }
        BeziersCurveGenerator cg = new BeziersCurveGenerator();
        cg.updatePoints(points);
        List<Point2d> result = cg.rasterPoints(startPointTransforms).stream().filter(point ->
                point.getY().intValue() >= 0 && point.getY().intValue() <= cs.getHeight()
        ).toList();
        return result;
    }

    @Override
    public void calculatorCreated(Calculator calculator) {
        this.calculator = calculator;
        notifyListeners();
    }

    @Override
    public void startPointPositionChanged(StartPointTransforms e) {
        notifyListeners();
    }

    @Override
    public void updateParameters(List<Parameter> parameterList) {
        this.parameterList = parameterList;
        notifyListeners();
    }
}
