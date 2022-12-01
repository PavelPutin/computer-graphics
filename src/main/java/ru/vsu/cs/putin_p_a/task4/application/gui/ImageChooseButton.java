package ru.vsu.cs.putin_p_a.task4.application.gui;

import ru.vsu.cs.putin_p_a.task4.composer.model.input.abstractions.ImageInputListener;
import ru.vsu.cs.putin_p_a.task4.composer.model.input.abstractions.ImageSource;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageChooseButton extends JButton implements ImageSource {
    private JFileChooser fc;
    private List<ImageInputListener> listeners = new ArrayList<>();

    public ImageChooseButton() {
        super();
        fc = new JFileChooser();

        addActionListener(e -> {
            int responseValue = fc.showOpenDialog(getParent());

            if (responseValue == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                notify(file);
            }
        });
    }

    @Override
    public void addImageInputListener(ImageInputListener listener) {
        listeners.add(listener);
    }

    @Override
    public void notify(File file) {
        for (ImageInputListener listener : listeners) {
            listener.enterImage(file);
        }
    }
}
