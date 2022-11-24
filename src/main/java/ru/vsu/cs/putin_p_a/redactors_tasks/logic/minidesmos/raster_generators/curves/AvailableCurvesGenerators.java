package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.curves;

public enum AvailableCurvesGenerators {
    BEZIERS("Кривая Безье"),
    BSPLAIN("Б-сплайн"),
    HERMITE("Кривая Эрмита");

    private final String lable;
    AvailableCurvesGenerators(String lable) {
        this.lable = lable;
    }

    public String getLable() {
        return lable;
    }
}
