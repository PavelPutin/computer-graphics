package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.BasicExpression;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.MathFunctionParser;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.MathFunctionParserUI;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.plot.PlotUI;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel implements PlotUI, MathFunctionParserUI {
    private JTextField rawFunctionField, startXField, finishXField, stepXField;
    private JButton createPlotButton;

    public ControlPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        rawFunctionField = new JTextField();
        add(rawFunctionField);

        startXField = new JTextField();
        add(startXField);

        finishXField = new JTextField();
        add(finishXField);

        stepXField = new JTextField();
        add(stepXField);
    }

    @Override
    public String getRawFunction() {
        return rawFunctionField.getText();
    }

    @Override
    public double getStartX() {
        return Double.parseDouble(startXField.getText());
    }

    @Override
    public double getFinishX() {
        return Double.parseDouble(finishXField.getText());
    }

    @Override
    public double getStepX() {
        return Double.parseDouble(stepXField.getText());
    }

    @Override
    public BasicExpression getExpression() {
        return new MathFunctionParser(getRawFunction()).parse();
    }
}
