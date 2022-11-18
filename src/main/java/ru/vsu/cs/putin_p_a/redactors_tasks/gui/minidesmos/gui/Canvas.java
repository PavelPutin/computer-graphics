package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.BresenhamLineAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.LineDrawingAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.PixelSetter;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.WuLineAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.shapes2d.Point2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Map;

public class Canvas extends JPanel implements RasterUpdateListener, PixelSetter {
    public static final Color AXIS_COLOR = Color.BLACK;
    public static final Color GRID_LINES = Color.LIGHT_GRAY;
    public static final Color PLOT_COLOR = Color.RED;
    public static final Color CURVE_COLOR = Color.MAGENTA;
    private Graphics g;
    private LineDrawingAlgorithm lineDrawingAlgorithm = new BresenhamLineAlgorithm(this);
    private int mousePressPositionX, mousePressPositionY;
    private final StartPointTransformsController startPointTransformsController;
    private final CoordinateSystemGridGenerator coordinateSystemGridGenerator;
    private final PlotGenerator plotGenerator;
    private final CurveGenerator curveGenerator;

    public Canvas(
            StartPointTransformsController startPointTransformsController,
            CoordinateSystemGridGenerator coordinateSystemGridGenerator,
            PlotGenerator plotGenerator,
            CurveGenerator curveGenerator
    ) {
        super();
        this.startPointTransformsController = startPointTransformsController;
        this.coordinateSystemGridGenerator = coordinateSystemGridGenerator;
        coordinateSystemGridGenerator.addRasterUpdateListener(this);
        this.plotGenerator = plotGenerator;
        this.plotGenerator.addRasterUpdateListener(this);
        this.curveGenerator = curveGenerator;
        this.curveGenerator.addRasterUpdateListener(this);

        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                int rotationValue = e.getWheelRotation();

                double userScale = 0.5;
                if (rotationValue > 0) {
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

        int[] plotYValues = plotGenerator.plot(startPointPosition);
        drawPlot(plotYValues);

        java.util.List<Point2d> points = curveGenerator.rasterPoints(startPointPosition);
        drawCurve(points);
    }

    private void drawPlot(int[] plotYValues) {
        for (int x = 1; x < plotYValues.length; x++) {
            int y1 = plotYValues[x - 1],
                    y2 = plotYValues[x];
            if (containsPixel(x - 1, y1) && containsPixel(x, y2)) {
                lineDrawingAlgorithm.drawLine(x - 1, y1, x, y2, PLOT_COLOR);
            }
        }
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
        CanvasCoordinateSystem cs = startPointTransformsController.getCurrent().canvasCoordinateSystem();
        for (Map.Entry<Integer, Double> pivotX : grid.verticalPivotsX().entrySet()) {
            int x = pivotX.getKey();
            lineDrawingAlgorithm.drawLine(x, 0, x, getHeight(), GRID_LINES);

            double xWidth = Math.abs(cs.getEndX() - cs.getStartX());
            String text = formatText(pivotX.getValue(), xWidth);
            int textY = getTextCoordinate(grid.centerY(), 0, getHeight(), 2);
            g.drawString(text, x, textY);
        }

        for (Map.Entry<Integer, Double> pivotY : grid.horizontalPivotsY().entrySet()) {
            int y = pivotY.getKey();
            lineDrawingAlgorithm.drawLine(0, y, getWidth(), y, GRID_LINES);

            double yWidth = Math.abs(cs.getEndY() - cs.getStartX());
            String text = formatText(pivotY.getValue(), yWidth);
            int gap = text.length() * 8;
            int textX = getTextCoordinate(grid.centerX(), 0, getWidth(), gap);
            g.drawString(text, textX, y);
        }

        lineDrawingAlgorithm.drawLine(grid.centerX(), 0, grid.centerX(), getHeight(), AXIS_COLOR);
        lineDrawingAlgorithm.drawLine(0, grid.centerY(), getWidth(), grid.centerY(), AXIS_COLOR);
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

    private String formatText(double value, double realWidth) {
        DecimalFormat pattern = new DecimalFormat("#.###");

        if (realWidth > 10_000 || realWidth < 1) {
            pattern = new DecimalFormat("#.##E0");
        }
        return pattern.format(value);
    }

    private void drawCurve(java.util.List<Point2d> points) {
        for (int i = 1; i < points.size(); i++) {
            Point2d from = points.get(i - 1),
                    to = points.get(i);
            int x1 = from.getX().intValue(),
                    y1 = from.getY().intValue(),
                    x2 = to.getX().intValue(),
                    y2 = to.getY().intValue();
            lineDrawingAlgorithm.drawLine(x1, y1, x2, y2, CURVE_COLOR);
        }
    }
}
