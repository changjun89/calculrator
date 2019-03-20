package me.anpan.calculator.service;

import me.anpan.calculator.utils.CalculatorUtils;

import java.util.Stack;

public class PostfixCalculaor {

    public double calculate(String[] expresion) {
        return calculateResult(expresion);
    }

    public double calculate(String expresion) {
        String [] expr = splitByBlank(expresion);
        return calculateResult(expr);
    }

    private double calculateResult(String[] expresion ) {
        Stack<Double> number = new Stack<>();
        for (String arg : expresion) {
            if (!CalculatorUtils.isOperator(arg)) {
                pushDouble(arg,number);
                continue;
            }
            number.push(OperatorType.calculate(arg, number.pop(), number.pop()));
        }
        return number.pop();
    }

    private String[] splitByBlank(String expr) {
        return expr.split(" ");
    }

    private void pushDouble(String arg,Stack<Double> number) {
        number.push(Double.parseDouble(arg));
    }
}
