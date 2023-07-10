package me.mtgbazar.mtgbazar.controllers;

import ch.qos.logback.core.model.Model;
import me.mtgbazar.mtgbazar.models.CalculatorDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalculatorController {
    @GetMapping
    public String renderCalculator(@ModelAttribute CalculatorDTO calculatorDTO) {
        return "calculator";
    }

    @PostMapping
    public String calculate(@ModelAttribute CalculatorDTO calculatorDTO,
                            Model model){
        return "result";
    }
}
