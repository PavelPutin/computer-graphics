package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.*;

import javax.swing.*;
import java.awt.*;

public class MiniDesmosFrame extends JFrame {
    public MiniDesmosFrame(Model model) throws HeadlessException {
        super();
        this.setMinimumSize(new Dimension(200, 200));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        Canvas canvas = new Canvas(
                model.startPointTransformsController(),
                model.getCoordinateSystemGridGenerator(),
                model.getPlotGenerator(),
                model.getCurveGenerator()
        );
        canvas.setMinimumSize(new Dimension(200, 200));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(Box.createVerticalStrut(15));
        controlPanel.setMaximumSize(new Dimension(400, getHeight()));
        mainPanel.add(controlPanel);

        ParametersInputPanel parametersInputPanel = new ParametersInputPanel();
        parametersInputPanel.addParametersUpdatingListener(model.getPlotGenerator());
        parametersInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(parametersInputPanel);

        MathFunctionParsingPanel mathFunctionParsingPanel = new MathFunctionParsingPanel(parametersInputPanel);
        mathFunctionParsingPanel.addCreationCalculatorListener(model.getPlotGenerator());
        mathFunctionParsingPanel.addParsingErrorListener(e -> {
            throw e;
        });
        mathFunctionParsingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(mathFunctionParsingPanel);

        CurvePointsInputPanel curvePointsInputPanel = new CurvePointsInputPanel();
        curvePointsInputPanel.addPointUpdateListener(model.getCurveGenerator());
        curvePointsInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(curvePointsInputPanel);

        controlPanel.add(Box.createVerticalGlue());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, canvas, controlPanel);
        splitPane.setDividerLocation(600);
        splitPane.setPreferredSize(new Dimension(800, 800));
        mainPanel.add(splitPane);
        pack();
    }
}
