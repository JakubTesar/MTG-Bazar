package me.mtgbazar.mtgbazar.controllers;

import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.service.cards.CardLoadService;
import me.mtgbazar.mtgbazar.models.service.cards.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/cards")
public class CardsController {
    @Autowired
    CardService cardService;
    @Autowired
    CardLoadService cardLoadService;
    @GetMapping
    public String getAllCards(Model model) throws IOException {
        model.addAttribute("cards", cardService.getAll().subList(0,36));
        return "cards/allCards";
    }

    @GetMapping("createCard")
    public String createCardsDBS() throws IOException {
        cardLoadService.getAllCardsCSV();
        return "cards/allCards";
    }
    @GetMapping("addCard")
    public String addCard(){
        cardService.addCardToAccount();
        return "cards/allCards";
    }

  /*  @PostMapping("createCard")
    public String createCard(@Valid @ModelAttribute CardDTO cardDTO, BindingResult result){
        if (result.hasErrors())
            return createCardRender(cardDTO);

        cardService.createCard(cardDTO);
        return "redirect:/cards";
    }*/

    @GetMapping("{cardId}")
    public String getCardDetail(@PathVariable long cardId, Model model){
        CardDTO cardDTO = cardService.getCardById(cardId);
        model.addAttribute("card",cardDTO);
        return "cards/detail";
    }


}
