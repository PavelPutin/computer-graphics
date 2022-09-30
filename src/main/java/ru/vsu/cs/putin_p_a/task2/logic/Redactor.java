package ru.vsu.cs.putin_p_a.task2.logic;

import ru.vsu.cs.putin_p_a.task2.logic.shapes.*;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.AffineTransformation;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.EmptyTransformation;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.Translation;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Redactor {
    private Shape2d current, preview;
    private HomogeneousCoordinates2d transformOrigin;
    private Map<String, Shape2d> availableShapes;
    private Stack<AffineTransformation> selectedTransformations;

    public Redactor() {
        current = null;
        preview = null;
        transformOrigin = new HomogeneousCoordinates2d(0, 0, 1);

        availableShapes = new HashMap<>();
        availableShapes.put("Прямоугольник", new Rectangle(0, 0, 1, 100, 50));
        availableShapes.put("Треугольник", new Triangle(0, 0, 1, 100, 0, 1, 50, 100, 1));

        selectedTransformations = new Stack<>();
    }

    public Shape2d getCurrent() {
        return current;
    }

    public void setCurrent(String shapeName) {
        this.current = availableShapes.get(shapeName).clone();
        this.preview = null;
    }

    public Shape2d getPreview() {
        return preview;
    }

    public void setPreview(Shape2d preview) {
        this.preview = preview;
    }

    public Map<String, Shape2d> getAvailableShapes() {
        return availableShapes;
    }

    public void addTransformation(AffineTransformation t) {
        Translation t1 = new Translation(-transformOrigin.getX(), -transformOrigin.getY()),
                t2 = new Translation(transformOrigin.getX(), transformOrigin.getY());
        AffineTransformation relativeTransform = t1.then(t).then(t2);
        selectedTransformations.push(relativeTransform);
        if (preview == null) {
            preview = new Path2d(current.getVertexes());
        }
        preview.transform(relativeTransform);
    }

    public void removeLastTransformation() {
        if (selectedTransformations.isEmpty() || preview == null) {
            return;
        }

        AffineTransformation t = selectedTransformations.pop();
        preview.transform(t.getRedo());

        if (selectedTransformations.isEmpty()) {
            preview = null;
        }
    }

    public void applyTransformations() {
        if (preview != null) {
            current = new Path2d(preview.getVertexes());
        }
        preview = null;
    }

    public void setTransformOrigin(HomogeneousCoordinates2d coordinates2d) {
        transformOrigin = coordinates2d;
    }
}
