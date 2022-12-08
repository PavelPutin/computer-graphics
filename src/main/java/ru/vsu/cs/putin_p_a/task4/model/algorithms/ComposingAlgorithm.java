package ru.vsu.cs.putin_p_a.task4.model.algorithms;

import java.util.function.Function;

public enum ComposingAlgorithm {
    RLE(new RLEComposer(), new RLEDecomposer()),
    LZW(new LZWComposer(), new LZWDecomposer());

    private final Function<byte[], byte[]> composer, decomposer;

    ComposingAlgorithm(Function<byte[], byte[]> composer, Function<byte[], byte[]> decomposer) {
        this.composer = composer;
        this.decomposer = decomposer;
    }

    public Function<byte[], byte[]> composer() {
        return composer;
    }

    public Function<byte[], byte[]> decomposer() {
        return decomposer;
    }
}
