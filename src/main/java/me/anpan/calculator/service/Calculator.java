package me.anpan.calculator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class Calculator {


    public double calculate(String input) {
        return calcatePostfix(convertToPostfix(input));
    }

    public double calcatePostfix(String expr) {
        String[] split = expr.split(" ");
        Stack<Double> number = new Stack<>();
        double firstNum;
        double secondNum;

        for (String arg : split) {
            switch (arg) {
                case "+":
                    firstNum = number.pop();
                    secondNum = number.pop();
                    number.push(secondNum + firstNum);
                    break;
                case "-":
                    firstNum = number.pop();
                    secondNum = number.pop();
                    number.push(secondNum - firstNum);
                    break;
                case "*":
                    firstNum = number.pop();
                    secondNum = number.pop();
                    number.push(secondNum * firstNum);
                    break;
                case "/":
                    firstNum = number.pop();
                    secondNum = number.pop();
                    number.push(Math.round(secondNum / firstNum * 100000) / 100000.0);
                    break;
                default:
                    number.push(Double.parseDouble(arg));
                    break;
            }
        }

        return number.pop();

    }


    public String convertToPostfix(String input) {
        List<String> result = new ArrayList<>();
        Stack<String> operator = new Stack<>();

        String[] splitedExpr = converStrToArrayBySplit(input);
        for (String arg : splitedExpr) {

            if (chkOperlator(arg)) {
                if (operator.empty()) {
                    operator.push(arg);
                    continue;
                }
                if (getOperatorPrioty(operator.peek()) >= getOperatorPrioty(arg) && getOperatorPrioty(operator.peek()) > 0) {
                    String popOperator = operator.pop();
                    result.add(popOperator);
                    operator.push(arg);
                    continue;
                }

                operator.push(arg);
                continue;
            }

            if ("(".equals(arg)) {
                operator.push(arg);
                continue;
            }

            if (")".equals(arg)) {
                while (!"(".equals(operator.peek())) {
                    result.add(operator.pop());
                }
                operator.pop();
                continue;
            }

            result.add(arg);
        }
        if (!operator.empty()) {
            while (!operator.empty()) {
                result.add(operator.pop());
            }
        }

        StringBuffer postfix = new StringBuffer();
        for (String character : result) {
            postfix.append(character + " ");
        }
        return postfix.toString().substring(0, postfix.length() - 1);
    }

    public int getOperatorPrioty(String operator) {
        if ("(".equals(operator) || ")".equals(operator)) {
            return 0;
        }
        if ("+".equals(operator) || "-".equals(operator)) {
            return 1;
        }
        if ("*".equals(operator) || "/".equals(operator)) {
            return 2;
        }
        return 99;
    }

    public String[] converStrToArrayBySplit(String expr) {

        char[] chars = expr.toCharArray();
        List<String> splitedExpr = new ArrayList<>();
        String numberExpr = "";

        for (Character arg : chars) {
            if (chkOperlatorIncudeBracket(arg)) {
                if (!"".equals(numberExpr)) {
                    splitedExpr.add(numberExpr);
                }
                splitedExpr.add(arg.toString());
                numberExpr = "";
                continue;
            }

            numberExpr += arg.toString();
        }
        if (!"".equals(numberExpr)) {
            splitedExpr.add(numberExpr);
        }
        String[] result = new String[splitedExpr.size()];

        return splitedExpr.toArray(result);
    }

    private boolean chkOperlatorIncudeBracket(Character arg) {
        if ('+' == arg || '-' == arg || '*' == arg || '/' == arg || '(' == arg || ')' == arg) {
            return true;
        }
        return false;
    }

    private boolean chkOperlator(String arg) {
        if ("+".equals(arg) || "-".equals(arg) || "*".equals(arg) || "/".equals(arg)) {
            return true;
        }
        return false;
    }
}
