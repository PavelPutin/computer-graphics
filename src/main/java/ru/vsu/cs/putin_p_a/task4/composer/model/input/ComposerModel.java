package ru.vsu.cs.putin_p_a.task4.composer.model.input;

import ru.vsu.cs.putin_p_a.task4.composer.model.input.abstractions.ErrorHandler;
import ru.vsu.cs.putin_p_a.task4.composer.model.input.abstractions.ImageConsumer;
import ru.vsu.cs.putin_p_a.task4.composer.model.input.abstractions.ImageInputListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ComposerModel implements ImageInputListener {
    private List<ImageConsumer> imageConsumers = new ArrayList<>();
    private List<ErrorHandler> errorHandlers = new ArrayList<>();
    private RLEComposer rleComposer;

    public ComposerModel() {
        rleComposer = new RLEComposer();
    }

    public void saveToFile(Path path, byte[] b) {
        byte[] composed = rleComposer.compose(b);
        try (FileOutputStream fos = new FileOutputStream(path.toFile()))  {
            fos.write(composed);
        } catch (IOException e) {
            notifyErrorHandlers(e);
        }
    }

    @Override
    public void enterImage(Path path) {
        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            byte[] input = fis.readAllBytes();
            Path composed = getComposedFilePath(path);
            saveToFile(composed, input);

            BufferedImage image = ImageIO.read(path.toFile());
            notifyImageConsumers(image);
        } catch (IOException e) {
            notifyErrorHandlers(e);
        }
    }

    @Override
    public void enterImage(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] input = fis.readAllBytes();
            Path composed = getComposedFilePath(Paths.get(file.getAbsolutePath()));
            saveToFile(composed, input);

            BufferedImage image = ImageIO.read(file);
            notifyImageConsumers(image);
        } catch (IOException e) {
            notifyErrorHandlers(e);
        }
    }

    @Override
    public void addImageConsumer(ImageConsumer consumer) {
        imageConsumers.add(consumer);
    }

    @Override
    public void notifyImageConsumers(BufferedImage image) {
        for (ImageConsumer consumer : imageConsumers) {
            consumer.accept(image);
        }
    }

    @Override
    public void addErrorHandler(ErrorHandler handler) {
        errorHandlers.add(handler);
    }

    @Override
    public void notifyErrorHandlers(Throwable throwable) {
        for (ErrorHandler handler : errorHandlers) {
            handler.accept(throwable);
        }
    }

    private Path getComposedFilePath(Path source) {
        String fileName = source.getName(source.getNameCount() - 1).toString().replaceFirst("[.][^.]+$", "");
        return source.getParent().resolve(fileName + ".rle");
    }
}
