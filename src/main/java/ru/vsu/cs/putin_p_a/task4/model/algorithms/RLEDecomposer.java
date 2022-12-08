package ru.vsu.cs.putin_p_a.task4.model.algorithms;

import java.io.ByteArrayOutputStream;
import java.util.function.Function;

class RLEDecomposer implements Function<byte[], byte[]> {
    @Override
    public byte[] apply(byte[] bytes) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        for (int i = 0; i < bytes.length; i++) {
            byte current = bytes[i];
            if (isCounter(current)) {
                byte counter = (byte) (current & 0b0011_1111);
                byte value = bytes[++i];
                for (int j = 0; j < counter; j++) {
                    byteStream.write(value);
                }
            }
        }

        return byteStream.toByteArray();
    }

    private boolean isCounter(byte value) {
        return (value & 0b1100_0000) == 0b1100_0000;
    }
}
