package ru.vsu.cs.putin_p_a.task4.decomposer.model;

import java.io.ByteArrayOutputStream;

public class RLEDecomposer {
    private byte current;
    public byte[] decompose(byte[] b) {
        if (b.length == 0) {
            return new byte[0];
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < b.length; i++) {
            current = b[i];
            if (isCurrentCounter()) {
                byte counter = low6bitsCurrent();
                byte value = b[++i];
                for (int j = 0; j < counter; j++) {
                    byteArrayOutputStream.write(value);
                }
            } else {
                byteArrayOutputStream.write(current);
            }
        }

        return byteArrayOutputStream.toByteArray();
    }

    private boolean isCurrentCounter() {
        return (current & 0b1100_0000) == 0b1100_0000;
    }

    private byte low6bitsCurrent() {
        return (byte) (current & 0b0011_1111);
    }
}
