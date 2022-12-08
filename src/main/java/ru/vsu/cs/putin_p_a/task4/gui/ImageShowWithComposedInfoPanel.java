package ru.vsu.cs.putin_p_a.task4.gui;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageShowWithComposedInfoPanel extends ImageShowPanel {
    private JLabel composedSizeLabel;
    public ImageShowWithComposedInfoPanel(BufferedImage initialImage) {
        super(initialImage);
        composedSizeLabel = new JLabel("Размер сжатого файла");
        add(composedSizeLabel);
    }

    public void setImage(BufferedImage image, byte[] b) {
        super.setImage(image);

        composedSizeLabel.setText("Размер сжатого файла " + getSize(b));
    }
}
