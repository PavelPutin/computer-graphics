package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.BresenhamLineAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.LineDrawingAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.PixelSetter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.coordinate_system.CoordinateSystemGrid;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.curves.BeziersCurveGenerator;
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
    private Model model;
    private LineDrawingAlgorithm lineDrawingAlgorithm = new BresenhamLineAlgorithm(this);
    private int mousePressPositionX, mousePressPositionY;

    public Canvas(Model model) {
        super();
        this.model = model;
        model.getCoordinateSystemGridGenerator().addRasterUpdateListener(this);
        model.getPlotGenerator().addRasterUpdateListener(this);
        model.getCurveGenerator().addRasterUpdateListener(this);

        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                int rotationValue = e.getWheelRotation();

                double userScale = 0.5;
                if (rotationValue > 0) {
                    userScale = 2;
                }
                model.getStartPointTransformsController().updateUserScale(e.getX(), e.getY(), userScale);
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
                model.getStartPointTransformsController().updateTranslation(mousePressPositionX, mousePressPositionY, e.getX(), e.getY());
                mousePressPositionX = e.getX();
                mousePressPositionY = e.getY();
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                model.getStartPointTransformsController().updateCanvasScale(-10, 10, getWidth(), getHeight());
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.g = g;
        StartPointTransforms startPointPosition = model.getStartPointTransformsController().getCurrent();
        CoordinateSystemGrid grid = model.getCoordinateSystemGridGenerator().getCoordinateSystemGrid(startPointPosition);
        drawGrid(grid);

        java.util.List<Point2d> plotYValues = model.getPlotGenerator().plot(startPointPosition, grid);
        drawPlot(plotYValues);

        java.util.List<Point2d> points = model.getCurveGenerator().rasterPoints(startPointPosition);
        drawCurve(points);
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
        CanvasCoordinateSystem cs = model.getStartPointTransformsController().getCurrent().canvasCoordinateSystem();
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

    private void drawPlot(java.util.List<Point2d> plotYValues) {
//        System.out.println("draw plot: " + plotYValues.size());
//        for (Point2d point2d : plotYValues) {
//            System.out.println(point2d.getX().doubleValue() + " " + point2d.getY().doubleValue());
//        }
        drawCurve(plotYValues);
    }

    private void drawCurve(java.util.List<Point2d> points) {
        for (int i = 1; i < points.size(); i++) {
            Point2d from = points.get(i - 1),
                    to = points.get(i);
            int x1 = from.getX().intValue(),
                    y1 = from.getY().intValue(),
                    x2 = to.getX().intValue(),
                    y2 = to.getY().intValue();

            if (containsPixel(x1, y1) || containsPixel(x2, y2)) {
                lineDrawingAlgorithm.drawLine(x1, y1, x2, y2, CURVE_COLOR);
            }
        }
    }
}
