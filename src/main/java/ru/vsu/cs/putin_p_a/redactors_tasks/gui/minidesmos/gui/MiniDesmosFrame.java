package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.CoordinateSystemGrid;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.Model;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.StartPointTransforms;

import javax.swing.*;
import java.awt.*;

public class MiniDesmosFrame extends JFrame {
    public MiniDesmosFrame(Model model) throws HeadlessException {
        super();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        Canvas canvas = new Canvas(
                model.getStartPointPositionController(),
                model.getCoordinateSystemGridGenerator()
        );
        canvas.setPreferredSize(new Dimension(800, 400));
        mainPanel.add(canvas);

        StartPointTransforms startPointPosition = model.getStartPointPositionController().getCurrent();
        StartPointPositionInfoPanel startPointPositionInfoPanel = new StartPointPositionInfoPanel(startPointPosition);
        model.getStartPointPositionController()
                .addStartPointPositionUpdatingListener(startPointPositionInfoPanel);
        mainPanel.add(startPointPositionInfoPanel);

        pack();
    }
}
