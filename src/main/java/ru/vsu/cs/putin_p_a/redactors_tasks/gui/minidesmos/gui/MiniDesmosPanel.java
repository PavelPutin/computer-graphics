package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.raster_generator.ExpressionInputUI;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.raster_generator.Plotter;

import javax.swing.*;
import java.awt.*;

public class MiniDesmosPanel extends JPanel {
    public static final Dimension INITIAL = new Dimension(800, 600);
    public static final Dimension SMALL = new Dimension(100, 25);
    private ExpressionInputPanel expressionInputPanel;
    private DrawPanel drawPanel;
    private Plotter plotter;

    public MiniDesmosPanel() throws HeadlessException {
        super();
        this.setPreferredSize(INITIAL);
        this.setVisible(true);
        this.setLayout(new GridLayout(1, 1));

        this.plotter = new Plotter(new ExpressionInputUI() {
            @Override
            public String getSourceExpression() {
                return "x^2";
            }

            @Override
            public Parameter[] getParameters() {
                return new Parameter[0];
            }
        });

        DrawPanel drawPanel = new DrawPanel(this.plotter);
        JScrollPane drawScrollPane = new JScrollPane();
        drawScrollPane.setViewportView(drawPanel);

        ExpressionInputPanel expressionInputPanel = new ExpressionInputPanel();
        JScrollPane expressionInputScrollPane = new JScrollPane();
        expressionInputScrollPane.setViewportView(expressionInputPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, drawScrollPane, expressionInputScrollPane);
        splitPane.setDividerLocation(INITIAL.width - 250);
        this.add(splitPane);
    }
}
