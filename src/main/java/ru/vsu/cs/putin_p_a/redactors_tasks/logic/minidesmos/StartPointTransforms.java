package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.AffineTransformation;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.transformations.Scale;

public record StartPointTransforms(AffineTransformation translationInPixels, Scale userScale, CanvasCoordinateSystem canvasCoordinateSystem) {
//    public AffineTransformation getComposition() {
//        return translationInPixels
//                .then(canvasCoordinateSystem.getScale())
//                .then(userScale);
//    }
}
