package me.anpan.calculator.service;

import java.util.NoSuchElementException;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Stream;

public enum  OperatorType {
    ADD("+", (value1, value2) -> value1 + value2),
    SUBTRACT("-", (value1, value2) -> value1 - value2),
    MULTIPLY("*", (value1, value2) -> value1 * value2),
    DIVISION("/", (value1, value2) -> Math.round(value1 / value2 * 100000) / 100000.0);

    private String symbol;
    private DoubleBinaryOperator expression;

    OperatorType(String symbol, DoubleBinaryOperator expression) {
        this.symbol = symbol;
        this.expression = expression;
    }

    private static OperatorType getOperator(String symbol) {
        OperatorType[] values = OperatorType.values();
        return Stream.of(values)
                .filter(operator -> operator.symbol.equals(symbol))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public static double calculate(String symbol,double value1, double value2) {
        return getOperator(symbol).expression.applyAsDouble(value2, value1);
    }
}
