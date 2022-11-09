package ru.vsu.cs.putin_p_a.redactors_tasks;

import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.MiniDesmos;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.redactor2d.MainWindow;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame md = new MiniDesmos();
            md.setVisible(true);
            md.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
