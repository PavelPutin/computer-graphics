package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

public class Model {
    private final StartPointTransformsController startPointTransformsController;
    private final CoordinateSystemGridGenerator coordinateSystemGridGenerator;

    public Model() {
        startPointTransformsController = new StartPointTransformsController();
        coordinateSystemGridGenerator = new CoordinateSystemGridGenerator();
        startPointTransformsController.addStartPointPositionUpdatingListener(coordinateSystemGridGenerator);
    }

    public StartPointTransformsController getStartPointPositionController() {
        return startPointTransformsController;
    }

    public CoordinateSystemGridGenerator getCoordinateSystemGridGenerator() {
        return coordinateSystemGridGenerator;
    }
}
