package ru.vsu.cs.putin_p_a.task4.model.algorithms;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Objects;

public class LZWString {
    private final ByteArrayOutputStream bytes;

    public LZWString() {
        bytes = new ByteArrayOutputStream();
    }

    public void addByte(byte b) {
        bytes.write(b);
    }

    byte[] getBytes() {
        return bytes.toByteArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LZWString lzwString = (LZWString) o;

        byte[] thisBytes = getBytes(),
                oBytes = lzwString.getBytes();

        if (thisBytes.length != oBytes.length) {
            return false;
        }

        for (int i = 0; i < thisBytes.length; i++) {
            if (thisBytes[i] != oBytes[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getBytes());
    }

    @Override
    public String toString() {
        return Arrays.toString(getBytes());
    }
}
