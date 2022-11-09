package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.plot;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Path2d;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plot {
    private BasicExpression expression;
    private double startX, finishX, stepX;
    private Path2d interpolation;

    public Plot(PlotUI ui) {
        this(ui.getStartX(), ui.getFinishX(), ui.getStepX(), ui.getExpression());
    }

    public Plot(double startX, double finishX, double stepX, BasicExpression expression) {
        this.expression = expression;
        this.startX = startX;
        this.finishX = finishX;
        this.stepX = stepX;
    }

    public Path2d getInterpolation(Parameter... parameters) {
        if (interpolation != null) {
            return interpolation;
        }

        if (Arrays.stream(parameters).noneMatch(parameter -> parameter.getName().equalsIgnoreCase("x"))) {
            BigDecimal result = expression.calc(parameters);
            List<HomogeneousCoordinates2d> points = Arrays.asList(
                    new HomogeneousCoordinates2d(startX, result.doubleValue(), 1),
                    new HomogeneousCoordinates2d(finishX, result.doubleValue(), 1)
            );
            return new Path2d(points);
        } else {
            Parameter x = Arrays.stream(parameters)
                    .filter(parameter -> parameter.getName().equalsIgnoreCase("x"))
                    .findAny().orElse(null);
            List<HomogeneousCoordinates2d> points = new ArrayList<>();
            for (double i = startX; i <= finishX; i += stepX) {
                x.setValue(BigDecimal.valueOf(i));
                BigDecimal result = expression.calc(parameters);
                points.add(new HomogeneousCoordinates2d(x.getValue(), result, BigDecimal.ONE));
            }
            return new Path2d(points);
        }
    }
}
