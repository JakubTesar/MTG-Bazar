package me.mtgbazar.mtgbazar.controllers;

import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.data.entities.filter.UserFilter;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.service.cards.CardService;
import me.mtgbazar.mtgbazar.models.service.users.UserService;
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
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;

    @GetMapping
    public String getAllUsers(Model model, @RequestParam("page") Optional<Integer> page,
                              UserFilter filter) throws IOException {
        int currentPage = page.orElse(1);
        int pageSize = 36;
        Page<UserDTO> userDTOPage = userService.getAllUsers(filter, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("cardsPage", userDTOPage);
        int totalPages = userDTOPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("f", filter);
        model.addAttribute("currentPage", userDTOPage.getNumber() + 1);
        return "users/allUsers";
    }

    @GetMapping("/myProfile")
    public String getLoggedUser(Model model,
                                @RequestParam("page") Optional<Integer> page,
                                CardFilter filter
    ) throws IOException {
        UserDTO loggedUser = userService.getLoggedUser();
        model.addAttribute("user", loggedUser);
        int currentPage = page.orElse(1);
        int pageSize = 36;
        Page<CardDTO> cardDTOPage = cardService.getAllByOwnerId(PageRequest.of(currentPage - 1, pageSize), filter, loggedUser);
        model.addAttribute("cardsPage", cardDTOPage);
        int totalPages = cardDTOPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("f", filter);
        model.addAttribute("currentPage", cardDTOPage.getNumber() + 1);
        return "redirect:profile/" + loggedUser.getId();
    }

    @GetMapping("/profile/{userId}")
    public String getUserProfile(Model model,
                                 @PathVariable long userId,
                                 @RequestParam("page") Optional<Integer> page,
                                 CardFilter filter) {
        UserDTO userDTO = userService.getUserById(userId);
        UserDTO loggedUser = userService.getLoggedUser();
        model.addAttribute("user", userDTO);
        int currentPage = page.orElse(1);
        int pageSize = 36;
        Page<CardDTO> cardDTOPage = cardService.getAllByOwnerId(PageRequest.of(currentPage - 1, pageSize), filter, userDTO);
        model.addAttribute("cardsPage", cardDTOPage);
        int totalPages = cardDTOPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("f", filter);
        model.addAttribute("currentPage", cardDTOPage.getNumber() + 1);
        if (userId == loggedUser.getId()) model.addAttribute("myProfile", true);
        else model.addAttribute("myProfile", false);
        return "users/detailUser";
    }

    @GetMapping("/profile/{userId}/selling")
    public String getLoggedUserSelling(Model model,
                                       @PathVariable long userId,
                                       @RequestParam("page") Optional<Integer> page,
                                       CardFilter filter
    ) throws IOException {
        UserDTO userDTO = userService.getUserById(userId);
        model.addAttribute("user", userDTO);
        int currentPage = page.orElse(1);
        int pageSize = 36;
        Page<CardForSaleDTO> cardDTOPage = cardService.getAllSellingByOwnerId(PageRequest.of(currentPage - 1, pageSize), filter, userDTO);
        model.addAttribute("cardsPage", cardDTOPage);
        int totalPages = cardDTOPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("f", filter);
        model.addAttribute("currentPage", cardDTOPage.getNumber() + 1);
        return "users/detailUserSell";
    }
}
