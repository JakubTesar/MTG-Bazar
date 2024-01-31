package me.mtgbazar.mtgbazar.controllers;

import jakarta.validation.Valid;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.EmailDTO;
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
    EmailService emailService;

    @GetMapping("/forSale/{cardId}")
    public String renderForSaleForm(@ModelAttribute CardForSaleDTO cardForSaleDTO, @PathVariable long cardId) {
        return "trade/sell";
    }

    @PostMapping("/forSale/{cardId}")
    public String forSaleCard(@Valid @ModelAttribute CardForSaleDTO cardForSaleDTO,
                              @PathVariable long cardId,
                              BindingResult result,
                              RedirectAttributes redirectAttributes
    ) throws IOException, DuplicateEmailException {
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

    @PostMapping("/buy/{cardId}")
    public String buyCard(@Valid @ModelAttribute EmailDTO emailDTO, CardForSaleDTO cardForSaleDTO,
                          @PathVariable long cardId,
                          BindingResult result,
                          RedirectAttributes redirectAttributes,
                          Model model
    ) throws IOException, DuplicateEmailException {
        if (result.hasErrors())
            return renderForSaleForm(cardForSaleDTO, cardId);
        model.addAttribute("c",cardId);
        redirectAttributes.addFlashAttribute("success", "Card placed on a market.");
        emailService.sendEmail(emailDTO);
        return "redirect: /../../../cards";
    }
}