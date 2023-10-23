package me.mtgbazar.mtgbazar.controllers;

import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.service.cards.CardLoadService;
import me.mtgbazar.mtgbazar.models.service.cards.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            @RequestParam("size") Optional<Integer> size) throws IOException {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(36);
        Page<CardDTO> cardDTOPage = cardService.getAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("cardsPage", cardDTOPage);
        int totalPages = cardDTOPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(currentPage, currentPage + 10)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "cards/allCards";
    }

    @GetMapping("createCard")
    public String createCardsDBS() throws IOException {
        cardLoadService.getAllCardsCSV();
        return "cards/allCards";
    }

    @GetMapping("addCard")
    public String addCard() {
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
    public String getCardDetail(@PathVariable long cardId, Model model) {
        CardDTO cardDTO = cardService.getCardById(cardId);
        model.addAttribute("card", cardDTO);
        return "cards/detail";
    }


}
