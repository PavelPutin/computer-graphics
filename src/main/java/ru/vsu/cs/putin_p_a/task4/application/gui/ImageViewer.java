package ru.vsu.cs.putin_p_a.task4.application.gui;

import ru.vsu.cs.putin_p_a.task4.composer.model.input.abstractions.ImageConsumer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageViewer extends JPanel implements ImageConsumer {
    private BufferedImage imageToDraw;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (imageToDraw != null) {
            g.drawImage(imageToDraw, 0, 0, null);
        }
    }

    @Override
    public void accept(BufferedImage imageNamedContainer) {
        imageToDraw = imageNamedContainer;
        repaint();
    }
}
