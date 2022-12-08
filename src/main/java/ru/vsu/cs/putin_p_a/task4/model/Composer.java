package ru.vsu.cs.putin_p_a.task4.model;

import ru.vsu.cs.putin_p_a.task4.model.algorithms.ComposingAlgorithm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Composer {
    private static final String ROOT = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final String SOURCE_PLACEHOLDER_PATH = ROOT + "source_placeholder.png";
    private static final String DECOMPOSED_PLACEHOLDER_PATH = ROOT + "decomposed_placeholder.png";

    private static final ComposingAlgorithm DEFAULT = ComposingAlgorithm.RLE;

    private BufferedImage origin, decomposed;
    private byte[] composed = new byte[0];
    private ComposingAlgorithm currentAlgorithm = DEFAULT;

    public Composer() throws IOException {
        origin = ImageIO.read(new File(SOURCE_PLACEHOLDER_PATH));
        decomposed = ImageIO.read(new File(DECOMPOSED_PLACEHOLDER_PATH));
    }

    public void compose(BufferedImage image) {
        origin = image;
        Raster raster = origin.getRaster();
        DataBufferByte bufferByte = (DataBufferByte) raster.getDataBuffer();
        byte[] source = bufferByte.getData();

        composed = currentAlgorithm.composer().apply(source);

        byte[] dBytes = currentAlgorithm.decomposer().apply(composed);
        decomposed = new BufferedImage(origin.getWidth(), origin.getHeight(), origin.getType());
        DataBufferByte dBufferByte = new DataBufferByte(dBytes, dBytes.length);
        Raster dRaster = Raster.createRaster(raster.getSampleModel(), dBufferByte, new Point());
        decomposed.setData(dRaster);
    }

    public BufferedImage getOrigin() {
        return origin;
    }

    public BufferedImage getDecomposed() {
        return decomposed;
    }

    public byte[] getComposed() {return Arrays.copyOf(composed, composed.length);}

    public void currentAlgorithm(ComposingAlgorithm algo) {
        this.currentAlgorithm = algo;
    }
}
