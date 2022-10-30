package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils.matrix_algebra;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.utils.MathConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
    private final List<List<BigDecimal>> values = new ArrayList<>();

    public Matrix(double[][] values) {
        for (double[] row : values) {
            List<BigDecimal> newRow = new ArrayList<>();
            this.values.add(newRow);
            for (Double value : row) {
                newRow.add(BigDecimal.valueOf(value));
            }
        }
    }

    public Matrix(List<List<BigDecimal>> values) {
        for (List<BigDecimal> row : values) {
            List<BigDecimal> newRow = new ArrayList<>();
            this.values.add(newRow);
            newRow.addAll(row);
        }
    }

    public Matrix(BigDecimal[][] values) {
        for (BigDecimal[] row : values) {
            List<BigDecimal> newRow = new ArrayList<>();
            this.values.add(newRow);
            newRow.addAll(Arrays.asList(row));
        }
    }

    public List<List<BigDecimal>> getValues() {
        List<List<BigDecimal>> doubleValues = new ArrayList<>();
        for (List<BigDecimal> row : values) {
            List<BigDecimal> newRow = new ArrayList<>();
            doubleValues.add(newRow);
            newRow.addAll(row);
        }
        return doubleValues;
    }

    public BigDecimal getValue(int i, int j) {
        return values.get(i).get(j);
    }

    public int getRowsNumber() {
        return values.size();
    }

    public int getColumnNumber() {
        return values.get(0).size();
    }

    public Matrix multiply(Matrix other) {
        List<List<BigDecimal>> resultValues = new ArrayList<>();
        for (List<BigDecimal> row : this.values) {
            List<BigDecimal> newRow = new ArrayList<>();
            resultValues.add(newRow);
            for (int columnIndex = 0; columnIndex < other.getColumnNumber(); columnIndex++) {
                BigDecimal result = BigDecimal.ZERO;
                for (int k = 0; k < other.getRowsNumber(); k++) {
                    result = result.add(row.get(k).multiply(other.getValue(k, columnIndex)));
                }
                newRow.add(result);
            }
        }
        return new Matrix(resultValues);
    }

    public BigDecimal getDeterminant() {
        if (getColumnNumber() == 1) {
            return values.get(0).get(0);
        }

        BigDecimal determinant = BigDecimal.ZERO;
        for (int i = 0; i < getColumnNumber(); i++) {
            determinant = determinant.add(values.get(0).get(i).multiply(getAlgebraicAdjunct(0, i)));
        }
        return determinant;
    }

    public BigDecimal getAlgebraicAdjunct(int i, int j) {
        BigDecimal k = (i + j) % 2 == 0 ? BigDecimal.ONE : BigDecimal.ONE.multiply(BigDecimal.valueOf(-1));
        return k.multiply(getMinor(i, j));
    }

    public BigDecimal getMinor(int i, int j) {
        List<List<BigDecimal>> minorValues = new ArrayList<>();
        for (int row = 0; row < getRowsNumber(); row++) {
            if (row != i) {
                List<BigDecimal> minorRow = new ArrayList<>();
                for (int column = 0; column < getColumnNumber(); column++) {
                    if (column != j) {
                        minorRow.add(values.get(row).get(column));
                    }
                }
                minorValues.add(minorRow);
            }
        }
        return new Matrix(minorValues).getDeterminant();
    }

    public Matrix getTransposed() {
        BigDecimal[][] transposed = new BigDecimal[getRowsNumber()][getColumnNumber()];
        for (int i = 0; i < getRowsNumber(); i++) {
            for (int j = i; j < getColumnNumber(); j++) {
                transposed[i][j] = values.get(j).get(i);
                transposed[j][i] = values.get(i).get(j);
            }
        }
        return new Matrix(transposed);
    }

    public Matrix getInverse() {
        BigDecimal det = getDeterminant();
        if (det.equals(BigDecimal.ZERO)) {
            throw new RuntimeException("Нельзя найти обратную матрицу, если определитель равен 0");
        }

        List<List<BigDecimal>> inverseValues = new ArrayList<>();
        for (int i = 0; i < getRowsNumber(); i++) {
            List<BigDecimal> row = new ArrayList<>();
            for (int j = 0; j < getColumnNumber(); j++) {
                row.add(getAlgebraicAdjunct(i, j).divide(det, MathConstants.DIVISION_SCALE, RoundingMode.HALF_UP));
            }
            inverseValues.add(row);
        }

        return new Matrix(inverseValues).getTransposed();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (List<BigDecimal> row : values) {
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
            for (BigDecimal value : row) {
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
