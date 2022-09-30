package ru.vsu.cs.putin_p_a.task2.gui;

import ru.vsu.cs.putin_p_a.task2.logic.Redactor;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.HomogeneousCoordinates2d;
import ru.vsu.cs.putin_p_a.task2.logic.shapes.Point2d;
import ru.vsu.cs.putin_p_a.task2.logic.transformations.*;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static final Dimension INITIAL = new Dimension(800, 600);
    public static final Dimension SMALL = new Dimension(100, 25);
    private final Redactor redactor;
    private boolean canSetTransformOrigin, canAddTransform;

    public MainWindow() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(INITIAL);
        this.setVisible(true);

        redactor = new Redactor();
        canSetTransformOrigin = false;
        canAddTransform = false;

        DrawPanel drawPanel = new DrawPanel();
        JScrollPane drawScrollPane = new JScrollPane();
        drawScrollPane.setViewportView(drawPanel);

        JPanel transformSettingsPanel = new JPanel();
        transformSettingsPanel.setLayout(new BoxLayout(transformSettingsPanel, BoxLayout.Y_AXIS));
        JScrollPane settingsScrollPane = new JScrollPane();
        settingsScrollPane.setViewportView(transformSettingsPanel);

        JPanel shapesSelectingPanel = new JPanel();
        transformSettingsPanel.add(shapesSelectingPanel);

        String[] availableShapes = redactor.getAvailableShapes().keySet().toArray(new String[0]);
        JComboBox availableShapesList = new JComboBox(availableShapes);
        availableShapesList.setMaximumSize(SMALL);
        shapesSelectingPanel.add(availableShapesList);

        JPanel originSelectingPanel = new JPanel();
        originSelectingPanel.setLayout(new BoxLayout(originSelectingPanel, BoxLayout.Y_AXIS));
        transformSettingsPanel.add(originSelectingPanel);

        originSelectingPanel.add(new JLabel("Точка трансформации"));

        JTextField transformOriginX = new JTextField();
        transformOriginX.setText("0");
        transformOriginX.setMaximumSize(SMALL);
        originSelectingPanel.add(transformOriginX);

        JTextField transformOriginY = new JTextField();
        transformOriginY.setText("0");
        transformOriginY.setMaximumSize(SMALL);
        originSelectingPanel.add(transformOriginY);

        JButton applyTransformOrigin = new JButton("Установить точку трансформации");

        originSelectingPanel.add(applyTransformOrigin);
        originSelectingPanel.setVisible(canSetTransformOrigin);

        JPanel transformationPanel = new JPanel();
        transformationPanel.setLayout(new BoxLayout(transformationPanel, BoxLayout.Y_AXIS));
        transformSettingsPanel.add(transformationPanel);
        transformationPanel.setVisible(false);

        JPanel rotationPanel = new JPanel();
        rotationPanel.setLayout(new BoxLayout(rotationPanel, BoxLayout.Y_AXIS));
        transformationPanel.add(rotationPanel);

        rotationPanel.add(new JLabel("Вращение против часовой стрелки"));

        JTextField rotationValue = new JTextField();
        rotationValue.setText("0");
        rotationValue.setMaximumSize(SMALL);
        rotationPanel.add(rotationValue);

        JButton applyRotation = new JButton("Повернуть");
        rotationPanel.add(applyRotation);

        JLabel exceptionLabel = new JLabel();
        transformationPanel.add(exceptionLabel);

        JPanel scalePanel = new JPanel();
        scalePanel.setLayout(new BoxLayout(scalePanel, BoxLayout.Y_AXIS));
        transformationPanel.add(scalePanel);

        scalePanel.add(new JLabel("Масштабирование (увеличение)"));

        JTextField scaleValue = new JTextField();
        scaleValue.setText("0");
        scaleValue.setMaximumSize(SMALL);
        scalePanel.add(scaleValue);

        JButton applyScale = new JButton("Масштабировать");
        scalePanel.add(applyScale);


        JPanel scaleXPanel = new JPanel();
        scaleXPanel.setLayout(new BoxLayout(scaleXPanel, BoxLayout.Y_AXIS));
        transformationPanel.add(scaleXPanel);

        scaleXPanel.add(new JLabel("Растяжение по X"));

        JTextField scaleXValue = new JTextField();
        scaleXValue.setText("0");
        scaleXValue.setMaximumSize(SMALL);
        scaleXPanel.add(scaleXValue);

        JButton applyScaleX = new JButton("Растянуть по X");
        scaleXPanel.add(applyScaleX);


        JPanel scaleYPanel = new JPanel();
        scaleYPanel.setLayout(new BoxLayout(scaleYPanel, BoxLayout.Y_AXIS));
        transformationPanel.add(scaleYPanel);

        scaleYPanel.add(new JLabel("Растяжение по Y"));

        JTextField scaleYValue = new JTextField();
        scaleYValue.setText("0");
        scaleYValue.setMaximumSize(SMALL);
        scaleYPanel.add(scaleYValue);

        JButton applyScaleY = new JButton("Растянуть по Y");
        scaleYPanel.add(applyScaleY);


        JPanel reflectionXPanel = new JPanel();
        reflectionXPanel.setLayout(new BoxLayout(reflectionXPanel, BoxLayout.Y_AXIS));
        transformationPanel.add(reflectionXPanel);

        JButton applyReflectionX = new JButton("Отразить по X");
        reflectionXPanel.add(applyReflectionX);

        JPanel reflectionYPanel = new JPanel();
        reflectionYPanel.setLayout(new BoxLayout(reflectionYPanel, BoxLayout.Y_AXIS));
        transformationPanel.add(reflectionYPanel);

        JButton applyReflectionY = new JButton("Отразить по Y");
        reflectionYPanel.add(applyReflectionY);


        JPanel translatePanel = new JPanel();
        translatePanel.setLayout(new BoxLayout(translatePanel, BoxLayout.Y_AXIS));
        transformationPanel.add(translatePanel);

        translatePanel.add(new JLabel("Перемещение"));

        JTextField translateXValue = new JTextField();
        translateXValue.setText("0");
        translateXValue.setMaximumSize(SMALL);
        translatePanel.add(translateXValue);

        JTextField translateYValue = new JTextField();
        translateYValue.setText("0");
        translateYValue.setMaximumSize(SMALL);
        translatePanel.add(translateYValue);

        JButton applyTranslate = new JButton("Переместить");
        translatePanel.add(applyTranslate);


        JPanel applyRemovePanel = new JPanel();
        applyRemovePanel.setLayout(new BoxLayout(applyRemovePanel, BoxLayout.Y_AXIS));
        transformSettingsPanel.add(applyRemovePanel);

        JButton applyTransform = new JButton("Применить трансформацию");
        applyRemovePanel.add(applyTransform);

        JButton removeTransform = new JButton("Убрать трансформацию");
        applyRemovePanel.add(removeTransform);

        Dimension minimumSize = new Dimension(250, 350);
        drawScrollPane.setMinimumSize(minimumSize);
        settingsScrollPane.setMinimumSize(minimumSize);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, drawScrollPane, settingsScrollPane);
        splitPane.setDividerLocation(INITIAL.width - 250);
        this.add(splitPane);

        this.pack();

        availableShapesList.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            String selected = (String) cb.getSelectedItem();
            redactor.setCurrent(selected);
            drawPanel.setTarget(redactor.getCurrent());
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
            canSetTransformOrigin = true;
            originSelectingPanel.setVisible(canSetTransformOrigin);
        });

        applyTransformOrigin.addActionListener(e -> {
            double x = Double.parseDouble(transformOriginX.getText());
            double y = Double.parseDouble(transformOriginY.getText());
            redactor.setTransformOrigin(new HomogeneousCoordinates2d(x, y, 1));
            drawPanel.setTransformOriginPoint(new Point2d(x, y, 1, 3));
            drawPanel.repaint();
            canAddTransform = true;
            transformationPanel.setVisible(canAddTransform);
        });

        applyRotation.addActionListener(e -> {
            double angle = Double.parseDouble(rotationValue.getText());
            double theta = angle * Math.PI / 180;
            redactor.addTransformation(new Rotation(theta));
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });

        applyScale.addActionListener(e -> {
            double scale = Double.parseDouble(scaleValue.getText());
            try {
                redactor.addTransformation(new Scale(scale));
                exceptionLabel.setText("");
            } catch (RuntimeException exception) {
                exceptionLabel.setText(exception.getMessage());
            }
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });

        applyScaleX.addActionListener(e -> {
            double scale = Double.parseDouble(scaleXValue.getText());
            try {
                redactor.addTransformation(new ScaleX(scale));
                exceptionLabel.setText("");
            }  catch (RuntimeException exception) {
                exceptionLabel.setText(exception.getMessage());
            }
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });

        applyScaleY.addActionListener(e -> {
            double scale = Double.parseDouble(scaleYValue.getText());
            try {
                redactor.addTransformation(new ScaleY(scale));
                exceptionLabel.setText("");
            } catch (RuntimeException exception) {
                exceptionLabel.setText(exception.getMessage());
            }
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });

        applyReflectionX.addActionListener(e -> {
            redactor.addTransformation(new ReflectionX());
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });

        applyReflectionY.addActionListener(e -> {
            redactor.addTransformation(new ReflectionY());
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });

        applyTranslate.addActionListener(e -> {
            double translateX = Double.parseDouble(translateXValue.getText());
            double translateY = Double.parseDouble(translateYValue.getText());
            redactor.addTransformation(new Translation(translateX, translateY));
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });

        applyTransform.addActionListener(e -> {
            redactor.applyTransformations();
            drawPanel.setTarget(redactor.getCurrent());
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });
        removeTransform.addActionListener(e -> {
            redactor.removeLastTransformation();
            drawPanel.setTarget(redactor.getCurrent());
            drawPanel.setTargetPreview(redactor.getPreview());
            drawPanel.repaint();
        });
    }
}
