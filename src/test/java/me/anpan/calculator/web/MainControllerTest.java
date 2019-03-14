package me.anpan.calculator.web;

import me.anpan.calculator.service.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({MainController.class, Calculator.class})
@RunWith(SpringRunner.class)
public class MainControllerTest {

   @Autowired
   MockMvc mockMvc;


   @Test
    public void mainControllerCalculatorTest() throws Exception {
       mockMvc.perform(post("/calculator").param("expr","2+2"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string("계산식: 2+2 결과는 : 4.0"));
   }




}