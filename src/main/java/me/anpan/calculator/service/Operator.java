package me.anpan.calculator.service;

public class Operator {
    private String symbol;
    private int priority;

    public Operator(String symbol) {
        this.symbol = symbol;
        this.priority = OperatorType.getPrioty(symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isHigherPriority(String symbol) {
        return priority >= OperatorType.getPrioty(symbol);
    }

    public double calculate(double value1, double value2) {
        return OperatorType.calculate(symbol, value1, value2);
    }


}
