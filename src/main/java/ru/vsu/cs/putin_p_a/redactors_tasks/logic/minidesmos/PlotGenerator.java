package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import java.math.BigDecimal;
import java.util.Arrays;
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

    public int[] plot(StartPointTransforms startPointTransforms) {
        CanvasCoordinateSystem cs = startPointTransforms.canvasCoordinateSystem();
        int width = cs.getWidth();
        int[] yValues = new int[width];
        for (int i = 0; i < width; i++) {
            yValues[i] = -1;
        }

        if (calculator == null) {
            return new int[width];
        }

        for (int x = 0; x < width; x++) {
            Point2d xValue = new Point2d(x, 0, 1, 0);
            xValue.transform(cs.getToReal());

            BigDecimal calculationResult = calculator.calc(xValue.getX(), parameterList);
            Point2d yValue = new Point2d(BigDecimal.ZERO, calculationResult, BigDecimal.ONE, BigDecimal.ZERO);
            yValue.transform(cs.getToPixel());
            int y = yValue.getY().intValue();
            yValues[x] = y;
        }
        return yValues;
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
