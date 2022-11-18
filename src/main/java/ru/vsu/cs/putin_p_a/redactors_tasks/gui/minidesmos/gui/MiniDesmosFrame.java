package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
//        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        JScrollPane controlScrollPane = new JScrollPane();
        controlScrollPane.setViewportView(controlPanel);
        mainPanel.add(controlScrollPane);

        ParametersInputPanel parametersInputPanel = new ParametersInputPanel();
        parametersInputPanel.addParametersUpdatingListener(model.getPlotGenerator());
        controlPanel.add(parametersInputPanel);

        MathFunctionParsingPanel mathFunctionParsingPanel = new MathFunctionParsingPanel(parametersInputPanel);
        mathFunctionParsingPanel.addCreationCalculatorListener(model.getPlotGenerator());
        mathFunctionParsingPanel.addParsingErrorListener(e -> {
            throw e;
        });
        controlPanel.add(mathFunctionParsingPanel);

        CurvePointsInputPanel curvePointsInputPanel = new CurvePointsInputPanel();
        curvePointsInputPanel.addPointUpdateListener(model.getCurveGenerator());
        controlPanel.add(curvePointsInputPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, canvas, controlScrollPane);
        splitPane.setDividerLocation(240);
        splitPane.setPreferredSize(new Dimension(800, 800));
        mainPanel.add(splitPane);
        pack();
    }
}
