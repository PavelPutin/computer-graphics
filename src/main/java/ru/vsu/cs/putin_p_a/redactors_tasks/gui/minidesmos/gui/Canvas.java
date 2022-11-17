package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.BresenhamLineAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.LineDrawingAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.PixelSetter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Map;

public class Canvas extends JPanel implements RasterUpdateListener, PixelSetter {
    public static final Color AXIS_COLOR = Color.BLACK;
    public static final Color GRID_LINES = Color.LIGHT_GRAY;
    private Graphics g;
    private LineDrawingAlgorithm lineDrawingAlgorithm = new BresenhamLineAlgorithm(this);
    private int mousePressPositionX, mousePressPositionY;
    private final StartPointTransformsController startPointTransformsController;
    private final CoordinateSystemGridGenerator coordinateSystemGridGenerator;

    public Canvas(
            StartPointTransformsController startPointTransformsController,
            CoordinateSystemGridGenerator coordinateSystemGridGenerator
    ) {
        super();
        this.startPointTransformsController = startPointTransformsController;
        this.coordinateSystemGridGenerator = coordinateSystemGridGenerator;
        coordinateSystemGridGenerator.addRasterUpdateListener(this);
        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                int rotationValue = e.getWheelRotation();

                double userScale = 0.5;
                if (rotationValue < 0) {
                    userScale = 2;
                }
                startPointTransformsController.updateUserScale(e.getX(), e.getY(), userScale);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mousePressPositionX = e.getX();
                mousePressPositionY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                startPointTransformsController.updateTranslation(mousePressPositionX, mousePressPositionY, e.getX(), e.getY());
                mousePressPositionX = e.getX();
                mousePressPositionY = e.getY();
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                startPointTransformsController.updateCanvasScale(-10, 10, getWidth(), getHeight());
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.g = g;
        StartPointTransforms startPointPosition = startPointTransformsController.getCurrent();
        CoordinateSystemGrid grid = this.coordinateSystemGridGenerator.getCoordinateSystemGrid(startPointPosition);
        drawGrid(grid);
    }

    @Override
    public void updateRasterView() {
        repaint();
    }

    @Override
    public void setPixel(int x, int y, Color color) {
        Color old = g.getColor();
        g.setColor(color);
        g.fillRect(x, y, 1, 1);
        g.setColor(old);
    }

    @Override
    public boolean containsPixel(int x, int y) {
        return 0 <= x && x <= getWidth() && 0 <= y && y <= getHeight();
    }

    private void drawGrid(CoordinateSystemGrid grid) {
        for (Map.Entry<Integer, Double> pivotX : grid.verticalPivotsX().entrySet()) {
            int x = pivotX.getKey();
            lineDrawingAlgorithm.drawLine(1, x, 0, x, getHeight(), GRID_LINES);

            String text = formatText(pivotX.getValue());
            int textY = getTextCoordinate(grid.centerY(), 0, getHeight(), 2);
            g.drawString(text, x, textY);
        }

        for (Map.Entry<Integer, Double> pivotY : grid.horizontalPivotsY().entrySet()) {
            int y = pivotY.getKey();
            lineDrawingAlgorithm.drawLine(1, 0, y, getWidth(), y, GRID_LINES);

            String text = formatText(pivotY.getValue());
            int gap = text.length() * 8;
            int textX = getTextCoordinate(grid.centerX(), 0, getWidth(), gap);
            g.drawString(text, textX, y);
        }

        lineDrawingAlgorithm.drawLine(1, grid.centerX(), 0, grid.centerX(), getHeight(), AXIS_COLOR);
        lineDrawingAlgorithm.drawLine(1, 0, grid.centerY(), getWidth(), grid.centerY(), AXIS_COLOR);
    }

    private int getTextCoordinate(int center, int minValue, int maxValue, int gap) {
        int textY = center;
        if (textY > maxValue) {
            textY = maxValue - gap;
        } else if (textY < minValue) {
            textY = minValue + g.getFont().getSize();
        }
        return textY;
    }

    private String formatText(double value) {
        DecimalFormat pattern = new DecimalFormat("#.###");
        if (Math.abs(value) > 10_000 || Math.abs(value) < 0.001) {
            pattern = new DecimalFormat("#.##E0");
        }
        return pattern.format(value);
    }
}
