package me.anpan.calculator.service;

import me.anpan.calculator.utils.CalculatorUtils;
import me.anpan.calculator.utils.ConvertToPostFix;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

@Service
public class Calculator {
    enum Operator {
        ADD("+", (value1, value2) -> value1 + value2),
        SUBTRACT("-", (value1, value2) -> value1 - value2),
        MULTIPLY("*", (value1, value2) -> value1 * value2),
        DIVISION("/", (value1, value2) -> Math.round(value1 / value2 * 100000) / 100000.0);

        private final String symbol;
        private DoubleBinaryOperator expression;

        Operator(String symbol, DoubleBinaryOperator expression) {
            this.symbol = symbol;
            this.expression = expression;
        }

        public static Operator getOperator(String symbol) {
            Operator[] values = Operator.values();

            for (Operator operator : values) {
                if (operator.symbol.equals(symbol)) {
                    return operator;
                }
            }
            throw new NoSuchElementException();
        }

        public double calculate(double value1, double value2) {
            return expression.applyAsDouble(value1, value2);
        }
    }

    public double calculate(String input) throws Exception {
        ConvertToPostFix convertToPostFix = new ConvertToPostFix();
        return calcatePostfix(convertToPostFix.convertToPostfix(input));
    }

    public double calcatePostfix(String expr) throws Exception {
        String[] split = splitByBlank(expr);
        Stack<Double> number = new Stack<>();

        for (String arg : split) {
            if (!CalculatorUtils.isOperator(arg)) {
                number.push(Double.parseDouble(arg));
                continue;
            }

            number.push(calculateByParamOfOperator(arg, number.pop(), number.pop()));
        }
        return number.pop();
    }

    private String[] splitByBlank(String expr) {
        return expr.split(" ");
    }

    private double calculateByParamOfOperator(String param, double firstNum, double secondNum) {

        return Operator.getOperator(param).calculate(secondNum, firstNum);

    }

}
