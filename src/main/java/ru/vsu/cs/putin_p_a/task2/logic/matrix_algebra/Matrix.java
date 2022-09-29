package ru.vsu.cs.putin_p_a.task2.logic.matrix_algebra;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private final List<List<Double>> values;

    {
        this.values = new ArrayList<>();
    }

    public Matrix(List<List<Double>> values) {
        for (List<Double> row : values) {
            List<Double> newRow = new ArrayList<>();
            this.values.add(newRow);
            newRow.addAll(row);
        }
    }

    public Matrix(double[][] values) {
        for (double[] row : values) {
            List<Double> newRow = new ArrayList<>();
            this.values.add(newRow);
            for (Double value : row) {
                newRow.add(value);
            }
        }
    }

    public List<List<Double>> getValues() {
        return new ArrayList<>(values);
    }

    public double getValue(int i, int j) {
        return values.get(i).get(j);
    }

    public int getRowsNumber() {
        return values.size();
    }

    public int getColumnNumber() {
        return values.get(0).size();
    }

    public Matrix multiply(Matrix other) {
        List<List<Double>> resultValues = new ArrayList<>();
        for (List<Double> value : this.values) {
            List<Double> newRow = new ArrayList<>();
            resultValues.add(newRow);
            for (int columnIndex = 0; columnIndex < other.getColumnNumber(); columnIndex++) {
                double result = 0;
                for (int k = 0; k < other.getRowsNumber(); k++) {
                    result += value.get(k) * other.getValue(k, columnIndex);
                }
                newRow.add(result);
            }
        }
        return new Matrix(resultValues);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (List<Double> row : values) {
            String firstChar = "|";
            String lastChar = "|";
            if (i == 0) {
                firstChar = "⌈";
                lastChar = "⌉";
            } else if (i == values.size() - 1) {
                firstChar = "⌊";
                lastChar = "⌋";
            }
            if (values.size() == 1) {
                firstChar = "(";
                lastChar = ")";
            }
            sb.append(firstChar);
            boolean printTab = false;
            for (Double value : row) {
                if (printTab) {
                    sb.append("\t");
                }
                sb.append(value);
                printTab = true;
            }
            sb.append(lastChar).append("\n");
            i++;
        }

        return sb.toString();
    }
}
