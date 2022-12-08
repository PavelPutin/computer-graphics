package ru.vsu.cs.putin_p_a.task4.model.algorithms;

import java.io.ByteArrayOutputStream;
import java.util.function.Function;

class RLEComposer implements Function<byte[], byte[]> {
    @Override
    public byte[] apply(byte[] bytes) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        byte counter = 1;
        byte prev = bytes[1];

        for (int i = 1; i < bytes.length; i++) {
            byte current = bytes[i];
            if (current == prev && counter < 63) {
                counter++;
            } else {
                counter |= 0b1100_0000;
                byteStream.write(counter);
                byteStream.write(prev);

                counter = 1;
            }
            prev = current;
        }

        counter |= 0b1100_0000;
        byteStream.write(counter);
        byteStream.write(prev);


        return byteStream.toByteArray();
    }
}
