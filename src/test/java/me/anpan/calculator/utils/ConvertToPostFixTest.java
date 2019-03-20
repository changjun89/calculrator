package me.anpan.calculator.utils;

import me.anpan.calculator.service.ConvertToPostFix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertToPostFixTest {
    ConvertToPostFix convertToPostfix;

    @Before
    public void setup() {
        convertToPostfix = new ConvertToPostFix();
    }

    @Test
    public void 중위표현을_후위표현으로_변환() {
        //String input = "A*(B+C)";
        String input2 = "(150+60/2)*2+(78-20+60)+1";
        //assertEquals("A B C + *",calculator.convertToPostfix(input));
        assertEquals("150 60 2 / + 2 * 78 20 - 60 + + 1 +", convertToPostfix.convertToPostfix(input2));
    }

}