package ru.vsu.cs.putin_p_a.redactors_tasks.logic.raster_generator;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.MathFunctionParser;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.*;

import java.math.BigDecimal;

public class Plotter extends RasterGenerator {

    private final BasicExpression expression;
    private final Parameter[] inputParameters;

    public Plotter(ExpressionInputUI expressionUI) {
        this.expression = new MathFunctionParser(expressionUI.getSourceExpression()).parse();
        inputParameters = expressionUI.getParameters();
    }

    @Override
    public int[] getRaster(int width, int height) {
        if (expression == null) {
            return null;
        }

        AffineTransformation scale = new Scale(BigDecimal.valueOf(1 / calcInitialScale(width)).multiply(BigDecimal.valueOf(getScaleValue())));

        AffineTransformation coordinateSystem = new ReflectionY()
                .then(new Translation(
                        BigDecimal.valueOf(-width / 2 + getTranslateX()),
                        BigDecimal.valueOf(-height / 2 + getTranslateY()))
                )
                .then(scale);

        AffineTransformation yInterpolation = new ReflectionY()
                .then(scale.getRedo())
                .then(new Translation(
                        BigDecimal.valueOf(width / 2 + getTranslateX()),
                        BigDecimal.valueOf(height / 2 + getTranslateY())
                ));

        Parameter x = new Parameter("x", BigDecimal.ZERO);
        Parameter[] parameters = new Parameter[inputParameters.length + 1];
        System.arraycopy(inputParameters, 0, parameters, 0, parameters.length - 1);
        parameters[parameters.length - 1] = x;

        int[] yValues = new int[width];
        for (int rasterX = 0; rasterX < width; rasterX++) {
            Point2d xPoint = new Point2d(rasterX, 0, 1, 1);
            xPoint.transform(coordinateSystem);
            x.setValue(xPoint.getX());

            BigDecimal y = expression.calc(parameters);
            Point2d yPoint = new Point2d(BigDecimal.ZERO, y, BigDecimal.ONE, BigDecimal.ONE);
            yPoint.transform(yInterpolation);
            yValues[rasterX] = yPoint.getY().intValue();
        }

        return yValues;
    }


}