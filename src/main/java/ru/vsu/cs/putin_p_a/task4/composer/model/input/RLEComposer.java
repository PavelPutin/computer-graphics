package ru.vsu.cs.putin_p_a.task4.composer.model.input;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class RLEComposer {
    public byte[] compose(byte[] b) {
        if (b.length == 0) {
            return new byte[0];
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte counter = 1;
        byte previous = b[0];
        for (int i = 1; i < b.length; i++) {
            byte current = b[i];

            if (current == previous && counter < 63) {
                counter++;
            } else {
                counter |= 0b1100_0000;
                byteArrayOutputStream.write(counter);
                byteArrayOutputStream.write(previous);
                counter = 1;
            }

            previous = current;
        }

        counter |= 0b1100_0000;

        byteArrayOutputStream.write(counter);
        byteArrayOutputStream.write(previous);
        return byteArrayOutputStream.toByteArray();
    }
}
