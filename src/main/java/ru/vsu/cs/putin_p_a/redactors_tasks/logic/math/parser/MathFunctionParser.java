package ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.parser;

import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.Number;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.functions.*;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations.Division;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations.Multiplication;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations.Subtraction;
import ru.vsu.cs.putin_p_a.redactors_tasks.logic.math.expression.operations.Sum;

import java.math.BigDecimal;

public class MathFunctionParser {
    private String source;
    private int pivot;

    public MathFunctionParser(String source) {
        this.source = source;
        pivot = 0;
    }

    public BasicExpression parse() {
        return expression();
    }

    private BasicExpression expression() {
        return sumAndSubtract();
    }

    private BasicExpression sumAndSubtract() {
        BasicExpression result = multiplicationAndDivision();

        while (!isEndOfLine() && (source.charAt(pivot) == '+' || source.charAt(pivot) == '-')) {
            char operation = source.charAt(pivot++);
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
        BasicExpression result;
        if (!isEndOfLine() && source.charAt(pivot) == '(') {
            result = parentheses();
        } else {
            result = term();
        }

        while (!isEndOfLine() && (source.charAt(pivot) == '*' || source.charAt(pivot) == '/')) {
            char operation = source.charAt(pivot++);
            BasicExpression right;
            space();
            if (!isEndOfLine() && source.charAt(pivot) == '(') {
                right = parentheses();
            } else {
                right = term();
            }
            if (operation == '*') {
                result = new Multiplication(result, right);
            } else if (operation == '/') {
                result = new Division(result, right);
            }
        }
        return result;
    }



    private BasicExpression parentheses() {
        space();
        boolean brackets = false;
        if (source.charAt(pivot) == '(') {
            brackets = true;
            pivot++;
            space();
        }

        BasicExpression result = expression();

        space();
        if (brackets) {
            if (source.charAt(pivot) == ')') {
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
        String result = "";
        if (Character.isAlphabetic(source.charAt(pivot))) {
            while (!isEndOfLine() && Character.isAlphabetic(source.charAt(pivot))) {
                result += source.charAt(pivot++);
            }
            space();
            if (source.charAt(pivot) == '(') {
                BasicExpression innerFunction = parentheses();
                if (result.equalsIgnoreCase("sin")) {
                    return new Sin(innerFunction);
                }
                if (result.equalsIgnoreCase("cos")) {
                    return new Cos(innerFunction);
                }
                if (result.equalsIgnoreCase("Tg")) {
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
        } else {
            if (source.charAt(pivot) == '-') {
                result += '-';
                pivot++;
            }

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
    }

    private void space() {
        while (!isEndOfLine() && source.charAt(pivot) == ' ') {
            pivot++;
        }
    }

    private boolean isEndOfLine() {
        return pivot >= source.length();
    }
}
