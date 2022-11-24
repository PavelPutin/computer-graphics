package ru.vsu.cs.putin_p_a.redactors_tasks.logic.minidesmos;

public interface ParsingErrorListener {
    void showErrorMessage(RuntimeException e);
    void clearErrorMessage();
}
