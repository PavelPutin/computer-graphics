package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.RasterUpdateListener;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.StartPointTransformsUpdatingListener;

import java.util.ArrayList;
import java.util.List;

abstract public class RasterGenerator implements StartPointTransformsUpdatingListener {
    private final List<RasterUpdateListener> listeners = new ArrayList<>();

    public void notifyListeners() {
        for (RasterUpdateListener listener : listeners) {
            listener.updateRasterView();
        }
    }

    public void addRasterUpdateListener(RasterUpdateListener listener) {
        listeners.add(listener);
    }

}
