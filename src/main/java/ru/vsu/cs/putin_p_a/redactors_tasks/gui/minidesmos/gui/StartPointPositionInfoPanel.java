package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.CoordinateSystemGrid;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.StartPointTransforms;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.StartPointPositionUpdatingListener;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.StartPointTransformsController;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import javax.swing.*;

public class StartPointPositionInfoPanel extends JPanel implements StartPointPositionUpdatingListener {
    private final JLabel startPointCoordinateXLabel, startPointCoordinateYLabel;

    public StartPointPositionInfoPanel(StartPointTransforms startPointTransforms) {
        super();
        Point2d startPoint = new Point2d(0, 0, 1, 1);
//        startPoint.transform(startPointTransforms.getComposition());

        startPointCoordinateXLabel = new JLabel();
        setText(startPointCoordinateXLabel, "X:", startPoint.getX().intValue());
        add(startPointCoordinateXLabel);

        startPointCoordinateYLabel = new JLabel();
        setText(startPointCoordinateYLabel, "Y:", startPoint.getY().intValue());
        add(startPointCoordinateYLabel);
    }

    @Override
    public void startPointPositionChanged(StartPointTransforms e) {
        Point2d startPoint = new Point2d(0, 0, 1, 1);
//        startPoint.transform(e.getComposition());
        setText(startPointCoordinateXLabel, "X:", startPoint.getX().intValue());
        setText(startPointCoordinateYLabel, "Y:", startPoint.getY().intValue());
    }

    private void setText(JLabel label, String title, int value) {
        label.setText(title + " " + value);
    }
}
