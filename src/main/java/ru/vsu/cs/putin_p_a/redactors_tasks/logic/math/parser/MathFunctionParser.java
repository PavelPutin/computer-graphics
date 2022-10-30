package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.values.special.E;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.values.Number;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.functions.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.values.special.Pi;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.values.Variable;

import java.math.BigDecimal;

public class MathFunctionParser {
    private String source;
    private int pivot;

    public MathFunctionParser(String source) {
        this.source = source;
        pivot = 0;
    }

    public BasicExpression parse() {
        space();
        return expression();
    }

    private BasicExpression expression() {
        return sumAndSubtract();
    }

    private BasicExpression sumAndSubtract() {
        BasicExpression result = multiplicationAndDivision();

        while (!isEndOfLine() && (source.charAt(pivot) == '+' || source.charAt(pivot) == '-')) {
            char operation = source.charAt(pivot++);
            space();
            BasicExpression right = multiplicationAndDivision();
            if (operation == '+') {
                result = new Sum(result, right);
            } else if (operation == '-') {
                result = new Subtraction(result, right);
            }
        }
        return result;
    }

    private BasicExpression multiplicationAndDivision() {
        BasicExpression result = exponential();

        while (!isEndOfLine() && (source.charAt(pivot) == '*' || source.charAt(pivot) == '/')) {
            char operation = source.charAt(pivot++);
            space();
            BasicExpression right = exponential();
            if (operation == '*') {
                result = new Multiplication(result, right);
            } else if (operation == '/') {
                result = new Division(result, right);
            }
        }
        return result;
    }

    private BasicExpression exponential() {
        BasicExpression result;
        if (!isEndOfLine() && isBracket()) {
            result = parentheses();
        } else if (!isEndOfLine() && source.charAt(pivot) == '-') {
            result = unaryMinus();
        } else {
            result = term();
        }

        while (!isEndOfLine() && source.charAt(pivot) == '^') {
            BasicExpression right;
            pivot++;
            space();
            if (!isEndOfLine() && isBracket()) {
                right = parentheses();
            } else if (!isEndOfLine() && source.charAt(pivot) == '-') {
                right = unaryMinus();
            } else {
                right = term();
            }
            result = new Exponential(result, right);
        }
        return result;
    }

    private BasicExpression unaryMinus() {
        if (!isEndOfLine() && source.charAt(pivot) == '-') {
            pivot++;
            space();
        }
        BasicExpression inner;
        if (!isEndOfLine() && isBracket()) {
            inner = parentheses();
        } else if (!isEndOfLine() && source.charAt(pivot) == '-') {
            inner = unaryMinus();
        } else {
            inner = term();
        }
        space();
        return new UnaryMinus(inner);
    }

    private BasicExpression parentheses() {
        space();
        char emptyChar = '0';
        char bracket = emptyChar;
        if (isBracket()) {
            bracket = source.charAt(pivot);
            pivot++;
            space();
        }

        BasicExpression result = expression();
        if (bracket == '|') {
            result = new Abs(result);
        }

        space();
        if (bracket != emptyChar) {
            if (source.charAt(pivot) == ')' || source.charAt(pivot) == '|') {
                pivot++;
                space();
            } else {
                throw new RuntimeException("Неправильно расставлены скобки");
            }
        }
        return result;
    }

    private BasicExpression term() {
        space();
        if (Character.isAlphabetic(source.charAt(pivot))) {
            return variableAndFunctionAndSpecialValue();
        } else {
            return number();
        }
    }

    private BasicExpression variableAndFunctionAndSpecialValue() {
        String result = "";
        while (!isEndOfLine() && Character.isAlphabetic(source.charAt(pivot))) {
            result += source.charAt(pivot++);
        }
        space();
        if (!isEndOfLine() && source.charAt(pivot) == '(') {
            BasicExpression innerFunction = parentheses();
            if (result.equalsIgnoreCase("sin")) {
                return new Sin(innerFunction);
            }
            if (result.equalsIgnoreCase("cos")) {
                return new Cos(innerFunction);
            }
            if (result.equalsIgnoreCase("tg")) {
                return new Tg(innerFunction);
            }
            if (result.equalsIgnoreCase("ctg")) {
                return new Ctg(innerFunction);
            }
            if (result.equalsIgnoreCase("sqrt")) {
                return new Sqrt(innerFunction);
            }
        }
        if (result.equalsIgnoreCase("pi")) {
            return new Pi();
        }
        if (result.equalsIgnoreCase("e")) {
            return new E();
        }
        return new Variable(result);
    }

    private BasicExpression number() {
        String result = "";
        int dotCounter = 0;
        while (!isEndOfLine() && (Character.isDigit(source.charAt(pivot)) || source.charAt(pivot) == '.')) {
            if (source.charAt(pivot) == '.') {
                dotCounter++;
                if (dotCounter > 1) {
                    throw new RuntimeException("Много точек");
                }
            }
            result += source.charAt(pivot++);
        }
        space();
        double parsed = Double.parseDouble(result);
        return new Number(new BigDecimal(parsed));
    }

    private void space() {
        while (!isEndOfLine() && source.charAt(pivot) == ' ') {
            pivot++;
        }
    }

    private boolean isEndOfLine() {
        return pivot >= source.length();
    }

    private boolean isBracket() {
        return source.charAt(pivot) == '(' || source.charAt(pivot) == '|';
    }
}
