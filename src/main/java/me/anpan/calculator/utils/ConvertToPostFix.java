package me.anpan.calculator.utils;

import me.anpan.calculator.service.OperatorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class ConvertToPostFix {
    List<String> result = new ArrayList<>();
    Operators operators = new Operators();

    private void operatorOrBraketExecute(String arg) {
        if (CalculatorUtils.isOperator(arg)) {
            operators.operatorExecute(arg, result);
        }
        if (CalculatorUtils.isBracket(arg)) {
            operators.bracketExecute(arg, result);
        }
    }

    public String convertToPostfix(String input) {

        for (String arg : getExpressionArray(input)) {
            if (CalculatorUtils.isOperatorOrBaraket(arg)) {
                operatorOrBraketExecute(arg);
                continue;
            }
            result.add(arg);
        }
        operators.remainInputToList(result);

        return listToStrWithBlank(result);
    }

    private String listToStrWithBlank(List<String> result) {
        StringBuilder postfix = new StringBuilder();
        for (String character : result) {
            postfix.append(character + " ");
        }
        return postfix.toString().substring(0, postfix.length() - 1);
    }

    public String[] getExpressionArray(String expr) {
        return convertListToStringArray(getExpressionList(expr));
    }

    private String[] convertListToStringArray(List<String> arg) {
        return arg.toArray(new String[arg.size()]);
    }

    private List<String> getExpressionList(String expr) {
        List<String> splitedExpr = new ArrayList<>();
        String numberExpr = "";

        for (Character arg : expr.toCharArray()) {
            if (!CalculatorUtils.isOperlatorIncudeBracket(arg)) {
                numberExpr += arg.toString();
                continue;
            }

            if (!"".equals(numberExpr)) {
                splitedExpr.add(numberExpr);
            }
            splitedExpr.add(arg.toString());
            numberExpr = "";
        }

        if (!"".equals(numberExpr)) {
            splitedExpr.add(numberExpr);
        }
        return splitedExpr;
    }

}
