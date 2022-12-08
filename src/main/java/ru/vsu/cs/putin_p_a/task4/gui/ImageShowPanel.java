package ru.vsu.cs.putin_p_a.task4.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;

class ImageShowPanel extends JPanel {
    private JLabel imageLabel;
    private JLabel sizeLabel;
    public ImageShowPanel(BufferedImage initialImage) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Icon icon = new ImageIcon(initialImage);
        imageLabel = new JLabel(icon);

        JScrollPane imageScrollPane = new JScrollPane(imageLabel);
        imageScrollPane.setPreferredSize(new Dimension(400, 400));
        add(imageScrollPane);

        sizeLabel = new JLabel("Размер изображения");
        add(sizeLabel);
    }

    public void setImage(BufferedImage image) {
        Icon icon = new ImageIcon(image);
        imageLabel.setIcon(icon);

        Raster raster = image.getRaster();
        DataBufferByte bufferByte = (DataBufferByte) raster.getDataBuffer();
        byte[] b = bufferByte.getData();
        sizeLabel.setText("Размер изображения " + getSize(b));
    }

    protected String getSize(byte[] b) {
        int size = b.length;
        if (size < 1024) {
            return String.format("%d байт", size);
        } else if (size < 1_048_576) {
            size /= 1024;
            return String.format("%d Kб", size);
        } else if (size < 1_073_741_824) {
            size /= 1_048_576;
            return String.format("%d Мб", size);
        } else {
            size /= 1_073_741_824;
            return String.format("%d Гб", size);
        }
    }
}
