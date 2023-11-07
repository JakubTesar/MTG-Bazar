package me.mtgbazar.mtgbazar.controllers;

import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;

    @GetMapping
    public String getAllUsers(Model model) throws IOException {
        model.addAttribute("users", userService.getAllUsers().subList(0, 2));
        return "users/allUsers";
    }

    @GetMapping("/myProfile")
    public String getLoggedUser(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) throws IOException {
        UserDTO loggedUser = userService.getLoggedUser();
        model.addAttribute("user", loggedUser);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(36);
        Page<CardDTO> cardDTOPage = cardService.getAllByOwnerId(PageRequest.of(currentPage - 1, pageSize), loggedUser);
        model.addAttribute("cardsPage", cardDTOPage);
        int totalPages = cardDTOPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(currentPage > 1 ? (currentPage - 1 >= totalPages - 10 ? currentPage - (totalPages-currentPage) : currentPage - 1) : currentPage, (totalPages <= 10 ? totalPages : ((currentPage + 10) >= totalPages ? totalPages : currentPage + 10)))
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "users/detailUser";
    }

    @GetMapping("/profile/{userId}")
    public String getUserProfile(Model model, @PathVariable long userId) {
        UserDTO userDTO = userService.getUserById(userId);
        model.addAttribute("user", userDTO);
        return "users/detailUser";
    }


}
