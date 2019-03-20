package me.anpan.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator {
    public double calculate(String input) throws Exception {
        ConvertToPostFix convertToPostFix = new ConvertToPostFix();
        String postExpression = convertToPostFix.convertToPostfix(input);
        return new PostfixCalculaor().calculate(postExpression);
    }


}
