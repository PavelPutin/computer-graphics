package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils;

import static java.lang.Math.abs;

public final class Trigonometry {
    private Trigonometry() {}
    public static double cos(double value) {
        double result = Math.cos(value);
        result = roundToZeroOrOne(result);
        return result;
    }

    public static double sin(double value) {
        double result = Math.sin(value);
        result = roundToZeroOrOne(result);
        return result;
    }


    private static double roundToZeroOrOne(double result) {
        if (abs(result) <= MathConstants.PRECISION) {
            return 0;
        }
        if (abs(result - 1) <= MathConstants.PRECISION) {
            return 1;
        }

        if (abs(result + 1) <= MathConstants.PRECISION) {
            return -1;
        }
        return result;
    }
}
