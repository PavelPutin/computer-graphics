package ru.vsu.cs.putin_p_a.task2;

import ru.vsu.cs.putin_p_a.task2.gui.MainWindow;
import ru.vsu.cs.putin_p_a.task2.logic.Matrix;

public class Application {

    public static void main(String[] args) {
        new MainWindow();
        Matrix a = new Matrix(new double[][]{
                {1, 0},
                {2, 3},
                {-2, 3}
        });
        Matrix b = new Matrix(new double[][] {
                {1, 2},
                {3, 4}
        });
        Matrix c = a
            .multiply(b)
            .multiply(b);
        System.out.println(c);
    }
}
