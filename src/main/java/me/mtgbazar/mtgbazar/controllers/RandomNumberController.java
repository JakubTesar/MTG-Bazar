package me.mtgbazar.mtgbazar.controllers;

import me.mtgbazar.mtgbazar.models.DTO.RandomNumberDTO;
import me.mtgbazar.mtgbazar.models.service.RandomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/randomNumber")
public class RandomNumberController {

    @Autowired
    public RandomNumberService randomNumberService = new RandomNumberService();

    @GetMapping
    public String renderGenerator(@ModelAttribute RandomNumberDTO randomNumberDTO){
        return "randomNumber";
    }

    @PostMapping
    public String randomize(@ModelAttribute RandomNumberDTO randomNumberDTO, Model model){
        model.addAttribute("randomNum", randomNumberService.randomize(
                        randomNumberDTO.getNumber1(),
                        randomNumberDTO.getNumber2()
                ));
        return "randomNumber";
    }

}
