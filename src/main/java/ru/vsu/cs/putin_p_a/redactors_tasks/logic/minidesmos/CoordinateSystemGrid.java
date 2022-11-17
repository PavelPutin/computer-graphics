package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import java.util.Map;

public record CoordinateSystemGrid(
        int centerX, int centerY,
        Map<Integer, Double> verticalPivotsX,
        Map<Integer, Double> horizontalPivotsY) {}
