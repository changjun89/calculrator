package me.anpan.calculator.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CalculatorTest {

    Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void 괄호를포함한_연산() throws Exception {
        String input = "(150+60/2)*2+(78-20+60)+1";
        assertThat(479.0, is(calculator.calculate(input)));
        //assertEquals(479, calculator.calculate(input));
    }

    @Test
    public void 소수점_나누기_테스트() throws Exception {
        String input = "10/3";
        Double result = 3.33333;
        assertThat(result, is(calculator.calculate(input)));
    }

    @Test
    public void 단순_나누기_테스트() throws Exception {
        String input = "4/2";
        assertThat(2.0, is(calculator.calculate(input)));
    }

    @Test
    public void 단순_곱하기_테스트() throws Exception {
        String input = "4*2";
        assertThat(8.0, is(calculator.calculate(input)));
    }

    @Test
    public void 단순_빼기_테스트() throws Exception {
        String input = "4-2";
        assertThat(2.0, is(calculator.calculate(input)));
    }


    @Test
    public void 단순_더하기_테스트() throws Exception {
        String input = "2+4";
        assertThat(6.0, is(calculator.calculate(input)));
    }

    @Test
    public void 중위표현계산() throws Exception {
        String arg = "5 10 5 - *";
        assertThat(25.0, is(calculator.calcatePostfix(arg)));
    }


}