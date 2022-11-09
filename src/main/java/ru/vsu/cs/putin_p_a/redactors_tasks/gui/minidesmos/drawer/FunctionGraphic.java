package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class FunctionGraphic extends BufferedImage {
    public FunctionGraphic(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public FunctionGraphic(int width, int height, int imageType, IndexColorModel cm) {
        super(width, height, imageType, cm);
    }

    public FunctionGraphic(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
        super(cm, raster, isRasterPremultiplied, properties);
    }

    public boolean containPixel(int x, int y) {
        return 0 <= x && x < getWidth() && 0 <= y && y < getHeight();
    }
}
