package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils;

public class Rounding {
    public static long getNumberDigitLength(long number) {
        if (number < 100000) {
            if (number < 100) {
                if (number < 10) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                if (number < 1000) {
                    return 3;
                } else {
                    if (number < 10000) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
        } else {
            if (number < 10000000) {
                if (number < 1000000) {
                    return 6;
                } else {
                    return 7;
                }
            } else {
                if (number < 100000000) {
                    return 8;
                } else {
                    if (number < 1000000000) {
                        return 9;
                    } else {
                        return 10;
                    }
                }
            }
        }
    }

    public static long roundToFirstDigit(double number) {
        long value = Math.round(number);
        double scale = Math.pow(10, -getNumberDigitLength(value) + 1);
        double result = Math.round(value * scale) / scale;
        return (long) result;
    }
}
