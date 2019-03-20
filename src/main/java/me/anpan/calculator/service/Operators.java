package me.anpan.calculator.service;

import java.util.List;
import java.util.Stack;

public class Operators {
    private Stack<Operator> operators = new Stack<>();

    private void push(String operator) {
        operators.push(new Operator(operator));
    }

    private Operator pop() {
        return operators.pop();
    }

    private Operator peek() {
        return operators.peek();
    }

    public boolean empty() {
        return operators.isEmpty();
    }

    public boolean isLessThanTop(String operator) {
        return peek().isHigherPriority(operator);
    }

    public void pushOperator(String operator, List<String> listOperator) {
        if (empty()) {
            push(operator);
            return;
        }
        if (isLessThanTop(operator)) {
            kickTop(operator, listOperator);
            push(operator);
            return;
        }
        push(operator);
    }

    private void kickTop(String operator, List<String> listOperatoer) {
        listOperatoer.add(pop().getSymbol());
    }

    public void pushBracket(String arg, List<String> listOperator) {
        if ("(".equals(arg)) {
            rightOpenBracketPush(arg);
        }

        if (")".equals(arg)) {
            leftOpenBracketPush(listOperator);
        }
    }

    private void rightOpenBracketPush(String arg) {
        push(arg);
    }

    private void leftOpenBracketPush(List<String> listOperator) {
        while (!"(".equals(peek().getSymbol())) {
            listOperator.add(pop().getSymbol());
        }
        pop();
    }

    public void remainInputToList(List<String> listOperator) {
        if (!operators.empty()) {
            while (!operators.empty()) {
                listOperator.add(operators.pop().getSymbol());
            }
        }
    }


}
