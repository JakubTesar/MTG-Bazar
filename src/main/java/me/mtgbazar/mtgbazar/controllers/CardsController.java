package me.mtgbazar.mtgbazar.controllers;

import jakarta.transaction.Transactional;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.service.cards.CardLoadService;
import me.mtgbazar.mtgbazar.models.service.cards.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/cards")
public class CardsController {
    @Autowired
    CardService cardService;
    @Autowired
    CardLoadService cardLoadService;

    @GetMapping
    public String getAllCards(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size
           ) throws IOException {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(36);
        Page<CardDTO> cardDTOPage = cardService.getAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("cardsPage", cardDTOPage);
        int totalPages = cardDTOPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(currentPage > 1 ? (currentPage - 1 >= totalPages - 10 ? (currentPage == totalPages ? currentPage - 10 : (currentPage == totalPages-1 ? currentPage - 9 : (currentPage == totalPages-2 ? currentPage - 8 : (currentPage == totalPages-3 ? currentPage - 7 : (currentPage == totalPages-4 ? currentPage - 6 : (currentPage == totalPages-5 ? currentPage - 5 : (currentPage == totalPages-6 ? currentPage - 4 : (currentPage == totalPages-7 ? currentPage - 3 : (currentPage == totalPages-8 ? currentPage - 2 : (currentPage == totalPages-9 ? currentPage - 1 : currentPage)))))))))) : currentPage - 1) : currentPage, (totalPages <= 10 ? totalPages : ((currentPage + 10) >= totalPages ? totalPages : currentPage + 10)))
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("total", totalPages);
        }
        return "cards/allCards";
    }

    @GetMapping("createCard")
    public String createCardsDBS() throws IOException {
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
    public String getCardDetail(@PathVariable long cardId, Model model) {
        CardDTO cardDTO = cardService.getCardById(cardId);
        List<UserDTO> sellingUsersForCard = cardService.getCardOwnersByCardId(cardId);
        model.addAttribute("card", cardDTO);
        model.addAttribute("sellingUsersForCard", sellingUsersForCard);
        return "cards/detail";
    }
}
