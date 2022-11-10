package ru.vsu.cs.putin_p_a;

import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui.MainFrame;

import javax.swing.*;

public class ApplicationMiniDesmos {
    public static void main(String[] args) {
        JFrame window = new MainFrame();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
