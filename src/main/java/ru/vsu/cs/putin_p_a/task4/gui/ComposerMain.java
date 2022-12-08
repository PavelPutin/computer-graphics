package ru.vsu.cs.putin_p_a.task4.gui;

import ru.vsu.cs.putin_p_a.task4.model.Composer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ComposerMain extends JPanel {

    public ComposerMain(Composer model) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel imagesShowPanel = new JPanel();
        imagesShowPanel.setLayout(new BoxLayout(imagesShowPanel, BoxLayout.X_AXIS));
        imagesShowPanel.setAlignmentY(TOP_ALIGNMENT);
        add(imagesShowPanel);

        ImageShowPanel originPanel = new ImageShowPanel(model.getOrigin());
        imagesShowPanel.add(originPanel);

        ImageShowWithComposedInfoPanel decomposedPanel = new ImageShowWithComposedInfoPanel(model.getDecomposed());
        imagesShowPanel.add(decomposedPanel);

        JFileChooser fc = new JFileChooser();

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        add(controlPanel);

        JButton openImageButton = new JButton("Открыть изображение");
        openImageButton.addActionListener(e -> {
            int returnValue = fc.showOpenDialog(ComposerMain.this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(file);
                    model.compose(image);
                    originPanel.setImage(model.getOrigin());
                    decomposedPanel.setImage(model.getDecomposed(), model.getComposed());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ComposerMain.this, ex.getMessage());
                }
            }
        });
        add(openImageButton);
    }
}
