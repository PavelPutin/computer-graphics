package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        super();
        add(new MiniDesmosPanel());
        pack();
    }
}
