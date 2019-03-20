package me.anpan.calculator.utils;

public class CalculatorUtils {

    final static String OPERATOR = "+-*/";
    final static String BRACKET = "()";

    public static boolean isOperator(String arg) {
        return OPERATOR.contains(arg);
    }

    public static boolean isBracket(String arg) {
        return BRACKET.contains(arg);
    }

    public static boolean isOperatorOrBaraket(String arg) {
        return (OPERATOR + BRACKET).contains(arg);
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
