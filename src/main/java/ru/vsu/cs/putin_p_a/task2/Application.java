package ru.vsu.cs.putin_p_a.task2;

import ru.vsu.cs.putin_p_a.task2.gui.MainWindow;
import ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra.Matrix;

public class Application {

    public static void main(String[] args) {
        new MainWindow();
        Matrix a = new Matrix(new double[][]{
                {1, 2},
                {3, 4}
        });
        System.out.println(a.getDeterminant());
        System.out.println(a.getTransposed());
        System.out.println(a.getInverse());
    }
}
