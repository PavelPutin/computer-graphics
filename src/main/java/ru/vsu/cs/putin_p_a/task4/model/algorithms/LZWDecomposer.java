package ru.vsu.cs.putin_p_a.task4.model.algorithms;

import java.util.function.Function;

class LZWDecomposer implements Function<byte[], byte[]> {
    @Override
    public byte[] apply(byte[] bytes) {
        System.out.println("LZW.decompose");
        return bytes;
    }
}
