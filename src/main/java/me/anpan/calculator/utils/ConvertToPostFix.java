package me.anpan.calculator.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ConvertToPostFix extends Stack<String> {
    List<String> result = new ArrayList<>();

    private void operatorExecute(String arg) {
        if (this.empty()) {
            this.push(arg);
            return;
        }
        if (comparePriotyAndIsOperator(arg)) {
            result.add(this.pop());
            this.push(arg);
            return;
        }

        this.push(arg);
    }

    private boolean comparePriotyAndIsOperator(String arg) {
        return (CalculatorUtils.getOperatorPrioty(this.peek()) >= CalculatorUtils.getOperatorPrioty(arg) && CalculatorUtils.isOperator(this.peek()));
    }

    private void operatorOrBraketExecute(String arg) {
        if (CalculatorUtils.isOperator(arg)) {
            operatorExecute(arg);
        }
        if (CalculatorUtils.isBacket(arg)) {
            bracketExecute(arg);
        }
    }

    private void bracketExecute(String arg) {
        if ("(".equals(arg)) {
            this.push(arg);
        }

        if (")".equals(arg)) {
            while (!"(".equals(this.peek())) {
                result.add(this.pop());
            }
            this.pop();
        }
    }

    private void remainInputToList() {
        if (!this.empty()) {
            while (!this.empty()) {
                result.add(this.pop());
            }
        }
    }

    public String convertToPostfix(String input) {

        for (String arg : converStrToArrayBySplit(input)) {
            if (CalculatorUtils.isOperatorOrBaraket(arg)) {
                operatorOrBraketExecute(arg);
                continue;
            }
            result.add(arg);
        }
        remainInputToList();

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

        List<String> splitedExpr = new ArrayList<>();
        String numberExpr = "";

        for (Character arg : expr.toCharArray()) {
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
