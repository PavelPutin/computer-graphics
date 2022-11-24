package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.*;

import javax.swing.*;
import java.awt.*;

public class MiniDesmosFrame extends JFrame implements CurveGeneratorChangeListener {
    private final Model model;
    private final CurvePointsInputPanel curvePointsInputPanel;
    private final Canvas canvas;
    public MiniDesmosFrame(Model model) throws HeadlessException {
        super();

        this.model = model;

        this.setMinimumSize(new Dimension(200, 200));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        canvas = new Canvas(model);
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

        ErrorInfoPanel errorInfoPanel = new ErrorInfoPanel();
        errorInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(errorInfoPanel);

        MathFunctionParsingPanel mathFunctionParsingPanel = new MathFunctionParsingPanel(parametersInputPanel);
        mathFunctionParsingPanel.addCreationCalculatorListener(model.getPlotGenerator());
        mathFunctionParsingPanel.addParsingErrorListener(errorInfoPanel);
        mathFunctionParsingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(mathFunctionParsingPanel);

        curvePointsInputPanel = new CurvePointsInputPanel();
        curvePointsInputPanel.addPointUpdateListener(model.getCurveGenerator());
        curvePointsInputPanel.addCurveGeneratorChangeListener(model);
        curvePointsInputPanel.addCurveGeneratorChangeListener(this);
        curvePointsInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(curvePointsInputPanel);

        controlPanel.add(Box.createVerticalGlue());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, canvas, controlPanel);
        splitPane.setDividerLocation(600);
        splitPane.setPreferredSize(new Dimension(800, 800));
        mainPanel.add(splitPane);
        pack();
    }

    @Override
    public void changeCurveGenerator(String curveGeneratorName) {
        model.getCurveGenerator().addRasterUpdateListener(canvas);
        curvePointsInputPanel.addPointUpdateListener(model.getCurveGenerator());
    }
}
