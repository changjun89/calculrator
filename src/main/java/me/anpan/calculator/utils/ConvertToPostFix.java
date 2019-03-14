package me.anpan.calculator.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ConvertToPostFix {

    public String convertToPostfix(String input) {
        List<String> result = new ArrayList<>();
        Stack<String> operator = new Stack<>();

        for (String arg : converStrToArrayBySplit(input)) {

            if (CalculatorUtils.isOperator(arg)) {
                if (operator.empty()) {
                    operator.push(arg);
                    continue;
                }
                if (CalculatorUtils.getOperatorPrioty(operator.peek()) >= CalculatorUtils.getOperatorPrioty(arg)
                        && CalculatorUtils.getOperatorPrioty(operator.peek()) > 0) {
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
        return listToStrWithBlank(result);
    }

    private String listToStrWithBlank(List<String> result) {
        StringBuffer postfix = new StringBuffer();
        for (String character : result) {
            postfix.append(character + " ");
        }
        return postfix.toString().substring(0, postfix.length() - 1);
    }

    public String[] converStrToArrayBySplit(String expr) {

        char[] chars = expr.toCharArray();
        List<String> splitedExpr = new ArrayList<>();
        String numberExpr = "";

        for (Character arg : chars) {
            if (CalculatorUtils.isOperlatorIncudeBracket(arg)) {
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


}
