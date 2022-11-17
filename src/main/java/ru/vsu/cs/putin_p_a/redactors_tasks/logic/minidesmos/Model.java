package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

public class Model {
    private final StartPointTransformsController startPointTransformsController;
    private final CoordinateSystemGridGenerator coordinateSystemGridGenerator;
    private final PlotGenerator plotGenerator;

    public Model() {
        startPointTransformsController = new StartPointTransformsController();
        coordinateSystemGridGenerator = new CoordinateSystemGridGenerator();
        startPointTransformsController.addStartPointPositionUpdatingListener(coordinateSystemGridGenerator);
        plotGenerator = new PlotGenerator();
    }

    public PlotGenerator getPlotGenerator() {
        return plotGenerator;
    }

    public StartPointTransformsController startPointTransformsController() {
        return startPointTransformsController;
    }

    public CoordinateSystemGridGenerator getCoordinateSystemGridGenerator() {
        return coordinateSystemGridGenerator;
    }
}
