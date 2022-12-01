package ru.vsu.cs.putin_p_a.task4.composer.model.input.abstractions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

public interface ImageInputListener {
    void enterImage(Path path);
    void enterImage(File file);

    void addImageConsumer(ImageConsumer consumer);
    void notifyImageConsumers(BufferedImage image);

    void addErrorHandler(ErrorHandler handler);
    void notifyErrorHandlers(Throwable throwable);
}
