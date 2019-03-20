package me.anpan.calculator.service;

import java.util.ArrayList;
import java.util.List;


public class ConvertToPostFix {
    List<String> result = new ArrayList<>();
    Operators operators = new Operators();

    public String convertToPostfix(String expression) {

        for (String arg : convertExprToArray(expression)) {
            if (OperatorType.isOperator(arg)) {
                pushOperator(arg);
                continue;
            }
            result.add(arg);
        }
        operators.remainInputToList(result);

        return makePostfixString(result);
    }

    private void pushOperator(String arg) {
        if (OperatorType.isBracket(arg)) {
            operators.pushBracket(arg, result);
        } else {
            operators.pushOperator(arg, result);
        }
    }

    private String makePostfixString(List<String> result) {
        StringBuilder postfix = new StringBuilder();
        for (String character : result) {
            postfix.append(character + " ");
        }
        return postfix.toString().substring(0, postfix.length() - 1);
    }

    private String[] convertExprToArray(String expr) {
        List<String> exprList = convertExprToList(expr);
        return convertListToStringArray(exprList);
    }

    private String[] convertListToStringArray(List<String> expr) {
        return expr.toArray(new String[expr.size()]);
    }

    private List<String> convertExprToList(String expr) {
        List<String> splitedExpr = new ArrayList<>();
        String numberExpr = "";

        for (Character arg : expr.toCharArray()) {
            if (!OperatorType.isOperator(arg)) {
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
