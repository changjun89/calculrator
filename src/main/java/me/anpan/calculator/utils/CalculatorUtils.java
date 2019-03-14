package me.anpan.calculator.utils;

public class CalculatorUtils {

    final static String OPERATOR = "+-*/";

    public static boolean isOperator(String arg) {
        return OPERATOR.contains(arg);
    }

    public static int getOperatorPrioty(String operator) {
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

    public static boolean isOperlatorIncudeBracket(Character arg) {
        return ('+' == arg || '-' == arg || '*' == arg || '/' == arg || '(' == arg || ')' == arg);
    }
}
