package me.mtgbazar.mtgbazar.controllers;

import jakarta.validation.Valid;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.repositories.CardsForSaleRepositories;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.EmailDTO;
import me.mtgbazar.mtgbazar.models.service.access.AccessService;
import me.mtgbazar.mtgbazar.models.service.access.DuplicateEmailException;
import me.mtgbazar.mtgbazar.models.service.email.EmailService;
import me.mtgbazar.mtgbazar.models.service.trade.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    TradeService tradeService;
    @Autowired
    private CardsForSaleRepositories cardsForSaleRepositories;
    @Autowired
    EmailService emailService;
    @Autowired
    AccessService accessService;

    @GetMapping("/forSale/{cardId}")
    public String renderForSaleForm(@ModelAttribute CardForSaleDTO cardForSaleDTO, @PathVariable long cardId) {
        return "trade/sell";
    }

    @PostMapping("/forSale/{cardId}")
    public String forSaleCard(@Valid @ModelAttribute CardForSaleDTO cardForSaleDTO,
                              @PathVariable long cardId,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors())
            return renderForSaleForm(cardForSaleDTO, cardId);
        redirectAttributes.addFlashAttribute("success", "Card placed on a market.");
        tradeService.forSaleCard(cardId, cardForSaleDTO);
        return "redirect: /../../../cards";
    }

    @GetMapping("/buy/{cardId}")
    public String renderBuyForm(@ModelAttribute CardForSaleDTO cardForSaleDTO, EmailDTO emailDTO, @PathVariable long cardId) {
        return "trade/buy";
    }

    @PostMapping("/buy/{cardForBuyId}")
    public String buyCard(@Valid @ModelAttribute EmailDTO emailDTO, CardForSaleDTO cardForSaleDTO,
                          @PathVariable long cardForBuyId,
                          BindingResult result,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if (result.hasErrors())
            return renderForSaleForm(cardForSaleDTO, cardForBuyId);
        model.addAttribute("c", cardForBuyId);
        redirectAttributes.addFlashAttribute("success", "Card placed on a market.");
        CardForSaleEntity card = cardsForSaleRepositories.findById(cardForBuyId).orElseThrow();
        emailService.sendEmail(emailDTO, card);
        return "redirect: /../../../cards";
    }
}