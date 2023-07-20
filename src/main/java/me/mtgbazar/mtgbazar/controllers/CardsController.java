package me.mtgbazar.mtgbazar.controllers;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@Controller
@RequestMapping("/cards")
public class CardsController {
    @Autowired
    CardService cardService;
    @GetMapping
    public String getAllcards(Model model){
        model.addAttribute("cards", cardService.getAll());
        return "cards/allCards";
    }

    @GetMapping("createCard")
    public String createCardRender(@ModelAttribute CardDTO cardDTO){
        return "cards/createCard";
    }

    @PostMapping("createCard")
    public String createCard(@Valid @ModelAttribute CardDTO cardDTO, BindingResult result){
        if (result.hasErrors())
            return createCardRender(cardDTO);

        cardService.createCard(cardDTO);
        System.out.println(cardDTO.getName() + " – " + cardDTO.getCost());
        return "redirect:/cards";
    }

    @GetMapping("{cardId}")
    public String getCardDetail(@PathVariable long cardId, Model model){
        CardDTO cardDTO = cardService.getCardById(cardId);
        model.addAttribute("card",cardDTO);
        return "cards/detail";
    }
}
