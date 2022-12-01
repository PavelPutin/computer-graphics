package ru.vsu.cs.putin_p_a.task4.composer.model.input.abstractions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

public interface ImageSource {
    void addImageInputListener(ImageInputListener listener);
    default void notifyImageInputListeners(Path path) {}
    default void notify(File file) {}
}
