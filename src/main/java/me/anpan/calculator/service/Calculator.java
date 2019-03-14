package me.anpan.calculator.service;

import me.anpan.calculator.utils.CalculatorUtils;
import me.anpan.calculator.utils.ConvertToPostFix;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class Calculator {

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

    private double calculateByParamOfOperator(String param, double firstNum, double secondNum) throws Exception {

        if ("+".equals(param)) {
            return secondNum + firstNum;
        }

        if ("-".equals(param)) {
            return secondNum - firstNum;
        }

        if ("*".equals(param)) {
            return secondNum * firstNum;
        }

        if ("/".equals(param)) {
            return Math.round(secondNum / firstNum * 100000) / 100000.0;
        }

        throw new Exception("허용되지 않은 연산자 입니다.");
    }

}
