package me.mtgbazar.mtgbazar.controllers;

import jakarta.transaction.Transactional;
import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.models.DTO.BasicCardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.service.cards.CardLoadService;
import me.mtgbazar.mtgbazar.models.service.cards.CardService;
import me.mtgbazar.mtgbazar.models.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cards")
public class CardsController {
    @Autowired
    CardService cardService;
    @Autowired
    CardLoadService cardLoadService;
    @Autowired
    EmailService service;

    @GetMapping
    public String getAllCards(Model model, @RequestParam("page") Optional<Integer> page, CardFilter filter) throws IOException {
        int currentPage = page.orElse(1);
        int pageSize = 36;
        Page<CardDTO> cardDTOPage = cardService.getAll(PageRequest.of(currentPage - 1, pageSize), filter);
        model.addAttribute("cardsPage", cardDTOPage);
        int totalPages = cardDTOPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("f", filter);
        model.addAttribute("currentPage", cardDTOPage.getNumber() + 1);
        return "cards/allCards";
    }

    @GetMapping("createDBS")
    public String dbsCreate() throws IOException {
        cardLoadService.getAllCardsCSV();
        return "cards/allCards";
    }

    @PostMapping("{cardId}")
    @Transactional
    public String addCard(@PathVariable long cardId) {
        cardService.addCardToAccount(cardId);
        return "redirect: /../../cards";
    }

    @GetMapping("{cardId}")
    public String getCardDetail(@PathVariable long cardId, @RequestParam("page") Optional<Integer> page, Model model) {
        int currentPage = page.orElse(1);
        int pageSize = 10;
        Page<BasicCardForSaleDTO> cardForSaleDTOS = cardService.getPagedOffers(PageRequest.of(currentPage - 1, pageSize), cardId);
        model.addAttribute("cardForSalePage", cardForSaleDTOS);
        int totalPages = cardForSaleDTOS.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", cardForSaleDTOS.getNumber() + 1);
        CardDTO cardDTO = cardService.getCardById(cardId);
        model.addAttribute("card", cardDTO);
        if (cardService.isAlreadyWatched(cardId)) {
            model.addAttribute("toggle", true);
        } else {
            model.addAttribute("toggle", false);
        }
        return "cards/detail";
    }



    @GetMapping("{cardId}/watchdog")
    public String toggleWatchList(@PathVariable long cardId, Model model) {
        cardService.toggleCardWatchlist(cardId);
        return "redirect:../../cards/" + cardId;
    }

}
