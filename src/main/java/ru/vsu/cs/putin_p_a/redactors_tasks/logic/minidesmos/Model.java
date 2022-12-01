package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.coordinate_system.CoordinateSystemGridGenerator;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.curves.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos.raster_generators.plot.PlotGenerator;

public class Model implements CurveGeneratorChangeListener {
    private final StartPointTransformsController startPointTransformsController;
    private final CoordinateSystemGridGenerator coordinateSystemGridGenerator;
    private final PlotGenerator plotGenerator;
    private CurveGenerator curveGenerator;

    public Model() {
        startPointTransformsController = new StartPointTransformsController();
        coordinateSystemGridGenerator = new CoordinateSystemGridGenerator();
        startPointTransformsController.addStartPointPositionUpdatingListener(coordinateSystemGridGenerator);
        plotGenerator = new PlotGenerator();
        curveGenerator = new BeziersCurveGenerator();
    }

    public CurveGenerator getCurveGenerator() {
        return curveGenerator;
    }

    public PlotGenerator getPlotGenerator() {
        return plotGenerator;
    }

    public StartPointTransformsController getStartPointTransformsController() {
        return startPointTransformsController;
    }

    public CoordinateSystemGridGenerator getCoordinateSystemGridGenerator() {
        return coordinateSystemGridGenerator;
    }

    @Override
    public void changeCurveGenerator(String curveGeneratorName) {
        System.out.println(curveGeneratorName);

        AvailableCurvesGenerators selectedCurveGenerator = AvailableCurvesGenerators.BEZIERS;
        for (AvailableCurvesGenerators value : AvailableCurvesGenerators.values()) {
            if (value.getLable().equals(curveGeneratorName)) {
                selectedCurveGenerator = value;
            }
        }

        System.out.println(selectedCurveGenerator);
        curveGenerator = switch (selectedCurveGenerator) {
            case BEZIERS -> new BeziersCurveGenerator();
            case BSPLAIN -> new BsplainCurveGenerator();
            case HERMITE -> new HermiteCurveGenerator();
        };
    }
}
