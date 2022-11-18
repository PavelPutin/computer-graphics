package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.PointUpdateListener;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CurvePointsInputPanel extends JPanel {
    private JTextArea pointsInputArea;
    private List<PointUpdateListener> pointUpdateListenerList = new ArrayList<>();
    public CurvePointsInputPanel() {
        super();
        add(new JLabel("Введите точки для кривых (количество точек должно быть кратно 3)"));

        pointsInputArea = new JTextArea(5, 20);
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
}
