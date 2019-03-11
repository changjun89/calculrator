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
    public void 오퍼레이터의_우선순위를_확인한다() {
        Character input = '/';
        assertEquals(2,calculator.getOperatorPrioty(input));

    }

    @Test
    public void 중위표현을_후위표현으로_변환() {
        //String input = "A*(B+C)";
        String input2 = "(150+60/2)*2+(78-20+60)+1";
        //assertEquals("A B C + *",calculator.convertToPostfix(input));
        assertEquals("150 60 2 / + 2 * 78 20 - 60 + + 1 +",calculator.convertToPostfix(input2));
    }

    @Test
    public void 중위표현계산() {
        String arg = "5 10 5 - *";
        assertEquals(25,calculator.calcatePostfix(arg));


    }

}
