package me.anpan.calculator.service;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class OperatorTypeTest {
    @Test
    public void isOperatorTest () {
        assertEquals(true,OperatorType.isOperator("("));
        assertEquals(false,OperatorType.isOperator("3"));
        assertEquals(true,OperatorType.isOperator("+"));
    }

    @Test
    public void calculateTest() {
        assertThat(4.0 , is(OperatorType.calculate("+",2,2)));
    }
}