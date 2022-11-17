package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.AffineTransformation;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.ScaleX;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.ScaleY;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.Translation;

import java.math.BigDecimal;

public class CanvasCoordinateSystem {
    private final double startX, startY;
    private final double endX, endY;
    private final int width, height;
    private final AffineTransformation toReal;
    private final AffineTransformation toPixel;

    public CanvasCoordinateSystem(double startX, double endX, int width, int height) {
        this(startX, endX, width,
                -calcEndY(startX, endX, width, height),
                calcEndY(startX, endX, width, height),
                height);
    }

    public CanvasCoordinateSystem(
            double startX, double endX, int width,
            double startY, double endY, int height
    ) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
        this.height = height;

        BigDecimal deltaXToReal = BigDecimal.valueOf(Math.round(width * startX / (endX - startX)));
        BigDecimal deltaYToReal = BigDecimal.valueOf(Math.round(height * endY / (startY - endY)));
        BigDecimal scaleXToReal = BigDecimal.valueOf((endX - startX) / width);
        BigDecimal scaleYToReal = BigDecimal.valueOf((startY - endY) / height);

        toReal = new Translation(deltaXToReal, deltaYToReal)
                .then(new ScaleX(scaleXToReal))
                .then(new ScaleY(scaleYToReal));

        BigDecimal scaleXToPixel = BigDecimal.valueOf(width / (endX - startX));
        BigDecimal scaleYToPixel = BigDecimal.valueOf(height / (startY - endY));
        BigDecimal deltaXToPixel = BigDecimal.valueOf(Math.round(width * startX / (startX - endX)));
        BigDecimal deltaYToPixel = BigDecimal.valueOf(Math.round(height * endY / (endY - startY)));

        toPixel = new ScaleX(scaleXToPixel)
                .then(new ScaleY(scaleYToPixel))
                .then(new Translation(deltaXToPixel, deltaYToPixel));
    }

    private static double calcEndY(double startX, double endX, int width, int height) {
        return height * Math.abs(endX - startX) / 2 / width;
    }

    public double getStartX() {
        return startX;
    }

    public double getEndX() {
        return endX;
    }

    public int getWidth() {
        return width;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndY() {
        return endY;
    }

    public int getHeight() {
        return height;
    }

    public AffineTransformation getToReal() {
        return toReal;
    }

    public AffineTransformation getToPixel() {
        return toPixel;
    }
}
