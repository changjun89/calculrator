package me.anpan.calculator.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorUtilsTest {

    @Test
    public void 오퍼레이터의_우선순위를_확인한다() {
        String input = "/";
        assertEquals(2, CalculatorUtils.getOperatorPrioty(input));
    }

    @Test
    public void 연산자인지_확인한다() {
        String input = "+";
        assertEquals(true, CalculatorUtils.isOperator(input));
    }

    @Test
    public void 연산자인지_확인한다_괄호포함() {
        Character input = '(';
        assertEquals(true, CalculatorUtils.isOperlatorIncudeBracket(input));
    }
}