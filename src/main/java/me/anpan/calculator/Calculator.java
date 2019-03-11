package me.anpan.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {


    public int calculate(String input) {
        return 0;
    }

    public int calcatePostfix(String expr) {
        String[] split = expr.split(" ");
        Stack<Integer> number = new Stack<>();
        int firstNum;
        int secondNum;

        for ( String arg : split) {
            switch (arg) {
                case "+":
                    firstNum = number.pop();
                    secondNum = number.pop();
                    number.push( secondNum +firstNum);
                    break;
                case "-":
                    firstNum = number.pop();
                    secondNum = number.pop();
                    number.push( secondNum -firstNum);
                    break;
                case "*":
                    firstNum = number.pop();
                    secondNum = number.pop();
                    number.push( secondNum *firstNum);
                    break;
                case "/":
                    firstNum = number.pop();
                    secondNum = number.pop();
                    number.push( secondNum /firstNum);
                    break;
                default:
                    number.push(Integer.parseInt(arg));
                    break;
            }
        }

        return number.pop();

    }


    public String convertToPostfix(String input) {
        List<Character> result = new ArrayList<>();
        Stack<Character> operator = new Stack<>();
        char[] chars = input.toCharArray();
        for(char arg : chars ) {

            if('+' == arg || '-' == arg || '*' == arg || '/' == arg ) {
                if(operator.empty()) {
                    operator.push(arg);
                    continue;
                }
                if(getOperatorPrioty(operator.peek()) >= getOperatorPrioty(arg) && getOperatorPrioty(operator.peek())>0 ) {
                    Character popOperator = operator.pop();
                    result.add(popOperator);
                    operator.push(arg);
                }else {
                    operator.push(arg);
                }
            } else if('(' == arg) {
                operator.push(arg);
            } else if(')' == arg) {
                while('('!= operator.peek()){
                    result.add(operator.pop());
                }
                operator.pop();
            } else {
                result.add(arg);
            }
        }
        if(!operator.empty()) {
            while (!operator.empty()){
                result.add(operator.pop());
            }
        }
        StringBuffer postfix = new StringBuffer();
        for (Character character : result) {
            postfix.append(character.toString()+" ");
        }
        return postfix.toString();
    }

    public int getOperatorPrioty(Character operator) {
        if('(' == operator || ')'==operator ) {
            return 0;
        }
        if('+' == operator || '-'==operator ) {
            return 1;
        }
        if('*' == operator || '/'==operator ) {
            return 2;
        }
        return 99;
    }
}
