package me.anpan.calculator;

import me.anpan.calculator.service.Calculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorApplicationTests {

    Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void 괄호를포함한_연산() {
        String input = "(150+60/2)*2+(78-20+60)+1";
        assertThat(479.0, is(calculator.calculate(input)));
        //assertEquals(479, calculator.calculate(input));
    }

    @Test
    public void 소수점_나누기_테스트() {
        String input = "10/3";
        Double result = 3.33333;
        assertThat(result, is(calculator.calculate(input)));
    }

    @Test
    public void 단순_나누기_테스트() {
        String input = "4/2";
        assertThat(2.0, is(calculator.calculate(input)));
    }

    @Test
    public void 단순_곱하기_테스트() {
        String input = "4*2";
        assertThat(8.0, is(calculator.calculate(input)));
    }

    @Test
    public void 단순_빼기_테스트() {
        String input = "4-2";
        assertThat(2.0, is(calculator.calculate(input)));
    }


    @Test
    public void 단순_더하기_테스트() {
        String input = "2+4";
        assertThat(6.0, is(calculator.calculate(input)));
    }

    @Test
    public void 오퍼레이터의_우선순위를_확인한다() {
        String input = "/";
        assertEquals(2, calculator.getOperatorPrioty(input));

    }

    @Test
    public void 중위표현을_후위표현으로_변환() {
        //String input = "A*(B+C)";
        String input2 = "(150+60/2)*2+(78-20+60)+1";
        //assertEquals("A B C + *",calculator.convertToPostfix(input));
        assertEquals("150 60 2 / + 2 * 78 20 - 60 + + 1 +", calculator.convertToPostfix(input2));
    }

    @Test
    public void 중위표현계산() {
        String arg = "5 10 5 - *";
        assertThat(25.0, is(calculator.calcatePostfix(arg)));
    }

    @Test
    public void 식을_배열로_변한환다() {
        String arg = "(10+20)*3";
        String[] result = {"(", "10", "+", "20", ")", "*", "3"};
        assertArrayEquals(result, calculator.converStrToArrayBySplit(arg));

    }

}
