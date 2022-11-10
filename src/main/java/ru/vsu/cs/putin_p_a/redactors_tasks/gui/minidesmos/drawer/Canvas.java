package ru.vsu.cs.putin_p_a.redactors_tasks.gui.minidesmos.drawer;

import java.awt.*;

public interface Canvas {
    void setPixel(int x, int y, Color color);
    boolean containsPixel(int x, int y);
}
