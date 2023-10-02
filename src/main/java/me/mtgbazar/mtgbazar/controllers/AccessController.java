package me.mtgbazar.mtgbazar.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import me.mtgbazar.mtgbazar.models.DTO.UserAccessDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.service.access.AccessService;
import me.mtgbazar.mtgbazar.models.service.access.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/access")
public class AccessController {

    @Autowired
    AccessService accessService;

    @GetMapping("/register")
    public String renderRegisterForm(@ModelAttribute UserAccessDTO userDTO) throws IOException {
        return "access/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserAccessDTO userDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes
    ) throws IOException, DuplicateEmailException {
        if (result.hasErrors())
            return renderRegisterForm(userDTO);
        redirectAttributes.addFlashAttribute("success", "User registered.");
        accessService.registerUser(userDTO);
        return "redirect:access/login";
    }

    @GetMapping("/login")
    public String renderLoginForm(@ModelAttribute UserAccessDTO userDTO) {
        return "access/login";
    }


    @PostMapping("/login2")
    public String login(
            @ModelAttribute("email") String email,
            @ModelAttribute("password") String password,
            HttpServletRequest req
    ) {
        try {
            req.login(email, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "access/login";
    }
}
