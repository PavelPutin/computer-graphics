package ru.vsu.cs.putin_p_a;

import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui.MiniDesmosFrame;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.Model;

import javax.swing.*;

public class ApplicationMiniDesmos {
    public static void main(String[] args) {
        Model model = new Model();
        JFrame window = new MiniDesmosFrame(model);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
