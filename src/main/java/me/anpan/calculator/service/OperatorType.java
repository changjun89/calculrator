package me.anpan.calculator.service;

import java.util.NoSuchElementException;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Stream;

enum OperatorType {

    ADD("+", 1, (value1, value2) -> value1 + value2),
    SUBTRACT("-", 1, (value1, value2) -> value1 - value2),
    MULTIPLY("*", 2, (value1, value2) -> value1 * value2),
    DIVISION("/", 2, (value1, value2) -> Math.round(value1 / value2 * 100000) / 100000.0),
    RIGHT_BRACKET("(", 0, (value1, value2) -> 0),
    LEFT_BRACKET(")", 0, (value1, value2) -> 0);

    private String symbol;
    private int priority;
    private DoubleBinaryOperator expression;

    OperatorType(String symbol, int priority, DoubleBinaryOperator expression) {
        this.symbol = symbol;
        this.priority = priority;
        this.expression = expression;
    }

    protected static int getPrioty(String symbol) {
        return getOperator(symbol).priority;
    }

    private static OperatorType getOperator(String symbol) {
        OperatorType[] values = OperatorType.values();
        return Stream.of(values)
                .filter(operator -> operator.symbol.equals(symbol))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    protected static double calculate(String symbol, double value1, double value2) {
        return getOperator(symbol).expression.applyAsDouble(value2, value1);
    }

    protected static boolean isOperator(String symbol) {
        OperatorType[] values = OperatorType.values();
        return Stream.of(values)
                .filter(operator -> operator.symbol.equals(symbol))
                .findAny()
                .isPresent();
    }

    protected static boolean isOperator(Character symbol) {
        OperatorType[] values = OperatorType.values();
        return Stream.of(values)
                .filter(operator -> operator.symbol.equals(symbol.toString()))
                .findAny()
                .isPresent();
    }

    protected static boolean isBracket(String symbol) {
        return "()".contains(symbol);
    }
    protected static boolean isOperatorExcludeBracket(String symbol) {
        return "+-*/".contains(symbol);
    }

}
