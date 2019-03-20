package me.anpan.calculator.service;

import me.anpan.calculator.utils.CalculatorUtils;
import me.anpan.calculator.utils.ConvertToPostFix;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Stream;

@Service
public class Calculator {
    public double calculate(String input) throws Exception {
        ConvertToPostFix convertToPostFix = new ConvertToPostFix();
        String postExpression = convertToPostFix.convertToPostfix(input);
        return  new PostfixCalculaor().calculate(postExpression);
    }


}
