package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;


import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.CurveGeneratorChangeListener;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.PointUpdateListener;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.curves.AvailableCurvesGenerators;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class CurvePointsInputPanel extends JPanel {
    public static final int ROWS = 12, COLUMNS = 9;
    private JTextArea pointsInputArea;
    private JComboBox<String> curveMethodsSelector;
    private List<PointUpdateListener> pointUpdateListenerList = new ArrayList<>();
    private List<CurveGeneratorChangeListener> curveGeneratorChangeListeners = new ArrayList<>();
    public CurvePointsInputPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Выберите спсоб задачи кривой"));

        String[] availableCurveGeneratorNames = new String[AvailableCurvesGenerators.values().length];
        int i = 0;
        for (AvailableCurvesGenerators value : EnumSet.allOf(AvailableCurvesGenerators.class)) {
            availableCurveGeneratorNames[i++] = value.getLable();
        }
        curveMethodsSelector = new JComboBox<>(availableCurveGeneratorNames);
        curveMethodsSelector.setMaximumSize(new Dimension(300, 60));
        add(curveMethodsSelector);
        curveMethodsSelector.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            String selected = (String) cb.getSelectedItem();
            notifyCurveGeneratorChangeListener(selected);
            notifyPointUpdateListeners(parsePoints());
        });

        add(new JLabel("Введите точки для кривых"));

        pointsInputArea = new JTextArea(ROWS, COLUMNS);
        pointsInputArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange();
            }

            private void onChange() {
                notifyPointUpdateListeners(parsePoints());
            }
        });

        JScrollPane textAreaScrollPane = new JScrollPane();
        textAreaScrollPane.setMaximumSize(new Dimension(200, 300));
        textAreaScrollPane.setViewportView(pointsInputArea);
        add(textAreaScrollPane);
    }

    private List<Point2d> parsePoints() {
        String text = pointsInputArea.getText();
        String[] lines = text.split("\n");
        List<Point2d> points = new ArrayList<>();
        for (String line : lines) {
            String[] parsed = line.split(" ");
            if (parsed.length != 2) {
                return new ArrayList<>();
            }
            try {
                double x = Double.parseDouble(parsed[0]);
                double y = Double.parseDouble(parsed[1]);
                points.add(new Point2d(x, y, 1, 0));
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        return points;
    }

    public void addPointUpdateListener(PointUpdateListener listener) {
        pointUpdateListenerList.add(listener);
    }

    public void notifyPointUpdateListeners(List<Point2d> points) {
        for (PointUpdateListener listener : pointUpdateListenerList) {
            listener.updatePoints(points);
        }
    }

    public void addCurveGeneratorChangeListener(CurveGeneratorChangeListener listener) {
        curveGeneratorChangeListeners.add(listener);
    }

    public void notifyCurveGeneratorChangeListener(String curveGeneratorName) {
        for (CurveGeneratorChangeListener listener : curveGeneratorChangeListeners) {
            listener.changeCurveGenerator(curveGeneratorName);
        }
    }
}
