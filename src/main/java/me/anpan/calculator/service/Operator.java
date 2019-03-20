package me.anpan.calculator.service;

public class Operator {
    private String symbol;
    private int prioty;

    public Operator(String symbol) {
        this.symbol = symbol;
        this.prioty = OperatorType.getPrioty(symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPrioty() {
        return prioty;
    }

    public boolean isHigherPriority(String symbol) {
        return prioty >= OperatorType.getPrioty(symbol);
    }

    public double calculate(double value1, double value2) {
        return OperatorType.calculate(symbol, value1, value2);
    }


}
