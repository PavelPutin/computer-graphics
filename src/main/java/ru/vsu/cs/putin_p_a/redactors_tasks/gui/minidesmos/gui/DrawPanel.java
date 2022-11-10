package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.gui;

import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.BresenhamLineAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.Canvas;
import ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer.LineDrawingAlgorithm;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser.Parameter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.raster_generator.ExpressionInputUI;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.raster_generator.Plotter;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.raster_generator.RasterGenerator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;

public class DrawPanel extends JPanel implements Canvas {
    private final RasterGenerator rasterGenerator;
    private LineDrawingAlgorithm lineDrawingAlgorithm;
    private Graphics g;

    public DrawPanel(RasterGenerator rasterGenerator) {
        super();
        setBorder(new LineBorder(Color.BLUE));
        lineDrawingAlgorithm = new BresenhamLineAlgorithm();
        this.rasterGenerator = rasterGenerator;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.g = g;
        List<Map<Integer, String>> pivotDots = rasterGenerator.getGrid(this.getWidth(), this.getHeight());

        for (Map.Entry<Integer, String> pivot : pivotDots.get(0).entrySet()) {
            Color c = pivot.getValue().equals("0") ? Color.GREEN : Color.BLACK;
            lineDrawingAlgorithm.drawLine(this, 1, pivot.getKey(), 0, pivot.getKey(), getHeight(), c);
            g.drawString(pivot.getValue(), pivot.getKey(), g.getFont().getSize());
        }

        for (Map.Entry<Integer, String> pivot : pivotDots.get(1).entrySet()) {
            Color c = pivot.getValue().equals("0") ? Color.GREEN : Color.BLACK;
            lineDrawingAlgorithm.drawLine(this, 1, 0, pivot.getKey(), getWidth(), pivot.getKey(), c);
            g.drawString(pivot.getValue(), 0, pivot.getKey());
        }

        int[] raster = rasterGenerator.getRaster(this.getWidth(), this.getHeight());
        for (int i = 1; i < this.getWidth(); i++) {
            if (containsPixel(i - 1, raster[i - 1]) || containsPixel(i, raster[i])) {
                lineDrawingAlgorithm.drawLine(this, 3, i - 1, raster[i - 1], i, raster[i], Color.BLUE);
            }
        }
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
        return 0 <= x && x < getWidth() && 0 <= y && y < getHeight();
    }
}
