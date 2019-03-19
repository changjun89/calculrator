package me.anpan.calculator.utils;

import java.util.List;
import java.util.Stack;

public class Operators {
    private Stack<String> operators = new Stack<>();

    public boolean empty() {
        return operators.isEmpty();
    }

    public void push(String arg) {
        operators.push(arg);
    }

    public String pop() {
        return operators.pop();
    }

    public String peek() {
        return operators.peek();
    }

    public boolean isLessThanTop(String arg) {
        return (CalculatorUtils.getOperatorPrioty(peek()) >= CalculatorUtils.getOperatorPrioty(arg));
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

    private void kickTop(String arg, List<String> listOperatoer) {
        listOperatoer.add(pop());
        push(arg);
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
