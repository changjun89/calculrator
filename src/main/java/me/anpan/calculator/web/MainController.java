package me.anpan.calculator.web;

import me.anpan.calculator.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    Calculator calculator;

    @PostMapping("/calculator")
    public String calculator(@RequestParam String expr) throws Exception{
        double result = calculator.calculate(expr);
        return "계산식: "+ expr + " 결과는 : " + result;
    }
}
