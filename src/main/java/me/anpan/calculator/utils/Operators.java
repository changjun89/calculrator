package me.anpan.calculator.utils;

import java.util.List;
import java.util.Stack;

public class Operators {
    private Stack<String> operators = new Stack<>();

    private void push(String operator) {
        operators.push(operator);
    }

    private String pop() {
        return operators.pop();
    }

    private String peek() {
        return operators.peek();
    }

    public boolean empty() {
        return operators.isEmpty();
    }

    public boolean isLessThanTop(String operatoer) {
        return (CalculatorUtils.getOperatorPrioty(peek()) >= CalculatorUtils.getOperatorPrioty(operatoer));
    }

    public void operatorExecute(String arg, List<String> listOperatoer) {
        if (empty()) {
            push(arg);
            return;
        }
        if (isLessThanTop(arg)) {
            kickTop(arg, listOperatoer);
            return;
        }
        push(arg);
    }

    private void kickTop(String operator, List<String> listOperatoer) {
        listOperatoer.add(pop());
        push(operator);
    }

    public void bracketExecute(String arg, List<String> listOperator) {
        if ("(".equals(arg)) {
            rightOpenBracketExecute(arg);
        }

        if (")".equals(arg)) {
            leftOpenBracketExcute(listOperator);
        }
    }

    private void rightOpenBracketExecute(String arg) {
        push(arg);
    }

    private void leftOpenBracketExcute(List<String> listOperator) {
        while (!"(".equals(peek())) {
            listOperator.add(pop());
        }
        pop();
    }

    public void remainInputToList(List<String> listOperator) {
        if (!operators.empty()) {
            while (!operators.empty()) {
                listOperator.add(operators.pop());
            }
        }
    }


}
