package me.anpan.calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorApplicationTests {

    Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }
    @Test
    public void 단순_더하기테스트_2더하기4는6() {
        String input ="2+2";
        assertEquals(6,calculator.calculate(input));
    }

    @Test
    public void 중위표현을_후위표현으로_변환() {
        String input = "(2+5)*3*(2+1)";

        assertEquals("25+3*21+*",calculator.convertToPostfix(input));
    }

}
