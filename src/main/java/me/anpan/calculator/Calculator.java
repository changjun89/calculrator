package me.anpan.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {


    public int calculate(String input) {
        return calcatePostfix(convertToPostfix(input));
    }

    public int calcatePostfix(String expr) {
        String[] split = expr.split(" ");
        Stack<Integer> number = new Stack<>();
        int firstNum;
        int secondNum;

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
                    number.push(secondNum / firstNum);
                    break;
                default:
                    number.push(Integer.parseInt(arg));
                    break;
            }
        }

        return number.pop();

    }


    public String convertToPostfix(String input) {
        List<String> result = new ArrayList<>();
        Stack<String> operator = new Stack<>();

        String[] splitedExpr = converStrToArrayBySplit(input);
        char[] chars = input.toCharArray();
        for (String arg : splitedExpr) {

            if ("+".equals(arg) || "-".equals(arg) || "*".equals(arg) || "/".equals(arg)) {
                if (operator.empty()) {
                    operator.push(arg);
                    continue;
                }
                if (getOperatorPrioty(operator.peek()) >= getOperatorPrioty(arg) && getOperatorPrioty(operator.peek()) > 0) {
                    String popOperator = operator.pop();
                    result.add(popOperator);
                    operator.push(arg);
                } else {
                    operator.push(arg);
                }
            } else if ("(".equals(arg)) {
                operator.push(arg);
            } else if (")".equals(arg)) {
                while (!"(".equals(operator.peek())) {
                    result.add(operator.pop());
                }
                operator.pop();
            } else {
                result.add(arg);
            }
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
            if ('+' == arg || '-' == arg || '*' == arg || '/' == arg || '(' == arg || ')' == arg) {
                if (!"".equals(numberExpr)) {
                    splitedExpr.add(numberExpr);
                }
                splitedExpr.add(arg.toString());
                numberExpr = "";
            } else {
                numberExpr += arg.toString();
            }
        }
        if (!"".equals(numberExpr)) {
            splitedExpr.add(numberExpr);
        }
        String[] result = new String[splitedExpr.size()];

        return splitedExpr.toArray(result);


    }
}
