package me.mtgbazar.mtgbazar.controllers;

import jakarta.transaction.Transactional;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.service.cards.CardLoadService;
import me.mtgbazar.mtgbazar.models.service.cards.CardService;
import me.mtgbazar.mtgbazar.models.service.email.EmailService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
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

    @Autowired
    EmailService service;

    @GetMapping
    public String getAllCards(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            CardFilter filter
    ) throws IOException {
        int currentPage = page.orElse(1);
        int pageSize = 36;
        Page<CardDTO> cardDTOPage = cardService.getAll(PageRequest.of(currentPage - 1, pageSize), filter);
        model.addAttribute("cardsPage", cardDTOPage);
        //cardDTOPage.getTotalPages()
        int totalPages = cardDTOPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("f", filter);
        model.addAttribute("currentPage", cardDTOPage.getNumber() + 1);
        return "cards/allCards";
    }

    @GetMapping("upload")
    public String createCardsDBS() throws IOException {
        return "cards/upload";
    }
    @GetMapping("createDBS")
    public String dbsCreate() throws IOException {
        cardLoadService.getAllCardsCSV();
        return "cards/allCards";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        modelMap.addAttribute("file", file);
            File path = new File("resources\\" + file.getOriginalFilename());
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();
        return "redirect:../cards";
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
