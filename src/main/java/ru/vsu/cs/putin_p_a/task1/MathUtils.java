package ru.vsu.cs.putin_p_a.task1;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {

    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
