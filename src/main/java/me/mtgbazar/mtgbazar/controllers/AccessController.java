package me.mtgbazar.mtgbazar.controllers;


import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.service.access.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/access")
public class AccessController {

    @Autowired
    AccessService accessService;

    @GetMapping("/register")
    public String renderRegisterForm(@ModelAttribute UserDTO userDTO) throws IOException {
        return "access/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO, Model model) {
        accessService.registerUser(userDTO);
        return "redirect:/cards";
    }

    @GetMapping("/login")
    public String renderLoginForm(@ModelAttribute UserDTO userDTO) {
        return "access/login";
    }

    @PostMapping("/login")
    public String loginCheck(@ModelAttribute UserDTO userDTO) {
        accessService.verifyUser(userDTO);
        return "redirect:/cards";
    }
}
