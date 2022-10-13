package ru.vsu.cs.putin_p_a.redactors_tasks.math_utils;

import static java.lang.Math.abs;

public class Trigonometry {
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
        System.out.println(abs(result));
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
