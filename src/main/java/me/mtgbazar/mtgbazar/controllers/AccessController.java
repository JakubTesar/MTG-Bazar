package me.mtgbazar.mtgbazar.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.models.DTO.UserAccessDTO;
import me.mtgbazar.mtgbazar.models.exeptions.DuplicateUsernameException;
import me.mtgbazar.mtgbazar.models.service.access.AccessService;
import me.mtgbazar.mtgbazar.models.service.access.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/access")
public class AccessController {
    @Autowired
    AccessService accessService;
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    @GetMapping("/register")
    public String renderRegisterForm(@ModelAttribute UserAccessDTO userDTO) throws IOException {
        return "access/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserAccessDTO userDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes
    ) throws IOException {
        if (result.hasErrors())
            return renderRegisterForm(userDTO);
        try {
            accessService.registerUser(userDTO);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "User with this email already exist");
            return "/access/register";
        } catch (DuplicateUsernameException e) {
            result.rejectValue("username", "error", "This username is already used, please choose another one");
            return "/access/register";
        }
        redirectAttributes.addFlashAttribute("success", "User registered");
        return "/access/verifyEmailSend";
    }
    @GetMapping("/verify")
    public String renderSendVerification(){
        return "/access/sendVerification";
    }
    @PostMapping("/verify")
    public String sendVerification(){
        accessService.sendVerification();
        return "/access/verifyEmailSend";
    }
    @GetMapping("/verify/{key}")
    public String renderVerifyUser(@PathVariable String key, Model model){
        model.addAttribute("msg", "");
        return "/access/verify";
    }
    @PostMapping("/verify/{key}")
    public String verifyUser(@PathVariable String key, Model model){
        boolean valid = accessService.verify(key);
        if (valid) {
            model.addAttribute("msg", "Your account is verified");
        } else {
            model.addAttribute("msg", "Something went wrong");
        }
        return "/access/verifyFinal";
    }
    @GetMapping("/login")
    public String renderLoginForm(@ModelAttribute UserAccessDTO userDTO) {
        return "/access/login";
    }

    @PostMapping("/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:../cards";
    }
}
